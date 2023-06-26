fun parsingNickname(emailString: String): String {
    val symbolsForNickname = Regex("[._]") // fix this condition
    val nicknameString = emailString.split("@").first() // fix this condition
    val (firstName, lastName ) = nicknameString.split(symbolsForNickname)
    val nickname = "${firstName.capitalize()} ${lastName.capitalize()}"
    return nickname
}