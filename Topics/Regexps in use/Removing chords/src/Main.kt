fun main() {
    val text = readln()
    val reg = "(Am|A|Em|E|Dm|D|G|C)\\b".toRegex()
    println(text.replace(reg, "").replace(" +".toRegex(), " ").trim())

}