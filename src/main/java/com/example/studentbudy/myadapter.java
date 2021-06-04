package com.example.studentbudy;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.ObjectStreamField;
import java.util.ArrayList;

public class myadapter extends RecyclerView.Adapter<myadapter.myviewholder> implements Filterable
{
    ArrayList<model> dataholder;
    int id;
    ArrayList<model> backup;

    private RecyclerViewClickInterface recyclerViewClickInterface;
    public myadapter(ArrayList<model> dataholder,RecyclerViewClickInterface recyclerViewClickInterface )
    {
        this.dataholder = dataholder;
        backup=new ArrayList<>(dataholder);
        this.recyclerViewClickInterface = recyclerViewClickInterface;

    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerow,parent,false);
        return new myviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myviewholder holder, int position)
    {
        holder.dname.setText(dataholder.get(position).getName());
        holder.dcontact.setText(dataholder.get(position).getContact());
        holder.daddress.setText(dataholder.get(position).getAddress());
        holder.demail.setText(dataholder.get(position).getEmail());
        holder.dtype.setText(dataholder.get(position).getType());
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

    @Override
    public Filter getFilter() {

        return filter;
    }
Filter filter = new Filter() {
    @Override
    protected FilterResults performFiltering(CharSequence constraint) {
        ArrayList<model> filtereddata=new ArrayList<>();
        ObjectStreamField keyword;
        if(constraint.toString().isEmpty())
            filtereddata.addAll(dataholder);
        else
        {
            for(model obj : backup)
            {
                if(obj.getName().toString().toLowerCase().contains(constraint.toString().toLowerCase()) || obj.getType().toString().toLowerCase().contains(constraint.toString().toLowerCase()))
                    filtereddata.add(obj);
            }
        }

        FilterResults results=new FilterResults();
        results.values=filtereddata;
        return results;
    }

    @Override
    protected void publishResults(CharSequence constraint, FilterResults results)
    {
        dataholder.clear();
        dataholder.addAll((ArrayList<model>)results.values);
        notifyDataSetChanged();
    }
};


class myviewholder extends RecyclerView.ViewHolder
    {
        TextView dname,dcontact,demail,dtype,daddress;
        ImageView imageView;

        public myviewholder(@NonNull View itemView)
        {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    recyclerViewClickInterface.onItemClick(getAdapterPosition());
                    recyclerViewClickInterface.onItemClick(dataholder.get(getAdapterPosition()).getId());
                }
            });
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    recyclerViewClickInterface.onLongItemClick(getAdapterPosition());
                    return true;
                }
            });
            dname=(TextView)itemView.findViewById(R.id.name);
            dcontact=(TextView)itemView.findViewById(R.id.o_contact);
            demail=(TextView)itemView.findViewById(R.id.o_email);
            dtype=(TextView)itemView.findViewById(R.id.type);
            daddress=(TextView)itemView.findViewById(R.id.o_address);
            imageView=(ImageView) itemView.findViewById(R.id.image);
        }
    }

}
