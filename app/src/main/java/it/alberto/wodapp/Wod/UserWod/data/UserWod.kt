package it.alberto.wodapp.Wod.UserWod.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
@Entity(tableName = "user_wod_table")
data class UserWod(
        @PrimaryKey(autoGenerate = true)
        val id: Int,
        val name: String,
        val type: String,
        val description: String,
        val date:String
) : Parcelable