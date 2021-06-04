package com.example.studentbudy;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyOrganizationAdapter extends RecyclerView.Adapter<MyOrganizationAdapter.myviewholder>
{
    ArrayList<model> dataholder;
    private RecyclerViewClickInterface recyclerViewClickInterface;

    public MyOrganizationAdapter(ArrayList<model> dataholder,RecyclerViewClickInterface recyclerViewClickInterface )
    {
        this.dataholder = dataholder;
        this.recyclerViewClickInterface = recyclerViewClickInterface;
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.orgpreview,parent,false);
        return new myviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myviewholder holder, int position)
    {
        holder.name.setText(dataholder.get(position).getName());
        holder.type.setText(dataholder.get(position).getType());
        holder.contact.setText(dataholder.get(position).getContact());
        holder.email.setText(dataholder.get(position).getEmail());
        holder.address.setText(dataholder.get(position).getAddress());
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
        TextView name,type,contact,email,address;
        ImageView imageView;

        public myviewholder(@NonNull View itemView)
        {
            super(itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    recyclerViewClickInterface.onItemClick(dataholder.get(getAdapterPosition()).getId());
                }
            });
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    recyclerViewClickInterface.onItemClick(dataholder.get(getAdapterPosition()).getId());
                    return true;
                }
            });
            name=(TextView)itemView.findViewById(R.id.orgName);
            type=(TextView)itemView.findViewById(R.id.o_type);
            contact=(TextView)itemView.findViewById(R.id.orgContact);
            email=(TextView)itemView.findViewById(R.id.orgEmail);
            address=(TextView)itemView.findViewById(R.id.orgAddress);
            imageView=(ImageView) itemView.findViewById(R.id.OrgImg);
        }
    }

}
