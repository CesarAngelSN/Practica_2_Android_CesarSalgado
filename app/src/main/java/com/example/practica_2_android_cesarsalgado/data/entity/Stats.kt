package com.example.practica_2_android_cesarsalgado.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Stats(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "correct answers")
    val totalCorrectAnswers: Int,
    @ColumnInfo(name = "icorrect answers")
    val totalIncorrectAnswers: Int,
    @ColumnInfo(name = "correct answers percentage")
    val correctAnswersPercentage: Int,
    @ColumnInfo(name = "incorrect answers percentage")
    val incorrectAnswersPercentage: Int,
    @ColumnInfo(name = "number of clicks")
    val numberOfClicks: Int
)
