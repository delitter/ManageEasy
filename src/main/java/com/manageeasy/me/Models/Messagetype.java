package com.manageeasy.me.Models;

public class Messagetype {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column messagetype.mt_id
     *
     * @mbg.generated
     */
    private Integer mtId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column messagetype.mt_name
     *
     * @mbg.generated
     */
    private String mtName;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column messagetype.mt_id
     *
     * @return the value of messagetype.mt_id
     *
     * @mbg.generated
     */
    public Integer getMtId() {
        return mtId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column messagetype.mt_id
     *
     * @param mtId the value for messagetype.mt_id
     *
     * @mbg.generated
     */
    public void setMtId(Integer mtId) {
        this.mtId = mtId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column messagetype.mt_name
     *
     * @return the value of messagetype.mt_name
     *
     * @mbg.generated
     */
    public String getMtName() {
        return mtName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column messagetype.mt_name
     *
     * @param mtName the value for messagetype.mt_name
     *
     * @mbg.generated
     */
    public void setMtName(String mtName) {
        this.mtName = mtName == null ? null : mtName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table messagetype
     *
     * @mbg.generated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", mtId=").append(mtId);
        sb.append(", mtName=").append(mtName);
        sb.append("]");
        return sb.toString();
    }
}