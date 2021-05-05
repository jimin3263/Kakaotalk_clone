package com.example.recycler_kakaotalk;

import android.content.Intent;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recycler_kakaotalk.Frag2;
import com.example.recycler_kakaotalk.R;

import java.util.ArrayList;

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.ViewHolder> {
    ArrayList<Person> charList = new ArrayList<Person>();
    public PersonAdapter(ArrayList<Person> cDataList) { this.charList = cDataList; }

    public void addItem(Person item) {
        charList.add(item);
    }

    public void setItems(ArrayList<Person> items) {
        this.charList = items;
    }

    public Person getItem(int position) {
        return charList.get(position);
    }

    public void setItem(int position, Person item) {
        charList.set(position, item);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        //View itemView = inflater.inflate(R.layout.chat_list, parent, false);
        View itemview = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_list, parent, false);
        ViewHolder holder = new ViewHolder(itemview);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.picture.setImageResource(charList.get(position).getPicture());
        holder.name.setText(charList.get(position).getName());
        holder.message.setText(charList.get(position).getMessage());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //프래그먼트로 인텐트 이용해서 이동하는거 구현
                Intent intent = new Intent(v.getContext(), Frag2.class);
                intent.setFlags(intent.FLAG_ACTIVITY_NEW_TASK);
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    //public int getItemCount() {return charList.size();}
    public int getItemCount() {
        return (null != charList ? charList.size() : 0);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        protected ImageView picture;
        protected TextView name;
        protected TextView message;
        //TextView textView;
        //TextView textView2;

        public ViewHolder(View itemView) {
            super(itemView);
            this.picture = (ImageView)itemView.findViewById(R.id.chatPicture);
            this.picture.setBackground(new ShapeDrawable(new OvalShape()));
            //this.picture.setClipToOutline(true);

            this.name = (TextView)itemView.findViewById(R.id.chatName);
            this.message = (TextView)itemView.findViewById(R.id.recentChat);
            //textView = itemView.findViewById(R.id.textView);
            //textView2 = itemView.findViewById(R.id.textView2);
        }

        //public void setItem(Person item) {
        //textView.setText(item.getName());
        //textView2.setText(item.getMobile());
        //}
    }
}