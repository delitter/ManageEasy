package com.manageeasy.me.Models;

public class Majortype {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column majortype.ma_id
     *
     * @mbg.generated
     */
    private Integer maId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column majortype.ma_name
     *
     * @mbg.generated
     */
    private String maName;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column majortype.ma_id
     *
     * @return the value of majortype.ma_id
     *
     * @mbg.generated
     */
    public Integer getMaId() {
        return maId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column majortype.ma_id
     *
     * @param maId the value for majortype.ma_id
     *
     * @mbg.generated
     */
    public void setMaId(Integer maId) {
        this.maId = maId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column majortype.ma_name
     *
     * @return the value of majortype.ma_name
     *
     * @mbg.generated
     */
    public String getMaName() {
        return maName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column majortype.ma_name
     *
     * @param maName the value for majortype.ma_name
     *
     * @mbg.generated
     */
    public void setMaName(String maName) {
        this.maName = maName == null ? null : maName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table majortype
     *
     * @mbg.generated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", maId=").append(maId);
        sb.append(", maName=").append(maName);
        sb.append("]");
        return sb.toString();
    }
}