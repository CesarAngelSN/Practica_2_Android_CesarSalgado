package com.example.practica_2_android_cesarsalgado.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity
data class Question(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private var id: Int,
    @ColumnInfo(name = "description")
    private var description: String,
    @ColumnInfo(name = "type")
    private var type: String,
    @ColumnInfo(name = "answers")
    private var answers: String,
    @ColumnInfo(name = "correctAnswerIndex")
    private var correctAnswerIndex: Int,
    @ColumnInfo(name = "image")
    private var image: ByteArray?
) {
    fun getId(): Int {
        return id
    }
    fun getDescription(): String {
        return description
    }
    fun getType(): String {
        return type
    }
    fun getAnswers(): String {
        return answers
    }
    fun getCorrectAnswerIndex(): Int {
        return correctAnswerIndex
    }
    fun getCorrectAnswer(): String {
        return answers.split(Regex(";"))[0]
    }
    fun getImage(): ByteArray? {
        return image
    }
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Question

        if (id != other.id) return false
        if (description != other.description) return false
        if (type != other.type) return false
        if (answers != other.answers) return false
        if (correctAnswerIndex != other.correctAnswerIndex) return false
        if (!image.contentEquals(other.image)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + description.hashCode()
        result = 31 * result + type.hashCode()
        result = 31 * result + answers.hashCode()
        result = 31 * result + correctAnswerIndex
        result = 31 * result + image.contentHashCode()
        return result
    }

    override fun toString(): String {
        return "Question with id=$id, description='$description', answers='$answers')"
    }
}
