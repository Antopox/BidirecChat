package com.example.chat_bidireccional.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chat_bidireccional.R;
import com.example.chat_bidireccional.controllers.MainActivity;
import com.example.chat_bidireccional.models.Message;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecyclerHolder> {

    private ArrayList<Message> messages;

    public RecyclerAdapter(ArrayList<Message> msgs){
            this.messages = msgs;
    }

    @NonNull
    @Override
    public RecyclerAdapter.RecyclerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_message_itemlist,parent, false);
        RecyclerHolder recyclerHolder = new RecyclerHolder(view);
        return recyclerHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerHolder holder, int position) {
        Message m = messages.get(position);

        if (m.getNick().equals("")) {
            holder.local.setText(MainActivity.txtNick.getText());
            holder.msg.setText(m.getMsg() + " ");
            holder.client.setText("");
        }else{
            holder.local.setText("");
            holder.client.setText(m.getNick());
            holder.msg.setText(" " + m.getMsg());
        }

    }

    @Override
    public int getItemCount() {
        if (messages == null) {
            return 0;
        }
        else {
            return messages.size();
        }
    }

    public class RecyclerHolder extends RecyclerView.ViewHolder{

        TextView msg;
        TextView local;
        TextView client;

        public RecyclerHolder(@NonNull View itemView) {
            super(itemView);
            this.client = (TextView) itemView.findViewById(R.id.txtNickDest);
            this.local = (TextView) itemView.findViewById(R.id.txtNickLocal);
            this.msg = (TextView) itemView.findViewById(R.id.txtMensaje);
        }
    }
}
