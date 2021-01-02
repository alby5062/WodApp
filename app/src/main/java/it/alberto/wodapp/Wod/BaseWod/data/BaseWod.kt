package it.alberto.wodapp.Wod.BaseWod.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "base_wod_table")
data class BaseWod(
        @PrimaryKey(autoGenerate = true)
        val id: Int,
        val name: String,
        val type: String,
        val description: String
) : Parcelable