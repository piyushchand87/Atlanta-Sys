package com.test.atlanta_sys.RETROFIT.ViewModal;

import android.app.Application;

import com.test.atlanta_sys.RETROFIT.Modal.Users;
import com.test.atlanta_sys.RETROFIT.Repository.UsersRespository;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class UsersViewModal extends AndroidViewModel {

    private UsersRespository usersRespository;
    private LiveData<List<Users>> getAllUsers;

    public UsersViewModal(@NonNull Application application) {
        super(application);
        usersRespository=new UsersRespository(application);
        getAllUsers=usersRespository.getAllUsers();
    }

    public void insert(List<Users> list)
    {
        usersRespository.insert(list);
    }

    public LiveData<List<Users>> getAllUsers()
    {
        return getAllUsers;
    }

}
