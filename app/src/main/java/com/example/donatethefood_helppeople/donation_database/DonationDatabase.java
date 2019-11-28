package com.example.donatethefood_helppeople.donation_database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.donatethefood_helppeople.donation_dao.IonformationDao;
import com.example.donatethefood_helppeople.model_class.InformationModel;

@Database(entities = {InformationModel.class},version = 1)
public abstract class DonationDatabase extends RoomDatabase {
    private static DonationDatabase db;
    private static final String DB_NAME = "donation.db";
    public abstract IonformationDao getDonationDao();

    public static DonationDatabase getInstance(Context context){
        if (db != null){
            return db;
        }

        db = Room.databaseBuilder(context.getApplicationContext(), DonationDatabase.class, DB_NAME)
                .allowMainThreadQueries()
                .build();
        return db;
    }
}
