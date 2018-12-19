package com.manageeasy.me.Controller;

import com.manageeasy.me.Models.Departments;
import com.manageeasy.me.Models.QueryModel;
import com.manageeasy.me.Models.Users;
import com.manageeasy.me.Service.DepartmentService;
import com.manageeasy.me.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    final UserService userService;
    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    private DepartmentService departmentService;
    @Autowired
    public void setUserService(DepartmentService departmentService){
        this.departmentService = departmentService;
    }

    //登录，成功则返回角色Id，否则返回-1
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public void login(HttpServletRequest request, HttpServletResponse response) throws IOException, JSONException {
        String name = request.getParameter("username");
        String p = request.getParameter("password");
        //System.out.println(u);
        //System.out.println(p);
        Users users = userService.selectByName(name);
        response.setContentType("text/html;charset=UTF-8");

        if (users.getuPassword().equals(p)) {
            HttpSession session = request.getSession(true);
            String sessionId = session.getId();
            session.setAttribute("user", users);
            Cookie cookie = new Cookie("JSESSIONID", sessionId);
            cookie.setPath("/");
            response.setStatus(200);
            response.addCookie(cookie);
            JSONObject res = new JSONObject();
            response.setContentType("application/json;charset=UTF-8");
            res.accumulate("cId", users.getcId());
            res.accumulate("uPassword", users.getuPassword());
            res.accumulate("dId", users.getdId());
            res.accumulate("uId", users.getuId());
            res.accumulate("uComment", users.getuComment());
            res.accumulate("uName", users.getuName());
            res.accumulate("uRegtime", users.getuRegtime());
            res.accumulate("uUptime", users.getuUptime());
            response.getWriter().write(res.toString());
        } else {
            response.setStatus(400);
            response.getWriter().write("-1");
        }
    }

    //退出，使session失效
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public ResponseEntity<String> logout(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.invalidate();
        return new ResponseEntity<>("成功", HttpStatus.OK);
    }

    //新增
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<Users> add(@RequestBody Users users){
        return new ResponseEntity<>(userService.add(users), HttpStatus.OK);
    }

    //删除
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public ResponseEntity<String> delete(@RequestParam int id){
        return new ResponseEntity<>(userService.delete(id), HttpStatus.OK);
    }

    //修改
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseEntity<Users> update(@RequestBody Users users){
        users.setuUptime(new Date());
        return new ResponseEntity<>(userService.update(users), HttpStatus.OK);
    }

    class UserResp{
        private Users users;
        private Departments departments;

        public UserResp(){}
        public Users getUsers() {
            return users;
        }

        public void setUsers(Users users) {
            this.users = users;
        }

        public Departments getDepartments() {
            return departments;
        }

        public void setDepartments(Departments departments) {
            this.departments = departments;
        }
    }
    //查询，传0获取所有
    @RequestMapping(value = "/query", method = RequestMethod.GET)
    public ResponseEntity<QueryModel> query(
            @RequestParam int cid, @RequestParam int pageNum, @RequestParam int pageSize){
        QueryModel temp = userService.selectByCid(cid, pageNum, pageSize);
        List<Users> users = (List<Users>) temp.data;
        ArrayList<UserResp> userResps = new ArrayList<>();
        for(Users u : users){
            UserResp userResp = new UserResp();
            userResp.setUsers(u);
            userResp.setDepartments(departmentService.selectById(u.getdId()));
            userResps.add(userResp);
        }
        return new ResponseEntity<>(new QueryModel<>(userResps, temp.totalCount), HttpStatus.OK);
    }

    @RequestMapping(value = "/resetpw", method = RequestMethod.POST)
    public ResponseEntity<String> resetpw(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if(session == null)
            return new ResponseEntity<>("please login!", HttpStatus.OK);
        Users user = (Users) session.getAttribute("user");
        System.out.println(user.getuPassword());
        if(user.getuPassword().equals(request.getParameter("oldpw"))){
            user.setuPassword(request.getParameter("newpw"));
            userService.update(user);
            session.setAttribute("user", user);
            return new ResponseEntity<>("Success!", HttpStatus.OK);
        }
        else
            return new ResponseEntity<>("incorrect oldpw!", HttpStatus.OK);
    }
}
