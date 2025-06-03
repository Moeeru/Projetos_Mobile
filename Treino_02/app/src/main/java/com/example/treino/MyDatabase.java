package com.example.treino;

import androidx.room.Database;
import androidx.room.RoomDatabase;
@Database(entities = {Gasto.class}, version = 1)
public abstract class MyDatabase extends RoomDatabase {

    public abstract GastoDAO gastoDAO();
}
