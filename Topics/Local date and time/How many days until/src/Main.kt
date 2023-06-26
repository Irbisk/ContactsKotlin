import kotlinx.datetime.*
import kotlin.math.abs

fun daysDifference(date1: String, date2: String): Int {
    return abs(date2.toLocalDate().daysUntil(date1.toLocalDate()))
}

fun main() {
    val date1 = readLine()!!
    val date2 = readLine()!!
    println( daysDifference(date1, date2) )
}