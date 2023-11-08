package com.example.practica_2_android_cesarsalgado.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.practica_2_android_cesarsalgado.data.dao.QuestionDao
import com.example.practica_2_android_cesarsalgado.data.dao.UserDao
import com.example.practica_2_android_cesarsalgado.data.entity.Question
import com.example.practica_2_android_cesarsalgado.data.entity.User

@Database(
    version = 1,
    entities = [User::class, Question::class]
)
abstract class AppDatabase: RoomDatabase() {
    abstract fun getUserDao(): UserDao
    abstract fun getQuestionDao(): QuestionDao
}