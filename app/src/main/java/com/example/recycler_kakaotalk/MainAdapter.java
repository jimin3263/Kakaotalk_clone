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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.CustomViewHolder> {

    private ArrayList<MainData> list;
    public MainAdapter(ArrayList<MainData> list){
        this.list = list;
    }
    public static final int REQUEST_CODE = 101;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @NonNull
    @Override
    public MainAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        CustomViewHolder holder = new CustomViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, final int position) {
        Glide.with(holder.itemView)
                .load(list.get(position).getIv_profile())
                .circleCrop()
                .into(holder.iv_profile);

        holder.iv_name.setText(list.get(position).getIv_name());
        holder.iv_message.setText(list.get(position).getIv_message());

        //여기서 다음 장면으로 넘어가기
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ProfileActivity.class);
                intent.putExtra( "message" , list.get(position).getIv_message());
                intent.putExtra( "name" ,list.get(position).getIv_name());
                intent.putExtra( "uri", list.get(position).getIv_profile());

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
            this.iv_profile = itemView.findViewById(R.id.iv_profile);
            this.iv_name = itemView.findViewById(R.id.iv_name);
            this.iv_message = itemView.findViewById(R.id.iv_message);
        }
    }

}

