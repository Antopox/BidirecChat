package com.example.chat_bidireccional.models;

import com.orm.SugarRecord;

public class Message extends SugarRecord<Message> {

    private String nick;
    private String msg;

    public Message(){}
    public Message(String nick,String msg){
        this.msg = msg;
        this.nick = nick;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public boolean equals(Object obj){
        boolean isEqual = false;

        if (obj instanceof Message){
            Message aux = (Message) obj;
            if (this.nick.equals(aux.getNick()) && this.msg.equals(aux.getMsg())){
                isEqual = true;
            }
        }

        return isEqual;
    }
}
