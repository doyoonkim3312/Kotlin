package kotlin_lambda_expression

/*
    Lambda Expression is a one forms of anonymous function, which perform similar to function even though it does not
    have a name.
    Lambda can be used as a argument of higher function or returned as a result value.
 */

fun main(args: Array<String>) {
    // simpleLambdaExpression()
    // lambdaExpressionExample2()
    // lambdaExpressionExample3()
    // lambdaExpressionExample4()
    // lambdaExpressionExample5()
    // lambdaExpressionExample6()
    // lambdaExpressionExample7()
    // lambdaExpressionExample8()
    lambdaExpressionExample9()

    // briefIntroOfInlineModifier()
}

fun simpleLambdaExpression() {
    //Simple example
    val lambdaExample1 = {x: Int, y: Int -> x * y}
    val lambdaExample2: (Int, Int) -> Unit = {
            x: Int, y -> println("${x * y}")
    }
    val lambdaExample3 = {x: Int, y: Int ->
        val z = x + y
        x * y
        z * x   //Expected: (x + y) * x
        // if there are more than two lines of expression, the very last expression will be returned.
    }
    println(lambdaExample1(2,5))
    lambdaExample2(2,5)
    println(lambdaExample3(2,5))
}

fun lambdaExpressionExample2() {
    // Nested Lambda
    val nestedLambda: () -> () -> Unit = { { println("Nested!") } }
    nestedLambda()  // Don't know the exact reason, but it didn't work.

    // Example of higher order function that uses lambda expression as an argument.
    var result: Int
        //Ex 1:
        result = higherOrder({x, y -> x + y}, 10, 20)
        println(result) // Expected to print 30
        //Ex 2:
        result = higherOrder({x, y -> x * y},20, 50)
        println(result) // Expected to print 1000
        //Ex 3:
        result = higherOrder({x, y -> x / y}, 40, 20)
        println(result) // Expected to print 2
}

fun higherOrder(calculate: (Int, Int) -> Int, a: Int, b:Int): Int {
    // Lambda Expression "calculate" is used as a argument of this higher order function.
    // 'argumentName': (Lambda parameter Data Types...) -> LambdaReturnType, (lambdaArgumentSpecifications...)
    return calculate(a,b)
}

fun lambdaExpressionExample3() {
    fun callByValue(b: Boolean): Boolean {
        println("callByValue Function")
        return b
    }

    val lambda: () -> Boolean = {
        println("Lambda Expression!")
        true
    }

    // Call-By-Value
    // if Lambda Expression is used as an argument of one function, that Lambda Expression will be treated as a value,
    // so that Lambda Expression will be executed immediately, then the returned value will be passed.
    val result = callByValue(lambda())
    println(result)
}

fun lambdaExpressionExample4() {
    fun callByName(b: () -> Boolean): Boolean { // This function's parameter value is declared as a Lambda Expression
        println("callByName Function")          // function data type.
        return b()
    }

    val otherLambda: () -> Boolean = {
        println("otherLambda Function")
        true
    }

    // Call-By-Name
    // Similar to Call-By-Value, but callByName function uses lambda expression's name. (in this case, b())
    val result = callByName(otherLambda)    // Check this difference! In this case, Lambda Expression is called by its name.
    println(result)
}

fun lambdaExpressionExample5() {
    fun sum(a: Int, b: Int) = a + b     // Function 'sum' is not a Lambda Expression Function.

    fun text(a: String, b: String) = "Hi!, $a $b"   // Function 'text' is not a Lambda Expression Function.

    fun funcParam(a: Int, b: Int, c: (Int, Int) -> Int): Int {  // Lambda Expression in function's argument is called by
        return c(a, b)                                          // its name.
    }

    fun hello(body: (String, String) -> String): Unit {     // Lambda Expression in function's argument is called by its
        println(body("Hello", "World"))                     // name.
    }
    // Call by reference by other function.
    // 1. Function that has argument and returned value.
    val result1 = funcParam(3,2, ::sum)     // Since function 'sum' is not a Lambda Expression Function, just call
    println(result1)                             // -ing function 'sum' by its name cause error.
                                                 // To call function 'sum' like a Lambda Expression Function, using two
                                                 // colons (::) in front of function name. (ex: '::sum')

    val result2 = hello(::text)     // Similar logic as described above. hello(::text) eventually executes:
                                    // 'println(text("Hello", "World"))

    // 3. Allocate normal function to a normal value like a value. (using normal function like a lambda Expression)
    val likeLambda = ::sum
    println(likeLambda(2,5))    // Expected 7.
}

fun lambdaExpressionExample6() {
    // Lambda Expression Function, which has no parameter values.
    fun noParam(out: () -> String): Unit {
        println(out())
    }
    fun noParam(a: String, out: (String) -> String): Unit {
        println(out(a))
    }

    noParam { "Hello, World!" }
    noParam { "Hello, Kotlin!" }

    val testLambda: (String) -> String = {x -> x}

    noParam("Hello, World") { x -> "$x!!!!!!!!!!" }
    noParam("Hello, Kotlin!", testLambda)
}

fun lambdaExpressionExample7() {
    // Lambda Expression Function, which has one parameter values.
    fun oneParam(out: (String) -> String): Unit {
        println(out("One Param"))
    }

    // Lambda Expression Function, which has more than two parameter values.
    fun twoParam(out: (String, String) -> String): Unit {
        println(out("First Argument", "Second Argument"))
    }

    oneParam { it ->    // "One Param" will be assigned to 'it'
        "$it, Welcome!"
    }

    oneParam { "$it, Welcome!" }    // 'it' is a default value assigned for argument passed to Lambda Expression, which
                                    // takes single argument value.

    oneParam { string ->
        "Hello, $string"
    }
    println()

    twoParam { a, b -> "Hello! $a and $b" }     // If Lambda Expression Function takes more than two parameter values,
                                                // parameter values' name in Lambda Expression cannot be skipped.
    twoParam { _, b -> "Hello! $b" }    // To drop some parameter values, use 'underbar' for parameter declaration.
}

fun lambdaExpressionExample8() {
    // Function that takes normal argument and lambda Expression Function at a same time.
    // 1. Function that takes Lambda Expression Function as a last argument.
    fun withArgs(a: String, b: String, out:(String, String) -> String): Unit {
        println(out(a, b))
    }
    // 2. Function that does not takes Lambda Expression Function as a last argument.
    fun withArgs(a: String, out:(String, String) -> String, b: String): Unit {
        println(out(a, b))
    }

    // Function that takes more than two Lambda Expression Function as an argument.
    fun twoLambda(out1: (String, String) -> String, out2: (String) -> String): Unit {
        println(out1("Welcome", "To"))
        println(out2("Kotlin"))
    }

    withArgs("Hello", "World") { a, b -> "$a, $b" } // If function takes Lambda Expression Function as a last argument,
                                                         // Lambda Expression can be extracted outside of round bracket when
                                                         // calling a function.

    withArgs("Hello", {a, b -> "$a, $b"}, "Kotlin")

    twoLambda({s1, s2 -> "$s1 $s2,"}) { "$it Lambda Expression Example!" }  // Similar convention described above.

}

fun briefIntroOfInlineModifier() {
    // Brief Introduce of 'inline' modifier.

    val testA: String = "Kotlin"
    println("Hello")
    testA.printWithDoubleSpaced {
        "\n\n$it"
    }

    testA.printWithDoubleSpaced()
}

inline fun <T> T.printWithDoubleSpaced(out: (T) -> String) {
    println(out(this))
}

fun <T> T.printWithDoubleSpaced() {
    println("\n\n$this")
}

fun lambdaExpressionExample9() {
    // Lambda Expression with 'Return" (w Labeling)
    val lambdaWithReturn: (Int, Int) -> Unit = lambda1@{x, y ->
        println("Start Point of Lambda")
        val result = x + y
        if (result > 10) return@lambda1
        else println("Result, $result, is not greater than 10")
    }

    lambdaWithReturn(2, 5)
    println()
    lambdaWithReturn(10, 20)
}

