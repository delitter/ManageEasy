package com.manageeasy.me.Daos;

import com.manageeasy.me.Models.Projectlevel;
import java.util.List;

public interface ProjectlevelMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table projectlevel
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer plId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table projectlevel
     *
     * @mbg.generated
     */
    int insert(Projectlevel record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table projectlevel
     *
     * @mbg.generated
     */
    Projectlevel selectByPrimaryKey(Integer plId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table projectlevel
     *
     * @mbg.generated
     */
    List<Projectlevel> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table projectlevel
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(Projectlevel record);
}