package com.example.demo.aop;

import java.io.Serializable;
import java.util.Date;

/**
 * (PaidFunctionStatistic)实体类
 *
 * @author makejava
 * @since 2020-07-10 13:47:32
 */
public class LogDetail implements Serializable {

    private String input;

    private String output;

    private Date callStartTime;

    private Date callEndTime;

    private Date createTime;

    private Date updateTime;

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public Date getCallStartTime() {
        return callStartTime;
    }

    public void setCallStartTime(Date callStartTime) {
        this.callStartTime = callStartTime;
    }

    public Date getCallEndTime() {
        return callEndTime;
    }

    public void setCallEndTime(Date callEndTime) {
        this.callEndTime = callEndTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}