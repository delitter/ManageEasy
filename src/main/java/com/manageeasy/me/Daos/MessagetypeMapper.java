package com.manageeasy.me.Daos;

import com.manageeasy.me.Models.Messagetype;
import java.util.List;

public interface MessagetypeMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table messagetype
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer mtId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table messagetype
     *
     * @mbg.generated
     */
    int insert(Messagetype record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table messagetype
     *
     * @mbg.generated
     */
    Messagetype selectByPrimaryKey(Integer mtId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table messagetype
     *
     * @mbg.generated
     */
    List<Messagetype> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table messagetype
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(Messagetype record);
}