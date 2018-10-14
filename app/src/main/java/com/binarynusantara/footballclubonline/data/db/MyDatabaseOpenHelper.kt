package com.binarynusantara.footballclubonline.data.db

import android.annotation.SuppressLint
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*

data class MyDatabaseOpenHelper(val ctx: Context) : ManagedSQLiteOpenHelper(ctx, "FavoriteMatch.db", null, 2){
    companion object {
        @SuppressLint("StaticFieldLeak")
        private var instance: MyDatabaseOpenHelper? = null

        @Synchronized
        fun getInstance(ctx: Context): MyDatabaseOpenHelper{
            if (instance == null) instance = MyDatabaseOpenHelper(ctx.applicationContext)
            return instance as MyDatabaseOpenHelper
        }
    }
    override fun onCreate(db: SQLiteDatabase?) {
        db?.createTable(Favorites.TABLE_FAVORITE, true,
                Favorites.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
                Favorites.EVENT_ID to TEXT + UNIQUE,
                Favorites.EVENT_DATE to TEXT,
                Favorites.HOME_TEAM to TEXT,
                Favorites.HOME_SCORE to TEXT,
                Favorites.AWAY_TEAM to TEXT,
                Favorites.AWAY_SCORE to TEXT,
                Favorites.HOME_TEAM_ID to TEXT,
                Favorites.AWAY_TEAM_ID to TEXT)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.dropTable(Favorites.TABLE_FAVORITE, true)
    }
}
