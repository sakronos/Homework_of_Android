package com.example.RegisterDemo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
        TextView leftMsg;
        TextView rightMsg;
        public ViewHolder(View view){


            super(view);
            msgView = view;
            leftLayout = (LinearLayout)view.findViewById(R.id.left_layout);
            rightLayout = (LinearLayout) view.findViewById(R.id.right_layout);
            leftMsg = (TextView)view.findViewById(R.id.left_msg);
            rightMsg= (TextView)view.findViewById(R.id.right_msg);
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
            holder.leftMsg.setText(msg.getContent());
        }else if(msg.getType()==Msg.TYPE_SENT){
            holder.leftLayout.setVisibility(View.GONE);
            holder.rightLayout.setVisibility(View.VISIBLE);
            holder.rightMsg.setText(msg.getContent());
        }
    }

    @Override
    public int getItemCount() {
        return mMsgList.size();
    }
}
