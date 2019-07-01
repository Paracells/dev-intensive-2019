package ru.skillbranch.devintensive.extensions

import ru.skillbranch.devintensive.R
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.Period
import java.time.temporal.ChronoUnit
import java.util.*
import java.util.concurrent.TimeUnit
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
    DAY
}

//TODO
/*
    Реализуй extension Date.humanizeDiff(date) (значение по умолчанию текущий момент времени)
    для форматирования вывода разницы между датами в человекообразном формате, с учетом склонения числительных.
    Временные интервалы преобразований к человекообразному формату доступны в ресурсах к заданию
    Пример:
    Date().humanizeDiff(Date().add(-2, TimeUnits.SECOND)) //через несколько секунд
    Date().add(-2, TimeUnits.HOUR).humanizeDiff() //2 часа назад
    Date().add(-5, TimeUnits.DAY).humanizeDiff() //5 дней назад
    Date().add(2, TimeUnits.MINUTE).humanizeDiff() //через 2 минуты
    Date().add(7, TimeUnits.DAY).humanizeDiff() //через 7 дней
    Date().add(-400, TimeUnits.MINUTE).humanizeDiff() //более года назад
    Date().add(400, TimeUnits.MINUTE).humanizeDiff() //более чем через год
 */
//this - это текущий объект

/*
0с - 1с "только что"

1с - 45с "несколько секунд назад"

45с - 75с "минуту назад"

75с - 45мин "N минут назад"

45мин - 75мин "час назад"

75мин 22ч "N часов назад"

22ч - 26ч "день назад"

26ч - 360д "N дней назад"

>360д "более года назад"

левое не входит, правое включается

 */

val prefYear = "бол"
fun Date.humanizeDiff(date: Date = Date()): String {
//    val dif = this.time - date.time

    //var delta = this.time - date.time
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
/*
    println(ms)
//    println(TimeUnit.MILLISECONDS.toDays(ms))
//    println("userTime $date")
//    println("currentTime $this")
//    println("MANY: ${ms in 5L..20L}")
//    println("ONE: ${ms % 10} 1L")
//    println("FEW: ${ms % 10}  2L..4L")


//    println("min: ${TimeUnit.MILLISECONDS.toMinutes(ms)}")
//    println("seconds: ${TimeUnit.MILLISECONDS.toSeconds(ms)}")
//    println("hour: ${TimeUnit.MILLISECONDS.toHours(ms)}")
//    println("days: ${TimeUnit.MILLISECONDS.toDays(ms)}")
//    println()
//    println()


    return when (ms) {
        0L -> "только что"
        in 1..45 * SECOND -> convertTime(ms, "seconds")
        in 46 * SECOND..75 * SECOND -> "минуту"
        in 76 * SECOND..45 * MINUTE -> convertTime(ms, "minutes")
        in 46 * MINUTE..75 * MINUTE -> convertTime(ms, "minutes")
        in 76 * MINUTE..22 * HOUR -> convertTime(ms, "hours")
        in 23 * HOUR..26 * HOUR -> "daysAsPlurals(ms)"
        in 27 * HOUR..360 * DAY -> convertTime(ms, "days")
        else -> "более года назад"
    }


}

fun convertTime(ms: Long, whatConvert: String): String {
    when (whatConvert) {
        "seconds" -> {
            var a = TimeUnit.MILLISECONDS.toSeconds(ms)
            when (a) {
                in 2L..4L -> return "через несколько секунд"
                in 5L..20L -> return "${TimeUnit.MILLISECONDS.toSeconds(ms)}"
            }

        }

        "minutes" -> {
            var a = TimeUnit.MILLISECONDS.toMinutes(ms)
            when (a) {
                1L -> return "$a минуту"
                in 2L..4L -> return "$a минуты"
                in 5L..20L -> return "$a минут"
            }

        }

        "hours" -> {
            var a = TimeUnit.MILLISECONDS.toHours(ms)
            when (a) {
                1L -> return "$a час"
                in 2L..4L -> return "$a часа"
                in 5L..20L -> return "$a часов"
            }

        }
        "days" -> {
            var a = TimeUnit.MILLISECONDS.toDays(ms)
            when (a) {
                1L -> return "$a день"
                in 2L..4L -> return "$a дня"
                in 5L..20L -> return "$a дней"
            }

        }


    }
    return "bbb"
}

//fun minutesAsPlurals(value: Long) = when (value.asPlurals) {
//    Plurals.ONE -> "${TimeUnit.MILLISECONDS.toMinutes(value)} минуту"
//    Plurals.FEW -> "${TimeUnit.MILLISECONDS.toMinutes(value)} минуты"
//    Plurals.MANY -> "${TimeUnit.MILLISECONDS.toMinutes(value)} минут"
//}
//
//fun hoursAsPlurals(value: Long) = when (value.asPlurals) {
//    Plurals.ONE -> "${TimeUnit.MILLISECONDS.toHours(value)} час"
//    Plurals.FEW -> "${TimeUnit.MILLISECONDS.toHours(value)} часа"
//    Plurals.MANY -> "${TimeUnit.MILLISECONDS.toHours(value)} часов"
//}
//
//fun daysAsPlurals(value: Long) = when (value.asPlurals) {
//    Plurals.ONE -> "${TimeUnit.MILLISECONDS.toDays(value)} день"
//    Plurals.FEW -> "${TimeUnit.MILLISECONDS.toDays(value)} дня"
//    Plurals.MANY -> "${TimeUnit.MILLISECONDS.toDays(value)} дней"
//}
//
//enum class Plurals {
//    ONE,
//    FEW,
//    MANY
//}
//
//


//enum class TUnit (val size: Long) {
//    SECOND(1000L),
//    MINUTE(60*SECOND.size),
//    HOUR(60*MINUTE.size),
//    DAY(24*HOUR.size)
//
//}
//
//val Int.sec
//get() = this* SECOND.size
//
//val Int.min
//    get() = this* MINUTE.size
//
//val Int.hour
//    get() = this* HOUR.size
//
//val Int.day
//    get() = this* DAY.size
//
//
//val Long.asMin
//get() = ((this+45.sec)/ MINUTE.size)
//
//val Long.asHour
//    get() = ((this+45.min)/ HOUR.size)
//
//val Long.asDay
//    get() = ((this+2.hour)/ DAY.size)
//
//
//val Long.asPlurals
//    get() = when {
//        this in 5L..20L -> Plurals.MANY
//        this % 10L == 1L -> Plurals.ONE
//        this % 10L in 2L..4L -> Plurals.FEW
//        else -> Plurals.MANY
//    }

 */