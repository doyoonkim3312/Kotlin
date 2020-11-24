/*
Higher Order Function
    Higher Order Function is either return another function or that take functions as parameter value
    Most essential concept of Higher Order Function in Kotlin is 'Other Function' in the function's parameter,
    it's treated as "Function Type" like "String", "Int", "Boolean", "Float" etc,.
 */
package kotlin_AdvancedFunctions

fun printFilterStrings(list: List<String>, predicate: ((String)-> Boolean)?) {
    //iteration
    list.forEach() {
        if (predicate?.invoke(it) == true) {
            println(it)
        }
    }
}

val predicate: (String) -> Boolean = {
    it.startsWith("K")
}

fun getPrintPredicate() : (String) -> Boolean {
    return {it.startsWith("J")}
}

fun main() {
    val list = mutableListOf<String>("Kotlin","Java","Javascript","C++")
    printFilterStrings(list, predicate)
    //printFilterStrings(list) {it.startsWith("K")}
    // If the 'predicate is already defined, programmer can just invoke predicate directly.
    // If the last parameter of function is another function, the Lambda expression can be placed outside of the
    //parentheses.

    printFilterStrings(list, getPrintPredicate())

    printFilterStrings(list, null)
}