package contacts

var contactsList = mutableListOf<Base>()

fun main() {
    menu()
}

fun menu() {
    BaseSerializer.deserialize()
    while (true) {
        println("[menu] Enter action (add, list, search, count, exit):")
        when (readln()) {
            "add" -> add()
            "list" -> list()
            "count" -> count()
            "search" -> search()
            "exit" -> break
        }
    }
    BaseSerializer.deleteFile()
}

fun search() {
    while (true) {
        println("Enter search query:")
        val query = readln().lowercase()
        val searchList = contactsList.filter { it.toString().lowercase().contains(query) }.toMutableList()
        println("Found ${searchList.size} results:")
        listContacts(searchList)
        println("[search] Enter action ([number], back, again):")
        when (val action = readln()) {
            "back" -> break
            "again" -> continue
            else -> {
                searchList[action.toInt() - 1].showInfo()
                recordMenu(searchList[action.toInt() - 1])
                break
            }
        }
    }
    println()
}

fun recordMenu(record: Base) {
    while (true) {
        println("[record] Enter action (edit, delete, menu):")
        when(readln()) {
            "edit" -> edit(record)
            "remove" -> remove(record)
            "menu" -> break
        }
    }
    println()
}

fun count() {
    println("The Phone Book has ${contactsList.size} records.\n")
}

fun edit(record: Base) {
    println("Select a field ${record.getFieldsNames()}:")
    val fieldName = readln()
    println("Enter $fieldName")
    val field = readln()
    record.setField(fieldName, field)
    println("Saved\n")

    BaseSerializer.serialize()
}

fun listContacts(list: MutableList<Base>) {
    for (i in list.indices) {
        println("${i + 1}. ${list[i].getFullName()}")
    }
    println()
}

fun list() {
    while (true) {
        println("[list] Enter action ([number], back):")
        when (val action = readln()) {
            "back" -> break
            else -> {
                contactsList[action.toInt() - 1].showInfo()
                recordMenu(contactsList[action.toInt() - 1])
                break
            }
        }
    }
    println()
}

fun remove(record: Base) {
    contactsList.remove(record)
    println("The record removed!\n")

    BaseSerializer.serialize()
}

fun add() {
    println("Enter the type (person, organization):")
    when (readln()) {
        "person" -> {
            println("Enter the name:")
            val name = readln()
            println("Enter the surname:")
            val surname = readln()
            contactsList.add(Contact(name, surname))
            println("Enter the birth date:")
            contactsList[contactsList.lastIndex].setField("bd", readln())
            println("Enter the gender (M, F):")
            contactsList[contactsList.lastIndex].setField("gender", readln())
            println("Enter the number:")
            contactsList[contactsList.lastIndex].setField("number", readln())
        }
        "organization" -> {
            println("Enter the organization name:")
            val name = readln()
            println("Enter the address:")
            val address = readln()
            contactsList.add(Organization(name, address))
            println("Enter the number:")
            contactsList[contactsList.lastIndex].setField("number", readln())
        }
    }

    println("The record added.\n")

    BaseSerializer.serialize()
}