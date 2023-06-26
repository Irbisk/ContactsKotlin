fun main() {
    val text = readLine()!!
    val regexColors = "#[0-9a-fA-F]{6}\\b".toRegex()
    val list = regexColors.findAll(text)
    list.forEach { println(it.value) }
}