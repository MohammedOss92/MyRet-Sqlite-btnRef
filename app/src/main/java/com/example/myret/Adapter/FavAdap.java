package com.example.myret.Adapter;

import android.content.Context;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myret.Modal.Msgs;
import com.example.myret.R;
import com.example.myret.Sqlite.Sqlite;

import java.util.ArrayList;
import java.util.List;

public class FavAdap extends RecyclerView.Adapter<FavAdap.ViewHolder> {
    private Context context;
    private List<Msgs> msgsList=new ArrayList<>();

    public FavAdap(Context context, List<Msgs> msgsList) {
        this.context = context;
        this.msgsList = msgsList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_msgs,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Msgs msgs = msgsList.get(position);
        holder.txt_msg.setText(msgs.getMsgDescription());
        holder.txt_new.setText(""+msgs.getNewMsg());

        holder.txt_msg.setTextColor(context.getResources().getColor(R.color.colorBlue));
        if ((position% 2) == 0) {
            // number is even
        } else {
            // number is odd
            holder.txt_msg.setTextColor(context.getResources().getColor(R.color.colorRed));
        }

        Sqlite d = new Sqlite(context);
        if (d.getIFMsgIsFav(msgs) == 0) {
            holder.img_fav.setImageResource(R.mipmap.nf);

        } else {
            holder.img_fav.setImageResource(R.drawable.f);
        }

        holder.img_fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (d.getIFMsgIsFav(msgs) == 0) {
                    holder.img_fav.setImageResource(R.drawable.f);
                    d.changeFav(msgs, 1);
                    Toast.makeText(context, "تم الإضافة إلى المفضلة", Toast.LENGTH_LONG).show();
                    notifyDataSetChanged();
                } else {

                    holder.img_fav.setImageResource(R.mipmap.nf);
                    d.changeFav(msgs, 0);
                    Toast.makeText(context, "تم الإزالة من المفضلة", Toast.LENGTH_LONG).show();
                    msgsList.remove(position);
                    notifyDataSetChanged();
                }

            }
        });

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return msgsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txt_msg ,txt_new;
        CardView cardView;
        ImageView img_fav;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_msg=itemView.findViewById(R.id.tvMsgada);
            txt_new=itemView.findViewById(R.id.newMsg);
            cardView=itemView.findViewById(R.id.card_msgs);
            img_fav=itemView.findViewById(R.id.favada);
        }
    }
}
