package com.test.atlanta_sys.RETROFIT.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.test.atlanta_sys.RETROFIT.Modal.Users;
import com.test.atlanta_sys.R;

import java.util.List;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.UsersViewHolder> {

private Context context;
private List<Users> usersList;

    public UsersAdapter(Context context, List<Users> usersList) {
        this.context =  context;
        this.usersList = usersList;
    }

    @NonNull
    @Override
    public UsersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new UsersViewHolder(LayoutInflater.from(parent.getContext())
        .inflate(R.layout.each_roe,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull UsersViewHolder holder, int position) {
      Users users=usersList.get(position);
        holder.tvName.setText("("+users.getId()+""+".)"+users.getName().toString());

        holder.tvEmail.setText(users.getEmail().toString());

        /*holder.tvAdd.setText(users.getAddress().getStreet().toString()+", "+
                users.getAddress().getSuite().toString()+"\n"+
                users.getAddress().getCity().toString()+", "+
                users.getAddress().getZipCode().toString());

        holder.tvGeo.setText("Latitude :- "+users.getAddress().getGeo().getLatitude().toString()+"\n"+"Longitude :- "+
                users.getAddress().getGeo().getLongitude().toString());*/

        holder.tvPhone.setText(users.getPhone().toString());

        holder.tvWeb.setText(users.getWebsite().toString());

        /*holder.tvComp.setText(users.getCompany().getName().toString()+"\n"+
                users.getCompany().getCatchPhrase().toString()+"\n"+this.
                usersList.get(position).getCompany().getBs().toString());*/

    }

    public void getAllUsers(List<Users> usersList)
    {
        this.usersList=usersList;
    }

    @Override
    public int getItemCount() {
        return usersList.size();
    }

    public static class UsersViewHolder extends RecyclerView.ViewHolder{
        TextView tvName, tvEmail, tvAdd, tvPhone, tvGeo, tvWeb, tvComp;

        public UsersViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName  = (TextView)itemView.findViewById(R.id.TV_Name);
            tvEmail = (TextView)itemView.findViewById(R.id.TV_Email);
            tvAdd   = (TextView)itemView.findViewById(R.id.TV_Address);
            tvPhone = (TextView)itemView.findViewById(R.id.TV_Phone);
            tvGeo   = (TextView)itemView.findViewById(R.id.TV_Geo);
            tvWeb   = (TextView)itemView.findViewById(R.id.TV_Website);
            tvComp  = (TextView)itemView.findViewById(R.id.TV_Company);
        }
    }
}
