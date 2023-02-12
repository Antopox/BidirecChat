package com.example.chat_bidireccional.net;

import android.util.Log;

import com.example.chat_bidireccional.controllers.MainActivity;
import com.example.chat_bidireccional.models.Message;
import com.example.chat_bidireccional.models.Pack;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client implements Runnable{

    Socket cs;
    Pack p;
    ObjectOutputStream oos;

    public Client(Pack p){
      this.p = p;
    }

    @Override
    public void run() {
        try{
            cs = new Socket(p.getIp(), 1500);
            Log.d("cliente", "Conexion al servidor");
            oos = new ObjectOutputStream(cs.getOutputStream());
            oos.writeObject(this.p);
            Message aux = new Message("", p.getMessage());
            aux.save();
            MainActivity.messages.add(aux);
            Log.d("cliente", "msg enviado");
            oos.close();
            cs.close();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
