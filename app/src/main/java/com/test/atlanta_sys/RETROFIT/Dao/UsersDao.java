package com.test.atlanta_sys.RETROFIT.Dao;

import com.test.atlanta_sys.RETROFIT.Modal.Users;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;


import java.util.List;

@Dao
public interface UsersDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<Users> usersList);

    @Query("SELECT * FROM users")
    LiveData<List<Users>> getAllUsers();

    @Query("DELETE FROM users")
    void deleteAll();

    @Query("SELECT * FROM users WHERE name LIKE :dealText")
    public LiveData<List<Users>> getDealsList(String dealText);
}
