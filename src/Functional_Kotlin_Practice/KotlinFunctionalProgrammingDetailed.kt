package Functional_Kotlin_Practice

fun main(args: Array<String>) {
    // basicExtendedFunctionExample()
    // infixFunctionExample()
    // operatorOverloadingExample()
    inlineFunctionExample()

}

// Extended Function.
fun basicExtendedFunctionExample() {
    // Basic Extended Function
    fun String.writeConsole() = println(this)

    "Hello, World".writeConsole()
}

// It is possible to access instance and its properties using this keyword, however, it is not possible to access to its
// properties if they are declared as a private.

// infix function: Infix function is a general/extended function that has only one parameter.
// infix expression is useful to make code for math/algebra calculation look more clear and natual.
fun infixFunctionExample() {
    // infix function
    infix fun Int.superOperation(i: Int) = this + i

    // Both are same value.
    println(1.superOperation(2))
    println(1 superOperation 2)     // This type of expression is possible for infix function.
}

// Operator Overloading
// It is possible to overload operators in Kotlin, including plus, minus, multiply, divide, mode, even a invoke.
// Example: Overload invoke operator.
// x() same as x.invoke().      x(y) same as x.invoke(y)
//
// This is a main technique behind that allows to call lambda function using parentheses.
class VendingMachine(val name: String) {
    // Overload operator
    operator fun invoke(itemNumber: Int) = when (itemNumber) {
        1 -> "Vending Coke..."
        2 -> "Vending Sprite..."
        3 -> "Vending Fanta..."
        4 -> "Vending Monster Energy..."
        5 -> "Vending Dr. Paper..."
        else -> {}
    }
}

fun operatorOverloadingExample() {
    val mMachine: VendingMachine = VendingMachine("Test Machine")
    println(mMachine(1))    // Overloaded operator has been called.
    println(mMachine.invoke(2))     // Explicitly call overloaded operator.
}

// Inline Function.
// High Order Function offers remarkable performance, however, it also has a drawbacks.
// During compilation-time, lambda is converted into allocated object, and its invoke function will be called to
// execute the lambda function. This will take CPU power and memory regardless of whether its task is huge or small.
//
// Kotlin offers inline keyword that converts lambda into a normal expression, not allocated object. Therefore, inline
// function is generally fast, however, creates more lines of code.

// Inline Function
inline fun <T> withTimeInlined(body: () -> T): Pair<T, Long> {
    val timeStart = System.nanoTime()
    val v = body()
    val timeEnd = System.nanoTime()
    return v to timeEnd - timeStart
}

fun inlineFunctionExample() {
    // Typical High Order Function without inline keyword.
    fun <T> withTime(body: () -> T): Pair<T, Long> {
        val timeStart = System.nanoTime()
        val v = body()
        val timeStop = System.nanoTime()
        return v to timeStop - timeStart    // function 'to' is an infix function.
                                            // (expected:
                                            // infix fun <T,R> T.to(v: R): Pair<T, R> {
                                            //      return Pair(this, v)
                                            // }
    }

    val (_, time) = withTime { Thread.sleep(1000L) }
    println("time (Typical High Order Function = $time")

    val (_, timeInlined) = withTimeInlined { Thread.sleep(1000L) }
    println("time (Inlined function) = $timeInlined")
}

// Inline function has a restriction: Modification is not allowed.
// Also, the way that inlined function treated during compilation time, it is not possible to pass argument-ed lambda
// function to another function. To pass the argument-ed lambda function to another function, put noinline keyword
// in front of parameter declaration.
//
// Use inline when:
// 1. High order function that accept function (lambda) as a parameter.
// 2. Check noinline is applicable when argument-ed function (lambda) is passed to another high order function that isn't
//    inlined.