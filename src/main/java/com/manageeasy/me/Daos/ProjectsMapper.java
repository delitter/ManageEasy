package com.manageeasy.me.Daos;

import com.manageeasy.me.Models.Projects;
import java.util.List;

public interface ProjectsMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table projects
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer pId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table projects
     *
     * @mbg.generated
     */
    int insert(Projects record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table projects
     *
     * @mbg.generated
     */
    Projects selectByPrimaryKey(Integer pId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table projects
     *
     * @mbg.generated
     */
    List<Projects> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table projects
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(Projects record);
}