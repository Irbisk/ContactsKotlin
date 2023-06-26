import kotlinx.datetime.*

fun nextMonth(date: String): String {
    val instant = date.toInstant()
    return instant.plus(DateTimeUnit.MONTH, TimeZone.UTC).toString()
}

fun main() {
    val date = readln()
    println(nextMonth(date))
}