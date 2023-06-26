import kotlinx.datetime.*

fun printDate(date: String) {
    val ld = date.toLocalDate()
    println("${ld.dayOfWeek}, ${ld.month} ${ld.dayOfMonth}, ${ld.year}")
}

fun main() {
    val date = readln()
    printDate(date)
}