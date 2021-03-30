package com.test.atlanta_sys.RETROFIT.Database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.test.atlanta_sys.RETROFIT.Dao.UsersDao;
import com.test.atlanta_sys.RETROFIT.Modal.Users;

@Database(entities = {Users.class}, version = 2)
public abstract class UsersDatabase extends RoomDatabase {
    private static final String DATABASE_NAME="UsersDatabase";

    public abstract UsersDao usersDao();

    private static volatile UsersDatabase INSTANCE;

    public static UsersDatabase getInstance(Context context){
        if(INSTANCE == null)
        {
            synchronized (UsersDatabase.class){
                if(INSTANCE == null)
                {
                    INSTANCE= Room.databaseBuilder(context, UsersDatabase.class,
                            DATABASE_NAME)
                            .addCallback(callback)
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    static Callback callback=new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateAsynTask(INSTANCE);
        }
    };
    public static class PopulateAsynTask extends AsyncTask<Void,Void,Void>
    {
        private UsersDao usersDao;
        PopulateAsynTask(UsersDatabase usersDatabase)
        {
            usersDao=usersDatabase.usersDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            usersDao.deleteAll();
            return null;
        }
    }
}
