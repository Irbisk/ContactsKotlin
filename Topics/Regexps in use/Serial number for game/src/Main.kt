fun findSerialNumberForGame(listGames: List<String>) {
    val name = readln()
    val line = listGames.filter { it.contains(name) }
    val codes = line[0].split("@")
    println("Code for Xbox - ${codes[1]}, for PC - ${codes[2]}")

}