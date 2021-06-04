package com.example.studentbudy;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class featureAdapter extends RecyclerView.Adapter<featureAdapter.myviewholder>
{
    ArrayList<FeatureModel> dataholder;

    public featureAdapter(ArrayList<FeatureModel> dataholder)
    {
        this.dataholder = dataholder;
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.description,parent,false);
        return new myviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myviewholder holder, int position)
    {
        holder.title.setText(dataholder.get(position).getTitle());
        holder.description.setText(dataholder.get(position).getDescription());
        Bitmap bitmap = convertByteArrayToBitmap(dataholder.get(position).getImage());
        holder.imageView.setImageBitmap(bitmap);
    }
    private Bitmap convertByteArrayToBitmap(byte[] bytes){
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
    }

    @Override
    public int getItemCount() {
        return dataholder.size();
    }


    class myviewholder extends RecyclerView.ViewHolder
    {
        TextView title,description;
        ImageView imageView;

        public myviewholder(@NonNull View itemView)
        {
            super(itemView);
            title=(TextView)itemView.findViewById(R.id.title);
            description=(TextView)itemView.findViewById(R.id.desc);
            imageView=(ImageView) itemView.findViewById(R.id.image);
        }
    }

}
