package com.example.practica_2_android_cesarsalgado.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull


@Entity
data class User(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private val id: Int,
    @ColumnInfo(name = "type")
    private val type: String,
    @ColumnInfo(name = "name")
    private val name: String,
    @ColumnInfo(name = "password")
    private val password: String,
    @ColumnInfo(name = "games played")
    private var gamesPlayed: Int,
    @ColumnInfo(name = "total correct answers")
    private var totalCorrectAnswers: Int
) : Comparable<User>
{
    fun getId(): Int {
        return id
    }
    fun getType(): String {
        return type
    }
    fun getName(): String {
        return name
    }
    fun getPassword(): String {
        return password
    }
    fun getGamesPlayed(): Int {
        return gamesPlayed
    }
    fun getTotalCorrectAnswers(): Int {
        return totalCorrectAnswers
    }
    fun setGamesPlayed(): Unit {
        gamesPlayed++
    }
    fun setTotalCorrectAnswers(): Unit {
        gamesPlayed++
    }

    override fun compareTo(other: User): Int {
        var result = 0
        if (this.getTotalCorrectAnswers() > other.getTotalCorrectAnswers()) {
            result = 1
        }
        else if (this.getTotalCorrectAnswers() < other.getTotalCorrectAnswers()) {
            result = -1
        }
        else {
            result = 0
        }
        return result
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as User

        if (id != other.id) return false
        if (type != other.type) return false
        if (name != other.name) return false
        if (password != other.password) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + type.hashCode()
        result = 31 * result + name.hashCode()
        result = 31 * result + password.hashCode()
        return result
    }

    override fun toString(): String {
        return "User(id=$id, type='$type', name='$name', password='$password')"
    }
}
