package com.example.chat_bidireccional.models;

import java.io.Serializable;

public class Pack implements Serializable {
    String nick;
    String ip;
    String message;

    public Pack(){}

    public Pack(String nick, String ip, String message){
        this.ip = ip;
        this.nick = nick;
        this.message = message;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
