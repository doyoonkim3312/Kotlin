package kotlin_AdvancedFunctions

fun main() {
    val list = mutableListOf("Kotlin", "Java", "C++", "Javascript", null)
    list.forEach {
        println(it)
    }

    list.filterNotNull().filter{it.startsWith("J")}.forEach{
        println(it)
    }

    list.filterNotNull().map{ it.length }.forEach{
        println(it)
    }

    list.take(3).filterNotNull().map{ it.length }.forEach{
        println(it)
    }

    list.takeLast(3).forEach{
        println(it)
    }

    list.filterNotNull().associate{ it to it.length }.forEach{
        println(it)
        println("${it.value}, ${it.key}")
    }

    val map = list.filterNotNull().associate { it to it.length }

    val language = list.first()
    println(language)

    val languageAtLast = list.last()
    println(languageAtLast)

    val languageFiltered = list.filterNotNull().find{ it.startsWith("Java")}
    println(languageFiltered)

    val languageFiltered2 = list.filterNotNull().findLast{ it.startsWith("Java") }
    println(languageFiltered2)

    val languageFilteredEmptyPlaceHolder = list.filterNotNull().findLast{ it.startsWith("Foo") }.orEmpty()
    println(languageFilteredEmptyPlaceHolder)
    /*
    this prints empty placeholder rather than 'null' because of .orEmpty() method. This is useful when default collection
    or string is an empty value as opposed to null value.
     */

}