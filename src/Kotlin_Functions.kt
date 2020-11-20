/*
Basic Functions.

    Function: Sum of codes for doing certain task. Function should do 'ONE' thing well. Function that does too much task
    should be avoided.

    fun: keyword for generating function.
    fun functionName (parameters) : returnType {}

    * Unit: Unit in Kotlin is essentially the absence of any useful type (means nothing useful.)
    While function is being declared, keyword 'Unit' can be skipped.

    function in Kotlin also has a type inference.

    "String Template: Kotlin support String template that allows to substitute in variable values or argument value
    into a predefined string template.
 */

fun main() {
    println("Hello Kotlin")
    println(getGreeting())
    sayHelloKotlin()
    println(getGreetingSingleExpression())
    sayHello("Word", "Hey!")
}

fun getGreeting(): String? {
    return "Hello Kotlin"
}

fun getGreetingSingleExpression() = "Hello Kotlin using one line"
//fun getGreetingSingleExpression(): String = "Hello Kotlin using one line"


fun sayHelloKotlin(): Unit {
    println("Hello Kotlin!")
}

// Parameters
fun sayHello(targetToGreet: String, typeOfGreet: String) {
    println("$typeOfGreet $targetToGreet")
}
// -> Single Expression
//fun sayHello(targetToGreet: String) = println("Hello $targetToGreet")

