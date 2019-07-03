package ru.skillbranch.devintensive.extensions

import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.abs


const val SECOND = 1000L
const val MINUTE = 60 * SECOND
const val HOUR = 60 * MINUTE
const val DAY = 24 * HOUR


fun Date.format(pattern: String = "HH:mm:ss dd.MM.yy"): String {

    var dateFormat = SimpleDateFormat(pattern, Locale("ru"))
    return dateFormat.format(this)

}


fun Date.add(value: Int, units: TimeUnits = TimeUnits.SECOND): Date {
    var time = this.time
    time += when (units) {
        TimeUnits.SECOND -> value * SECOND
        TimeUnits.MINUTE -> value * MINUTE
        TimeUnits.HOUR -> value * HOUR
        TimeUnits.DAY -> value * DAY


    }
    this.time = time
    return this

}


enum class TimeUnits {
    SECOND,
    MINUTE,
    HOUR,
    DAY;

    fun plural(value: Long): String {
        return when (this) {
            SECOND -> "$value ${secondsAsWord(value)}"
            MINUTE -> "$value ${minutesAsWord(value)}"
            HOUR -> "$value ${hoursAsWord(value)}"
            DAY -> "$value ${daysAsWord(value)}"
        }
    }
}

//fun TimeUnits.plural(value: Long):String {
//    when (this) {
//        TimeUnits.SECOND -> { //секунДА, секунДЫ, секунД, 1 секунДУ
//           return  "$value ${secondsAsWord(value)}"
//        }
//        TimeUnits.MINUTE -> { //минуТА, минуТЫ, минуТ
//            return  "$value ${minutesAsWord(value)}"
//        }
//        TimeUnits.HOUR -> { //час, часА, часОВ
//            return  "$value ${hoursAsWord(value)}"
//        }
//        TimeUnits.DAY -> { //день, дня, дней
//            return  "$value ${daysAsWord(value)}"
//        }
//
//    }
//    return  "hello"
//}


val prefYear = "бол"
fun Date.humanizeDiff(date: Date = Date()): String {

    var delta = ((time + 500) / 1000 - (date.time + 500) / 1000) * 1000


    var past = true
    var prefix = ""
    var postfix = ""
    if (delta < 0) {
        past = false
        delta = -delta
    }
    if (!past) {
        prefix = ""
        postfix = " назад"
    } else {
        prefix = "через "
        postfix = ""
    }
    var dif = abs(delta)

    val seconds = dif / SECOND
    val minute = dif / MINUTE
    val hours = dif / HOUR
    val days = dif / DAY
    val year = if (days >= 360) 1 else 0

    return when (dif) {
        in 0L..SECOND -> "только что"
        in 2 * SECOND..45 * SECOND -> "${prefix}несколько секунд$postfix"//несколько секунд назад
        in 46 * SECOND..75 * SECOND -> "${prefix}минуту$postfix" //минуту назад
        in 76 * SECOND..45 * MINUTE -> "$prefix$minute ${minutesAsWord(minute)}$postfix" //N минут
        in 46 * MINUTE..75 * MINUTE -> "${prefix}час$postfix" //час назад
        in 76 * MINUTE..22 * HOUR -> "$prefix$hours ${hoursAsWord(hours)}$postfix" //через N часов
        in 23 * HOUR..26 * HOUR -> "${prefix}день$postfix" //день назад
        in 27 * HOUR..360 * DAY -> "$prefix$days ${daysAsWord(days)}$postfix" //N дней назад
        else -> if (!past) "более года назад" else "более чем через год"


    }

}

fun secondsAsWord(value: Long) = when (value.asPlurals) {
    Plurals.ONE -> "секунду"
    Plurals.FEW -> "секунды"
    Plurals.MANY -> "секунд"
    Plurals.OTHER -> "секунду"
}

fun minutesAsWord(value: Long) = when (value.asPlurals) {
    Plurals.ONE -> "минуту"
    Plurals.FEW -> "минуты"
    Plurals.MANY -> "минут"
    Plurals.OTHER -> "минуту"
}

fun hoursAsWord(value: Long) = when (value.asPlurals) {
    Plurals.ONE -> "час"
    Plurals.FEW -> "часа"
    Plurals.MANY -> "часов"
    Plurals.OTHER -> "час"
}

fun daysAsWord(value: Long) = when (value.asPlurals) {
    Plurals.ONE -> "день"
    Plurals.FEW -> "дня"
    Plurals.MANY -> "дней"
    Plurals.OTHER -> "день"
}

val Long.asPlurals
    get() = when {
        this in 5L..20L -> Plurals.MANY
        this == 1L -> Plurals.ONE
        (this % 10L) in 2L..4L -> Plurals.FEW
        (this % 10L == 1L) -> Plurals.OTHER
        else -> Plurals.MANY
    }


enum class Plurals {
    ONE,
    FEW,
    MANY,
    OTHER
}