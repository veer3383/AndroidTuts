package com.mes.tuts.firebase;

import java.util.Date;

/**
 * Created by Hp on 8/7/2017.
 */

public class MessageModel {

    private String msgText;
    private String msgUser;
    private long msgTime;

    MessageModel(String msgText, String msgUser) {
        this.msgText = msgText;
        this.msgUser = msgUser;

        msgTime = new Date().getTime();
    }

    MessageModel() {

    }

    public long getMsgTime() {
        return msgTime;
    }

    public void setMsgTime(long msgTime) {
        this.msgTime = msgTime;
    }


    public String getMsgUser() {
        return msgUser;
    }

    public void setMsgUser(String msgUser) {
        this.msgUser = msgUser;
    }

    public String getMsgText() {

        return msgText;
    }

    public void setMsgText(String msgText) {
        this.msgText = msgText;
    }
}
