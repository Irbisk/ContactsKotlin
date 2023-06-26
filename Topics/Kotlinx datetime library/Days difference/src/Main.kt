import kotlinx.datetime.*

fun howManyDays(year1: Int, month1: Int, day1: Int, year2: Int, month2: Int, day2: Int): Int {
    // Write your code here
    val lt1 = LocalDateTime(year1, month1, day1,0,0)
    val lt2 = LocalDateTime(year2, month2, day2,0,0)


    val instant1 = Instant.parse("$lt1:00Z")
    val instant2 = Instant.parse("$lt2:00Z")

    return instant2.minus(instant1, DateTimeUnit.DAY, TimeZone.UTC).toInt()
    //
}

fun main() {
    val (year1, month1, day1) = readln().split(" ").map { it.toInt() }
    val (year2, month2, day2) = readln().split(" ").map { it.toInt() }

    println(howManyDays(year1, month1, day1, year2, month2, day2))
}