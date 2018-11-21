package com.manageeasy.me.Models;

import java.math.BigDecimal;
import java.util.Date;

public class Judge {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column judge.j_id
     *
     * @mbg.generated
     */
    private Integer jId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column judge.j_endTime
     *
     * @mbg.generated
     */
    private Date jEndtime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column judge.p_id
     *
     * @mbg.generated
     */
    private Integer pId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column judge.u_id
     *
     * @mbg.generated
     */
    private Integer uId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column judge.j_score
     *
     * @mbg.generated
     */
    private BigDecimal jScore;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column judge.j_comment
     *
     * @mbg.generated
     */
    private String jComment;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column judge.j_id
     *
     * @return the value of judge.j_id
     *
     * @mbg.generated
     */
    public Integer getjId() {
        return jId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column judge.j_id
     *
     * @param jId the value for judge.j_id
     *
     * @mbg.generated
     */
    public void setjId(Integer jId) {
        this.jId = jId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column judge.j_endTime
     *
     * @return the value of judge.j_endTime
     *
     * @mbg.generated
     */
    public Date getjEndtime() {
        return jEndtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column judge.j_endTime
     *
     * @param jEndtime the value for judge.j_endTime
     *
     * @mbg.generated
     */
    public void setjEndtime(Date jEndtime) {
        this.jEndtime = jEndtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column judge.p_id
     *
     * @return the value of judge.p_id
     *
     * @mbg.generated
     */
    public Integer getpId() {
        return pId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column judge.p_id
     *
     * @param pId the value for judge.p_id
     *
     * @mbg.generated
     */
    public void setpId(Integer pId) {
        this.pId = pId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column judge.u_id
     *
     * @return the value of judge.u_id
     *
     * @mbg.generated
     */
    public Integer getuId() {
        return uId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column judge.u_id
     *
     * @param uId the value for judge.u_id
     *
     * @mbg.generated
     */
    public void setuId(Integer uId) {
        this.uId = uId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column judge.j_score
     *
     * @return the value of judge.j_score
     *
     * @mbg.generated
     */
    public BigDecimal getjScore() {
        return jScore;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column judge.j_score
     *
     * @param jScore the value for judge.j_score
     *
     * @mbg.generated
     */
    public void setjScore(BigDecimal jScore) {
        this.jScore = jScore;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column judge.j_comment
     *
     * @return the value of judge.j_comment
     *
     * @mbg.generated
     */
    public String getjComment() {
        return jComment;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column judge.j_comment
     *
     * @param jComment the value for judge.j_comment
     *
     * @mbg.generated
     */
    public void setjComment(String jComment) {
        this.jComment = jComment == null ? null : jComment.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table judge
     *
     * @mbg.generated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", jId=").append(jId);
        sb.append(", jEndtime=").append(jEndtime);
        sb.append(", pId=").append(pId);
        sb.append(", uId=").append(uId);
        sb.append(", jScore=").append(jScore);
        sb.append(", jComment=").append(jComment);
        sb.append("]");
        return sb.toString();
    }
}