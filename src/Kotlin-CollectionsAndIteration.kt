/*
Collection and Iteration

    Default Collection Type is 'immutable'
    immutable is similar to val; can't add of subtract values from that collection once it's created.
    mutable
 */

fun main() {
    val interestingThings = arrayOf("Kotlin", "Programming", "Video Game")
    println(interestingThings.size)
    println(interestingThings[0])
    println(interestingThings.get(0))   //.get() can be substituted with indexing.

    for (interestingThing in interestingThings) {
        //This is going to iterate over each value in the array(interestingThings)
        println(interestingThing)
    }

    // The collection iteration using for loop above can be replaced by using standard library.

    interestingThings.forEach {
        println(it)     //it: Default name for each element in Array.
        //'it' keyword is not always very readable; 'it' keyword can be renamed that to value that is
        //passed into the Lambda.
    }

    /*
    'it' keyword is not always very readable; 'it' keyword can be renamed that to value that is passed into the Lambda.

    Quick idea of Lambda Syntax: A function and its only parameter is another function, then programmer can omit the
    parentheses all together and pass that function in by specifying this open and closed parentheses.
     */

    interestingThings.forEach {element ->
        println(element)
    }

    interestingThings.forEachIndexed {index,element ->
        println("$element is in index $index")
        //Useful when iterating array while index data has to be kept.
    }

    val interestingLanguage = mutableListOf("java", "Kotlin", "python")
    interestingLanguage.add("C#")
    interestingLanguage.forEach {element ->
        println(element)
    }
    greeting("Hello", interestingLanguage)

    /*
    mapOf(): "this function will essentially take in pairs"
    Pair: Simple wrapper class containing two values.

    mapOf(key to AssociatedValue, key to AssociatedValue, ...)
     */
    val map = mapOf(1 to "a", 2 to "b", 3 to "c")
    map.forEach { (key, value) -> println("$key and $value")}
}

fun greeting(typeOfGreet: String, targetsToGreet: List<String>) {
    targetsToGreet.forEach {element ->
        println("$typeOfGreet! $element")
    }
}