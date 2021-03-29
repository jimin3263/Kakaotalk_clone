package com.example.recycler_kakaotalk;

import android.content.Intent;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.CustomViewHolder> {

    private ArrayList<MainData> list;
    public MainAdapter(ArrayList<MainData> list){
        this.list = list;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @NonNull
    @Override
    public MainAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        CustomViewHolder holder = new CustomViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        holder.iv_profile.setImageResource(list.get(position).getIv_profile());
        holder.iv_name.setText(list.get(position).getIv_name());
        holder.iv_message.setText(list.get(position).getIv_message());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //프래그먼트로 인텐트 이용해서 이동하는거 구현
                Intent intent = new Intent(v.getContext(), Fragment1.class);
                intent.setFlags(intent.FLAG_ACTIVITY_NEW_TASK);
                v.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return (null != list ? list.size() : 0);
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder{

        protected ImageView iv_profile;
        protected TextView iv_name;
        protected TextView iv_message;


        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.iv_profile = (ImageView)itemView.findViewById(R.id.iv_profile);
            this.iv_profile.setBackground(new ShapeDrawable(new OvalShape()));
            this.iv_profile.setClipToOutline(true);

            this.iv_name = (TextView)itemView.findViewById(R.id.iv_name);
            this.iv_message = (TextView)itemView.findViewById(R.id.iv_message);
        }
    }

}

