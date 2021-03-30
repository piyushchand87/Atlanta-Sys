package com.test.atlanta_sys.RETROFIT;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;


import com.test.atlanta_sys.R;
import com.test.atlanta_sys.RETROFIT.Adapter.UsersAdapter;
import com.test.atlanta_sys.RETROFIT.Modal.Users;
import com.test.atlanta_sys.RETROFIT.Network.Retrofit;
import com.test.atlanta_sys.RETROFIT.Repository.UsersRespository;
import com.test.atlanta_sys.RETROFIT.ViewModal.UsersViewModal;
import com.test.atlanta_sys.Selection_Class;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private UsersViewModal usersViewModal;

    private RecyclerView recyclerView;
    private List<Users> usersList;
    private UsersRespository usersRespository;
   private UsersAdapter usersAdapter;
    EditText EditText_SearUser;
    String searchText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        usersRespository=new UsersRespository(getApplication());
        usersList=new ArrayList<>();
       usersAdapter=new UsersAdapter(this,usersList);

        usersViewModal=new ViewModelProvider(this).get(UsersViewModal.class);
        networkRequest();
        usersViewModal.getAllUsers().observe(this, new Observer<List<Users>>() {
            @Override
            public void onChanged(List<Users> usersList) {
                recyclerView.setAdapter(usersAdapter);
                usersAdapter.getAllUsers(usersList);

                Log.d("main", "onChanged: "+usersList);
            }
        });

        EditText_SearUser = (EditText) findViewById(R.id.EditText_SearUser);
        EditText_SearUser.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                getDealsFromDb(EditText_SearUser.getText().toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    private void networkRequest() {

        Retrofit retrofit=new Retrofit();
        Call<List<Users>> call=retrofit.api.getAllUsers();
        call.enqueue(new Callback<List<Users>>() {
            @Override
            public void onResponse(Call<List<Users>> call, Response<List<Users>> response) {
                if(response.isSuccessful())
                {
                    usersRespository.insert(response.body());
                    Log.d("main", "onResponse: "+response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Users>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "something went wrong...", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void getDealsFromDb(String searchText) {
        searchText = "%"+searchText+"%";
        usersRespository.getDealsListInfo(MainActivity.this, searchText)
                .observe(MainActivity.this, new Observer<List<Users>>() {
                    @Override
                    public void onChanged(List<Users> users) {
                        usersAdapter = new UsersAdapter(
                                MainActivity.this, users);
                        recyclerView.setAdapter(usersAdapter);
//                        usersAdapter.getAllUsers(users);
                    }
                });
    }

    @Override
    public void onBackPressed() {
        Intent in = new Intent(this, Selection_Class.class);
        startActivity(in);
        finish();
    }
}
