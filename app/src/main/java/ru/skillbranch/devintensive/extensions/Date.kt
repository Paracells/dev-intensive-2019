package ru.skillbranch.devintensive.extensions

import java.text.SimpleDateFormat
import java.util.*


const val SECOND = 1000L
const val MINUTE = 60 * SECOND
const val HOUR = 60 * MINUTE
const val DAY = 24 * HOUR

fun Date.format(pattern: String = "HH:mm:ss dd.MM.yy"): String {

    var dateFormat = SimpleDateFormat(pattern, Locale("ru"))
    return dateFormat.format(this)

}


//TODO

/*
Реализуй extension Date.add(value, TimeUnits) добавляющий или вычитающий значение переданное первым аргументом в единицах измерения второго аргумента (enum TimeUnits [SECOND, MINUTE, HOUR, DAY]) и возвращающий модифицированный экземпляр Date
Пример:
Date().add(2, TimeUnits.SECOND) //Thu Jun 27 14:00:02 GST 2019
Date().add(-4, TimeUnits.DAY) //Thu Jun 23 14:00:00 GST 2019
 */

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

//TODO
/*
    Реализуй extension Date.humanizeDiff(date) (значение по умолчанию текущий момент времени)
    для форматирования вывода разницы между датами в человекообразном формате, с учетом склонения числительных.
    Временные интервалы преобразований к человекообразному формату доступны в ресурсах к заданию
    Пример:
    Date().humanizeDiff(Date().add(-2, TimeUnits.SECOND)) //несколько секунд назад
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
fun Date.humanizeDiff(date: Date = Date()): String {
    return this.format()




return "asd"
}

enum class TimeUnits {
    SECOND,
    MINUTE,
    HOUR,
    DAY
}