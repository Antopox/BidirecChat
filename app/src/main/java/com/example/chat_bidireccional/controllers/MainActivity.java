package com.example.chat_bidireccional.controllers;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chat_bidireccional.R;
import com.example.chat_bidireccional.adapters.RecyclerAdapter;
import com.example.chat_bidireccional.models.Message;
import com.example.chat_bidireccional.models.Pack;
import com.example.chat_bidireccional.net.Client;
import com.google.android.material.textfield.TextInputEditText;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity{

    public static ArrayList<Message> messages;

    private TextInputEditText txtWritedMsg;
    public static TextView txtNick;
    private TextInputEditText txtIP;
    private RecyclerView recViewMsgs;
    private Button btSend;
    private Pack pack;
    private String nick, ip, message;

    Client c;
    Server s;
    RecyclerAdapter ra;

    int size;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtWritedMsg = findViewById(R.id.txtWritedMsg);
        txtNick = findViewById(R.id.txtNick);
        txtIP = findViewById(R.id.txtIP);
        recViewMsgs = findViewById(R.id.Msg_RecView);
        btSend = findViewById(R.id.btSend);

        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                .permitNetwork().build());

        Intent i = getIntent();
        String user = i.getStringExtra("User");
        messages = new ArrayList<>();
        messages = (ArrayList<Message>) Message.listAll(Message.class);
        size = messages.size();


        txtNick.setText(user);
        LinearLayoutManager lm = new LinearLayoutManager(this);
        ra = new RecyclerAdapter(messages);
        recViewMsgs.setLayoutManager(lm);
        recViewMsgs.setAdapter(ra);

        btSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                nick = String.valueOf(txtNick.getText());
                ip = String.valueOf(txtIP.getText());
                message = String.valueOf(txtWritedMsg.getText());
                if (ip.equals("")){
                    Toast.makeText(getApplicationContext(), "Por favor itroduce una IP valida", Toast.LENGTH_LONG).show();
                }else{
                    pack = new Pack(nick, ip, message);
                    c = new Client(pack);
                    Thread sendMsg = new Thread(c);
                    sendMsg.start();
                    try {
                        sendMsg.join();
                        ra.notifyDataSetChanged();
                        Log.d("RecyclerAdapter", "Notify data set changed");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Log.d("hilos", "hilo mandar Mensaje iniciado");
                }

            }
        });
        s = new Server();
        Thread MsgListener = new Thread(s);
        MsgListener.start();
        Log.d("hilos", "hilo recibir Mensaje iniciado");

    }

    public class Server implements Runnable{

        ObjectInputStream ois;
        ServerSocket ss;
        Socket cs;

        public Server(){
            try { 
                ss = new ServerSocket(1500);
                ss.setReuseAddress(true);
                Log.d("server", "Server on");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {

            Pack p;
            while(true) {
                try {
                    if (ss != null && !ss.isClosed()) {
                        cs = ss.accept();

                        ois = new ObjectInputStream(cs.getInputStream());
                        p = (Pack) ois.readObject();

                        Message aux = new Message(p.getNick(), p.getMessage());
                        messages.add(aux);
                        aux.save();
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                synchronized (this) {
                                    ra.notifyDataSetChanged();
                                }
                                try {
                                    this.finalize();
                                } catch (Throwable e) {
                                    e.printStackTrace();
                                }
                            }
                        });

                        Log.d("server", "Msg recibido");

                        ois.close();
                        cs.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}