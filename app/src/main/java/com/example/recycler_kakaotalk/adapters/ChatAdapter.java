package com.example.recycler_kakaotalk.adapters;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recycler_kakaotalk.R;
import com.example.recycler_kakaotalk.data.ChatData;

import java.text.SimpleDateFormat;
import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.MyViewHolder>{
    private List<ChatData> mDataset;
    private String myName;

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        //public ImageView chat_profile;
        public TextView chat_name;
        public TextView chat_msg;

        public MyViewHolder(View v){
            super(v);
            //chat_profile = v.findViewById(R.id.chat_profile);
            chat_name = v.findViewById(R.id.chat_name);
            chat_msg = v.findViewById(R.id.chat_msg);

            v.setClickable(true);
            v.setEnabled(true);

        }
    }

    public ChatAdapter(List<ChatData>myDataset, Context context, String myName){  //myName: 메세지 오른쪽, 왼쪽 구분을 위함
        mDataset = myDataset;
        this.myName = myName;
    }

    public ChatAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LinearLayout v = (LinearLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.row_chat, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public void onBindViewHolder(MyViewHolder holder, int position){
        ChatData chat = mDataset.get(position);

        //chat.getChat_profile();
        //holder.chat_profile.setImageResource(chat.getChat_profile());
        holder.chat_name.setText(chat.getChat_name());
        holder.chat_msg.setText(chat.getChat_message());

        if (chat.getChat_name().equals(myName)){
            holder.chat_msg.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_END);
            holder.chat_name.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_END);
        }
        else{
            holder.chat_msg.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
            holder.chat_name.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
        }
    }

    public int getItemCount(){
        return mDataset == null ? 0 : mDataset.size();
    }

    public ChatData getChat(int position){
        return mDataset != null ? mDataset.get(position) : null;
    }

    public void addChat(ChatData chat){
        mDataset.add(chat);
        notifyItemInserted(mDataset.size()-1);   // 갱신
    }
}

