package ru.skillbranch.devintensive.models

import java.util.*

//TODO

/*
Необходимо создать абстрактный класс BaseMessage содержащий сделующие свойства:
val id: String,
val from: User?,
val chat: Chat,
val isIncoming: Boolean = false,
val date: Date = Date()
и абстрактный метод formatMessage() - возвращает строку содержащюю информацию о id сообщения, имени получателя/отправителя, виде сообщения ("получил/отправил") и типе сообщения ("сообщение"/"изображение")

Реализуй паттерн AbstractFactory с методом makeMessage(from, chat, date, type, payload, isIncoming = false)
 принимающий в качесте аргументов пользователя создавшего сообщение, чат к которому относится сообщение, дата сообщения и его тип ("text/image"), полезную нагрузку
Пример:
BaseMessage.makeMessage(user, chat, date, "any text message", "text") //Василий отправил сообщение "any text message" только что
BaseMessage.makeMessage(user, chat, date, "https://anyurl.com", "image",true) //Василий получил изображение "https://anyurl.com" 2 часа назад
 */

abstract class BaseMessage(
    val id: String,
    val from: User?,
    val chat: Chat,
    val isComing: Boolean = false,
    val date: Date = Date()
) {
    abstract fun formatMessage(): String

    companion object AbstractFactory {
        var lastId = -1
        fun makeMessage(
            from: User?,
            chat: Chat,
            date: Date = Date(),
            type: String = "text",
            payload: Any?,
            isComing: Boolean = false
        ): BaseMessage {
            lastId++
            return when (type) {
                "image" -> ImageMessage(
                    "$lastId",
                    from,
                    chat,
                    date = date,
                    image = payload as String
                )
                else -> TextMessage(
                    "$lastId",
                    from,
                    chat,
                    date = date,
                    text = payload as String
                )

            }

        }

    }
}