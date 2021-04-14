package com.ubaya.adv160718006week8.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Todo(
    var title:String,
    var notes:String

)
{
    @PrimaryKey(autoGenerate = true)
    var uuid:Int = 0
}