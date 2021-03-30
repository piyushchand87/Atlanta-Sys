package com.test.atlanta_sys.RETROFIT.Network;



import com.test.atlanta_sys.RETROFIT.Modal.Users;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {
    @GET("users")
    Call<List<Users>> getAllUsers();
}
