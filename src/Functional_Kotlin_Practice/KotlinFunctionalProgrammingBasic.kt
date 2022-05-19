package Functional_Kotlin_Practice

// Functional Programming.
// Functional Programming is one of the paradigm, which fundamentally 'return a data as an expression.'
// --> in other words, always ensure same value when function with a parameter is called.
//
// Ex: In mathematics, function f(x) = x^2 always ensures same value with same x value. If x = 5, result of
// f(x) would always be 25. --> Define a relationship between input and output.
//
// The easiest method to achieve the objective described above is 'avoid access under mutable status.'

fun main(args: Array<String>) {
    // accessUnderMutableAndImmutableStatusExample()
    // firstClassFunctionExample()
    // lambdaAsParameter()
    // domainSpecificLanguageExample()
    dslWithTypeAlias()
}

// Example of comparison between access under mutable status and immutable status.
fun accessUnderMutableAndImmutableStatusExample() {
    var j = 0

    // Access under immutable status.
    fun f(x: Long): Long {
        return x * x
    }

    // Access under mutable status.
    fun g(x: Long): Long {
        return x * j
    }

    // J will be incremented.
    while (j < 3) {
        ++j
        println("Immutable Status: ${f(5)}\t\tMutable STatus: ${g(5)}")
    }
}

// In actual application, this 'status' would be changed. Therefore, a status manager in Functional Programming
// is explicit and needs extra caution.
//
// Advantage of functional programming style.
// 1. Code would be easier to read and tested.
// 2. A status and its following effect would be designed carefully.
// 3. Provide safer, and more natual concurrency.

// First Class Function.
// Under Kotlin, First Class Function is called 'Lambda'.
// Refer content under kotlin_lambda_expression directory for more details about lambda.

fun firstClassFunctionExample() {

    // Lambda Expression
    val capitalize = { str: String -> str.capitalize() }
    println(capitalize("hail purdue"))

    // Lambda Expression above has a type of (String) -> String, which means, capitalize takes String as its parameter
    // and returns String value.
    //
    // More detail, (String) -> String is a shortcut of Function1<String, String> in Kotlin Standard Library.
    // Kotlin standard library contains Function1<P1, R> interface. (Function1<P1, R>은 연산자로 표기된 invoke(P1): R 인
    // 하나의 메소드.)

    // Based on the information above, code below is same as lambda expression above.
    val capitalize2 = object: Function1<String, String> {
        override fun invoke(p1: String): String {
            return p1.capitalize()
        }
    }

    println(capitalize2("hail purdue"))
}

// Use lambda function as a parameter.
fun lambdaAsParameter() {
    // Lambda Function Transform.
    // Lambda Function 'transform(String, (String) -> String) takes one String and apply it to given lambda Function.
    fun transform(str: String, fn: (String) -> String): String {
        return fn(str)
    }

    // It is possible to generalize above lambda function:
    // fun <T> transform (t: T, fn: (T) -> T): T { return fn(T) }

    // Use transform function.
    val reverse = { str: String -> str.reversed() }

    // Another Lambda function
    fun reverse(str: String): String {
        return str.reversed()
    }

    // lambda under object.
    val StringUtil = object {
        fun reverse(str: String): String {
            return str.reversed()
        }
    }

    // Pass References to lambda function.
    println(transform("Apple", reverse))
    println(transform("Apple", ::reverse))  // :: pass the reference of function reverse.
    println(transform("Apple", StringUtil::reverse))

    // However, lambda expression would be passed directly in most cases.
    println(transform("Apple", { str -> str.reversed() }))

    // If lambda function is a very last parameter, lambda expression can be extracted outside of parentheses.
    // This feature opens a possibility of creating Domain Specific Language (DSL).
    println(transform("Apple") { str -> str.reversed() })
}

// Brief Example of Domain Specific Language.
// Example: Create Kotlin version of 'unless' flow control in Ruby. In Ruby, unless is a negative-if statement; code is
// executed when the condition is false.
fun domainSpecificLanguageExample() {

    // () -> Unit means, lambda function  with no parameter and return value.
    fun unless(condition: Boolean, block: () -> Unit) {
        if (!condition) block()
    }

    val securityStatus: Boolean = false

    // This style of expression is possible because the lambda function in unless function is a very last parameter.
    // unless(securityStatus, { println("Security Exception: Denied" }) (without extract lambda expression outside of parantheses.
    unless(securityStatus) {
        println("Security Exception: Denied")
    }

}

// DSL also can be used with a type alias to replace simple interface.
typealias Animal<T> = (T) -> Unit;

fun <T> soundOfAnimal(sound: T, fn: Animal<T>) {
    fn(sound)
}

// Lambda Expression also can be expressed like Function1<R1, T> interface with invoke function.
class PrintSound<T>: Animal<T> {
    override fun invoke(p1: T) {
        println(p1)
    }
}

fun dslWithTypeAlias() {
    soundOfAnimal("Bark", PrintSound())
    soundOfAnimal("Bark", ::println)
}