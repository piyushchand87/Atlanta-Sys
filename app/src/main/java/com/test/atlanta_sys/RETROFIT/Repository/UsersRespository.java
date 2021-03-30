package com.test.atlanta_sys.RETROFIT.Repository;

import android.app.Application;
import android.content.Context;
import android.os.AsyncTask;

import com.test.atlanta_sys.RETROFIT.Dao.UsersDao;
import com.test.atlanta_sys.RETROFIT.Database.UsersDatabase;
import com.test.atlanta_sys.RETROFIT.Modal.Users;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;


import java.util.List;

public class UsersRespository {
    private static UsersDatabase database;
    private LiveData<List<Users>> getAllUsers;
    private LiveData<List<Users>> getSearchUsers;
    private static final Object LOCK = new Object();

    public UsersRespository(Application application)
    {
        database        =   UsersDatabase.getInstance(application);
        getAllUsers     =   database.usersDao().getAllUsers();
    }
    public synchronized static UsersDatabase getDealsDatabase(Context context) {
        if (database == null) {
            synchronized (LOCK) {
                if (database == null) {
                    database = Room.databaseBuilder(context,
                            UsersDatabase.class, "users")
                            .fallbackToDestructiveMigration()
                            .addCallback(callback).build();

                }
            }
        }
        return database;
    }
    public UsersDao getUserDao(Context context){
        return getDealsDatabase(context).usersDao();
    }

    public LiveData<List<Users>>getDealsListInfo(Context context, String query){
        return getUserDao(context).getDealsList(query);
    }
    public void insert(List<Users> usersList){
     new InsertAsynTask(database).execute(usersList);
    }

    public LiveData<List<Users>> getAllUsers()
    {
        return getAllUsers;
    }


   static class InsertAsynTask extends AsyncTask<List<Users>,Void,Void>{
        private UsersDao usersDao;
         InsertAsynTask(UsersDatabase usersDatabase)
         {
             usersDao=usersDatabase.usersDao();
         }
        @Override
        protected Void doInBackground(List<Users>... lists) {
            usersDao.insert(lists[0]);
            return null;
        }
    }

    static RoomDatabase.Callback callback=new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

        }
    };

}
