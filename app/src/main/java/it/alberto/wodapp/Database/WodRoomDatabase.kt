package it.alberto.wodapp.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import it.alberto.wodapp.Wod.BaseWod.data.BaseWod
import it.alberto.wodapp.Wod.BaseWod.data.BaseWodDao
import it.alberto.wodapp.Wod.UserWod.data.UserWod
import it.alberto.wodapp.Wod.UserWod.data.UserWodDao
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized


@Database(entities = [UserWod::class, BaseWod::class], version = 4, exportSchema = false)
abstract class WodRoomDatabase  : RoomDatabase (){

    abstract fun userWodDao(): UserWodDao
    abstract fun baseWodDao(): BaseWodDao

    companion object{
        @Volatile
        private var INSTANCE: WodRoomDatabase? = null

        @InternalCoroutinesApi
        fun getDatabase(context: Context) : WodRoomDatabase {

            val tempInstance = INSTANCE
            if(tempInstance!=null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    WodRoomDatabase::class.java,
                    "wod_room_database"
                ).allowMainThreadQueries()
                        .fallbackToDestructiveMigration()
                        .createFromAsset("wod_room_database")
                        .build()

                INSTANCE = instance
                return instance
            }
        }
    }
}


