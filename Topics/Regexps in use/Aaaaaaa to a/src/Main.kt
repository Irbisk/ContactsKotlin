fun main() {
    val text = readln()
    val list = text.split(" ").map { it.replace("[aA]+".toRegex(), "a") }.joinToString(" ")
    println(list)
}