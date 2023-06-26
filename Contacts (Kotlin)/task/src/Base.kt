package contacts

import kotlinx.datetime.*

abstract class Base {
    val timeCreated = Clock.System.now().toLocalDateTime(TimeZone.UTC).toString()
    var timeLastEdit = timeCreated

    var number: String? = null
        set(value) {
            field = if (value?.checkNumber() == true) {
                value
            } else {
                println("Wrong number format!")
                null
            }
        }
        get() {
            timeLastEdit = Clock.System.now().toLocalDateTime(TimeZone.UTC).toString()
            return if (field == null) {
                "[no number]"
            } else field
        }

    private fun String.checkNumber(): Boolean {
        val noBrackets = "\\+?\\w+([- ]\\w{2,})*".toRegex()
        val firstBrackets = "\\+?\\(\\w+\\)([- ]\\w{2,})*".toRegex()
        val secondBrackets = "\\+?\\w+([- ]\\(\\w{2,}\\))([- ]\\w{2,})*".toRegex()

        return (this.matches(noBrackets) || this.matches(firstBrackets) || this.matches(secondBrackets))
    }



    abstract fun showInfo()
    abstract fun getField(field: String): String?
    abstract fun setField(field: String, value: String)
    abstract fun getFullName(): String
    abstract fun getFieldsNames(): String

}


class Contact(name: String, surname: String): Base() {
    private var name: String = name
        set(value) {
            timeLastEdit = Clock.System.now().toLocalDateTime(TimeZone.UTC).toString()
            field = value
        }
    private var surname: String = surname
        set(value) {
            timeLastEdit = Clock.System.now().toLocalDateTime(TimeZone.UTC).toString()
            field = value
        }
    private var gender: String? = null
        set(value) {
            timeLastEdit = Clock.System.now().toLocalDateTime(TimeZone.UTC).toString()
            field = if (value?.checkGender() == true) {
                value
            } else {
                println("Bad gender!")
                null
            }
        }
        get() {
            return if (field == null) {
                "[no data]"
            } else field
        }
    private var bd: String? = null
        set(value) {
            timeLastEdit = Clock.System.now().toLocalDateTime(TimeZone.UTC).toString()
            field = try {
                val date = value?.toLocalDate()
                date.toString()
            } catch (e: Exception) {
                println("Bad birth date!")
                null
            }
        }
        get() {
            return if (field == null) {
                "[no data]"
            } else field
        }


    private fun String.checkGender(): Boolean {
        val reg = "M|F|male|female".toRegex()
        return this.matches(reg)
    }

    override fun showInfo() {
        println("Name: ${this.name}")
        println("Surname: ${this.surname}")
        println("Gender: ${this.gender}")
        println("Birth date: ${this.bd}")
        println("Number: ${this.number}")
        println("Time created: ${this.timeCreated}")
        println("Time last edit: ${this.timeLastEdit}")
        println()

    }

    override fun getField(field: String): String? {
        return when (field) {
            "name" -> this.name
            "surname" -> this.surname
            "gender" -> this.gender
            "bd" -> this.bd
            "number" -> this.number
            else -> null
        }
    }

    override fun setField(field: String, value: String) {
        when (field) {
            "name" -> this.name = value
            "surname" -> this.surname = value
            "gender" -> this.gender = value
            "bd" -> this.bd = value
            "number" -> this.number = value
        }
    }

    override fun getFullName(): String {
        return "${this.name} ${this.surname}"
    }

    override fun getFieldsNames(): String {
        return "(name, surname, birth, gender, number)"
    }

    override fun toString(): String {
        return "$name $surname $gender $bd $number)"
    }

}

class Organization(name: String, address: String): Base() {
    private var name: String = name
        set(value) {
            timeLastEdit = Clock.System.now().toLocalDateTime(TimeZone.UTC).toString()
            field = value
        }
    private var address: String = address
        set(value) {
            timeLastEdit = Clock.System.now().toLocalDateTime(TimeZone.UTC).toString()
            field = value
        }

    override fun showInfo() {
        println("Organization name: ${this.name}")
        println("Address: ${this.address}")
        println("Number: ${this.number}")
        println("Time created: ${this.timeCreated}")
        println("Time last edit: ${this.timeLastEdit}")
        println()
    }

    override fun getField(field: String): String? {
        return when (field) {
            "name" -> this.name
            "number" -> this.number
            "address" -> this.address
            else -> null
        }
    }

    override fun setField(field: String, value: String) {
        when (field) {
            "name" -> this.name = value
            "number" -> this.number = value
            "address" -> this.address = value
        }
    }

    override fun getFullName(): String {
        return this.name
    }

    override fun getFieldsNames(): String {
        return "(address, number)"
    }

    override fun toString(): String {
        return "$name $address $number"
    }
}