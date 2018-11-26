package com.manageeasy.me.Controller;

import com.manageeasy.me.Models.Users;
import com.manageeasy.me.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
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
            response.getWriter().write(users.getcId().toString());
        } else {
            response.setStatus(400);
            response.getWriter().write("-1");
        }
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public ResponseEntity<String> logout(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.invalidate();
        return new ResponseEntity<>("成功", HttpStatus.OK);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<Users> add(@RequestBody Users users){
        return new ResponseEntity<>(userService.add(users), HttpStatus.OK);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public ResponseEntity<String> delete(@RequestParam int id){
        return new ResponseEntity<>(userService.delete(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseEntity<Users> update(@RequestBody Users users){
        return new ResponseEntity<>(userService.update(users), HttpStatus.OK);
    }

    @RequestMapping(value = "/query", method = RequestMethod.GET)
    public ResponseEntity<List<Users>> query(@RequestParam int cid){
        return new ResponseEntity<>(userService.selectByCid(cid), HttpStatus.OK);
    }

    @RequestMapping(value = "/resetpw", method = RequestMethod.POST)
    public ResponseEntity<String> resetpw(HttpServletRequest request){
        HttpSession session = request.getSession();
        Users user = (Users) session.getAttribute("user");
        if(user.getuPassword() == request.getParameter("oldpw")){
            user.setuPassword(request.getParameter("newpw"));
            userService.update(user);
            session.setAttribute("user", user);
            return new ResponseEntity<>("Success!", HttpStatus.OK);
        }
        else
            return new ResponseEntity<>("incorrect oldpw!", HttpStatus.OK);
    }
}
