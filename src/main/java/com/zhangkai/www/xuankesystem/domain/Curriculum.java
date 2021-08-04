package com.zhangkai.www.xuankesystem.domain;

import java.util.Date;

public class Curriculum {
    private int id;
    private int credit;
    private String t_name;
    private String c_name;
    private int limitnumber;
    private String detailtime;//注意这里用的是util的Date
    private  int currentnumber;
    private Date starttime;
    private Date endtime;

    @Override
    public String toString() {
        return "Curriculum{" +
                "id=" + id +
                ", credit=" + credit +
                ", limitnumber=" + limitnumber +
                ", currentnumber=" + currentnumber +
                ", t_name='" + t_name + '\'' +
                ", c_name='" + c_name + '\'' +
                ", detailtime='" + detailtime + '\'' +
                ", starttime=" + starttime +
                ", endtime=" + endtime +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public int getLimitnumber() {
        return limitnumber;
    }

    public void setLimitnumber(int limitnumber) {
        this.limitnumber = limitnumber;
    }

    public int getCurrentnumber() {
        return currentnumber;
    }

    public void setCurrentnumber(int currentnumber) {
        this.currentnumber = currentnumber;
    }

    public String getT_name() {
        return t_name;
    }

    public void setT_name(String t_name) {
        this.t_name = t_name;
    }

    public String getC_name() {
        return c_name;
    }

    public void setC_name(String c_name) {
        this.c_name = c_name;
    }

    public String getDetailtime() {
        return detailtime;
    }

    public void setDetailtime(String detailtime) {
        this.detailtime = detailtime;
    }

    public Date getStarttime() {
        return starttime;
    }

    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }
    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }
}
