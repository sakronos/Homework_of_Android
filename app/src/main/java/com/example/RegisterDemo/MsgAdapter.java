package com.example.RegisterDemo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MsgAdapter extends RecyclerView.Adapter<MsgAdapter.ViewHolder> {
    private List<Msg> mMsgList;

    static class ViewHolder extends RecyclerView.ViewHolder{

        View msgView;
        LinearLayout leftLayout;
        LinearLayout rightLayout;
        LinearLayout left_img_layout;
        LinearLayout right_img_layout;
        TextView leftMsg;
        TextView rightMsg;
        ImageView left_img;
        ImageView right_img;

        public ViewHolder(View view){


            super(view);
            msgView = view;
            leftLayout = (LinearLayout)view.findViewById(R.id.left_layout);
            rightLayout = (LinearLayout) view.findViewById(R.id.right_layout);
            left_img_layout = (LinearLayout)view.findViewById(R.id.left_img_layout);
            right_img_layout = (LinearLayout)view.findViewById(R.id.right_img_layout);
            leftMsg = (TextView)view.findViewById(R.id.left_msg);
            rightMsg= (TextView)view.findViewById(R.id.right_msg);
            left_img = (ImageView)view.findViewById(R.id.left_img);
            right_img = (ImageView)view.findViewById(R.id.right_img);
        }
    }

    public MsgAdapter(List<Msg> msgList){
        mMsgList=msgList;
    }


    @NonNull
    @Override
    public MsgAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.msg_item,parent,false);
        final  ViewHolder holder = new ViewHolder(view);
        holder.leftMsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Msg msg=mMsgList.get(position);
                Toast.makeText(v.getContext(),"you clicked RECEIVED",Toast.LENGTH_SHORT).show();
            }
        });
        holder.rightMsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Msg msg=mMsgList.get(position);
                Toast.makeText(v.getContext(),"you clicked SENT",Toast.LENGTH_SHORT).show();
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MsgAdapter.ViewHolder holder, int position) {
        Msg msg =mMsgList.get(position);
        if (msg.getType()==Msg.TYPE_RECEIVER){
            holder.leftLayout.setVisibility(View.VISIBLE);
            holder.rightLayout.setVisibility(View.GONE);
            holder.right_img_layout.setVisibility(View.GONE);
            holder.left_img_layout.setVisibility(View.GONE);

            holder.leftMsg.setText(msg.getContent());
        }else if(msg.getType()==Msg.TYPE_SENT){
            holder.leftLayout.setVisibility(View.GONE);
            holder.rightLayout.setVisibility(View.VISIBLE);
            holder.right_img_layout.setVisibility(View.GONE);
            holder.left_img_layout.setVisibility(View.GONE);
            holder.rightMsg.setText(msg.getContent());

        }else if (msg.getType()==Msg.TYPE_IMG_RECEIVED){
            holder.leftLayout.setVisibility(View.GONE);
            holder.rightLayout.setVisibility(View.GONE);
            holder.right_img_layout.setVisibility(View.GONE);
            holder.left_img_layout.setVisibility(View.VISIBLE);
            holder.left_img.setImageResource(msg.getImageId());
        }else if (msg.getType()==Msg.TYPE_IMG_SENT){
            holder.leftLayout.setVisibility(View.GONE);
            holder.rightLayout.setVisibility(View.GONE);
            holder.right_img_layout.setVisibility(View.VISIBLE);
            holder.left_img_layout.setVisibility(View.GONE);
            holder.right_img.setImageResource(msg.getImageId());
        }
    }

    @Override
    public int getItemCount() {
        return mMsgList.size();
    }
}
