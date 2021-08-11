package com.zhangkai.www.xuankesystem.domain;

public class Stu_Cur {
    private int s_id;
    private int c_id;

    @Override
    public String toString() {
        return "Stu_Cur{" +
                "s_id=" + s_id +
                ", c_id=" + c_id +
                '}';
    }

    public int getS_id() {
        return s_id;
    }

    public void setS_id(int s_id) {
        this.s_id = s_id;
    }

    public int getC_id() {
        return c_id;
    }

    public void setC_id(int c_id) {
        this.c_id = c_id;
    }
}
