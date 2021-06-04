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

public class myOrgAdapter extends RecyclerView.Adapter<myOrgAdapter.myviewholder>
{
    ArrayList<model> dataholder;
    private RecyclerViewClickInterface recyclerViewClickInterface;

    public myOrgAdapter(ArrayList<model> dataholder)
    {
        this.dataholder = dataholder;
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
        holder.dname.setText(dataholder.get(position).getName());
        holder.dcontact.setText(dataholder.get(position).getContact());
        holder.demail.setText(dataholder.get(position).getEmail());
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
        TextView dname,dcontact,demail;
        ImageView imageView;

        public myviewholder(@NonNull View itemView)
        {
            super(itemView);

            dname=(TextView)itemView.findViewById(R.id.orgName);
            dcontact=(TextView)itemView.findViewById(R.id.o_type);
            demail=(TextView)itemView.findViewById(R.id.orgEmail);
            imageView=(ImageView) itemView.findViewById(R.id.OrgImg);
        }
    }

}
