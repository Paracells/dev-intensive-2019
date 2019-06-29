package ru.skillbranch.devintensive.models

import ru.skillbranch.devintensive.utils.Utils
import java.util.*

//TODO



/*
Реализуй паттерн Builder для класса User.
User.Builder().id(s)
.firstName(s)
.lastName(s)
.avatar(s)
.rating(n)
.respect(n)
.lastVisit(d)
.isOnline(b)
.build() должен вернуть объект User
 */
data class User(
    val id: String,
    var firstName: String?,
    var lastName: String?,
    var avatar: String?,
    var rating: Int = 0,
    var respect: Int = 0,
    val lastVisit: Date? = null,
    var isOnline: Boolean = false
) {
    constructor(id: String, firstName: String?, lastName: String?) : this(
        id = id,
        firstName = firstName,
        lastName = lastName,
        avatar = null
    )



    companion object Factory {
        private var lastId = -1
        fun makeUser(fullName: String): User {
            lastId++
            val (firstName, lastName) = Utils.parseFullName(fullName)
            return User(id = "$lastId", firstName = firstName, lastName = lastName)


        }
    }

}