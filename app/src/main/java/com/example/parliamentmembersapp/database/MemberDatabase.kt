package com.example.parliamentmembersapp.database

/**
 * Member Database class
 * by An Huynh
 * on 30/9/2021
 */
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.parliamentmembersapp.MyApp

@Database(entities = [MemberOfParliament::class], version = 1, exportSchema = false)
abstract class MemberDatabase: RoomDatabase() {
    abstract val memberDatabaseDao: MemberOfParliamentDao

    companion object {
        @Volatile
        private var INSTANCE: MemberDatabase? = null

        fun getInstance(): MemberDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        MyApp.appContext,
                        MemberDatabase::class.java,
                        "member_database"
                    ).fallbackToDestructiveMigration().build()
                    INSTANCE = instance
                }

                return instance
            }
        }
    }
}