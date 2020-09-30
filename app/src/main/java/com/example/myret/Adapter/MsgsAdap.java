package com.example.myret.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myret.Modal.Msgs;
import com.example.myret.Pager;
import com.example.myret.R;

import java.util.ArrayList;
import java.util.List;

public class MsgsAdap extends RecyclerView.Adapter<MsgsAdap.ViewHolder> {
    private Context context;
    private List<Msgs> msgsList=new ArrayList<>();

    public MsgsAdap(Context context,List<Msgs>msgsList) {
        this.context = context;
        this.msgsList = msgsList;
    }

    @NonNull
    @Override
    public MsgsAdap.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_msgs,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MsgsAdap.ViewHolder holder, int position) {

        Msgs msgs = msgsList.get(position);
        holder.txt_msg.setText(msgs.getMsgDescription());
        holder.txt_new.setText(""+msgs.getNewMsg());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, Pager.class);
                i.putExtra("TypeDescription",msgs.getTypeDescription());
                i.putExtra("pos",position);
                i.putExtra("msgID",msgs.getMsgID());
                context.startActivity(i);
            }
        });

    }

    public void setAllMsgs(List<Msgs> msgsList) {
        this.msgsList = msgsList;
    }

    @Override
    public int getItemCount() {
        return msgsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txt_msg ,txt_new;
        CardView cardView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_msg=itemView.findViewById(R.id.txt_msg);
            txt_new=itemView.findViewById(R.id.newMsg);
            cardView=itemView.findViewById(R.id.card_msgs);
        }
    }
}
