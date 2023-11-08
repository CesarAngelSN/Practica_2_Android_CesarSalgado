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
    private val password: String
) {
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
