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
import com.example.myret.Pager_Messages;
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
        Sqlite sqlite = new Sqlite(context);
        Msgs msgs = msgsList.get(position);
        holder.txt_msg.setText(msgs.getMsgDescription());
        int titleId = msgs.getTypeDescription();
        String titleDesc = sqlite.getMsgTitleByTitleID(titleId);
        holder.txt_title.setText(titleDesc);

        holder.txt_msg.setTextColor(context.getResources().getColor(R.color.colorBlue));
        if ((position% 2) == 0) {
            // number is even
        } else {
            // number is odd
            holder.txt_msg.setTextColor(context.getResources().getColor(R.color.colorRed));
        }

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, Pager_Messages.class);

                final Msgs msgs=msgsList.get(position);
                i.putExtra("titleID", msgs.getTypeDescription());
                i.putExtra("pos",position);
                i.putExtra("msgID",msgs.getMsgID());
                i.putExtra("origPos",msgs.getOrigPos());
                i.putExtra("newMsg",msgs.getNewMsg());
                i.putExtra("sourceIsFav",true);
                context.startActivity(i);
            }
        });



        if (sqlite.getIFMsgIsFav(msgs) == 0) {
            holder.img_fav.setImageResource(R.drawable.nf);

        } else {
            holder.img_fav.setImageResource(R.drawable.f);
        }

        holder.img_fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sqlite.getIFMsgIsFav(msgs) == 0) {
                    holder.img_fav.setImageResource(R.drawable.f);
                    sqlite.changeFav(msgs, 1);
                    Toast.makeText(context, "تم الإضافة إلى المفضلة", Toast.LENGTH_LONG).show();
                    msgsList.remove(position);
                    notifyDataSetChanged();
                } else {
                    holder.img_fav.setImageResource(R.drawable.nf);
                    sqlite.changeFav(msgs, 0);
                    Toast.makeText(context, "تم الإزالة من المفضلة", Toast.LENGTH_LONG).show();
                    msgsList.remove(position);
                    notifyDataSetChanged();
                }
            }
        });

        if (msgs.getNewMsg() == 0) {
            holder.img_new.setVisibility(View.INVISIBLE);

        } else {
            holder.img_new.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return msgsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txt_msg ,txt_title;
        CardView cardView;
        ImageView img_fav,img_new;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_msg=itemView.findViewById(R.id.tvMsgada);
            txt_title=itemView.findViewById(R.id.tvTitleada);
            cardView=itemView.findViewById(R.id.card_msgs);
            img_fav=itemView.findViewById(R.id.favada);
            img_new=itemView.findViewById(R.id.new_Msg);
        }
    }
}
