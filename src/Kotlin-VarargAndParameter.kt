/*
Vararg, argument and Parameter Value

vararg: Keyword in Kotlin that essentially represents a variable number of arguments.
*: Spread Operator; applying asterisk before array variable.
Named-argument Syntax: When argument is given to function, declared which argument is which.
 */

fun main() {
    val programmingLanguages = arrayOf("Kotlin", "java", "python")
    sayHelloGreeting("Yo", *programmingLanguages)
    sayHelloGreeting("Ay","Kotlin", "C#")

    greetPerson("Hello", "Doyoon")
    greetPerson("Ay", "Doyoon")
}

fun sayHelloGreeting(typeOfGreet: String, vararg targetsToGreet: String) {
    targetsToGreet.forEach { element ->
        println("$typeOfGreet! $element")
    }
}

fun greetPerson(typeOfGreet: String, targetOfGreet: String) = println("$typeOfGreet! $targetOfGreet")