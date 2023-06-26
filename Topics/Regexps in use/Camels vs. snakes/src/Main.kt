import java.util.*

fun getCamelStyleString(inputString: String): String {
    return inputString.split("_").map { it.capitalize() }.joinToString("")

}