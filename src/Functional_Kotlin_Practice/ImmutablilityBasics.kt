package Functional_Kotlin_Practice

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

fun main(args: Array<String>) {
    // breakingImmutabilityOfVal()
    // threadAndMutableData()
    ensureImmutabilityOfData()
}

// Immutability in Kotlin.
// Most of the classic functional programming languages force developer to use/maintain an immutability. However, Kotlin
// does not force an immutability, but recommend it. Kotlin does have an immutable variable, declared with val, however,
// there are no mechanism that guarantees a true immutability.

// Two Types of Immutability.
// 1. Referential Immutability
// 2. Immutable Value.

// Example of breaking an immutability of variable declared with val.
fun breakingImmutabilityOfVal() {

    // Object with simple string declared as val.
    val TestObject = object {
        // counter object declared as var.
        var cnt = 0
        val myString: String = "Immutable?"
        get() {     // Declare custom getter as well.
            return "$field ${++cnt}"
        }
    }

    // Since the custom getter adds cnt value, which increments when it is called, at the end of the field value of
    // myString, immutability of myString has been broken.

    println("Calling TestObject: ${TestObject.myString}")
    println("Calling TestObject: ${TestObject.myString}")
    println("Calling TestObject: ${TestObject.myString}")

    // Everytime when TestObject.myString is called, returned values are different.
}

// Compile-time constant.
// 'val' is a read-only variable, however, 'const val' is a compile-time constant.
// val vs. const val.
// 1. val can have custom getter, while const val cannot.
// 2. variable can be declared as val everywhere in Kotlin code, while variable has to be a very top-level member of
//    class or object in order to be declared as a const val.
// 3. Delegate cannot be written for const val.
// 4. Only primitive datatype and String can be declared as a const val.
// 5. const val is not-nullable.


// Advantages of Immutability
// 1. Thread-safe
// 2. Low Coupling.
// 3. Referential Transparency
// 4. Failure Atomicity
// 5. Compiler Optimization
// 6. Pure Function


// Advantage of Immutability - Thread Safe
// Example 1: Thread and Mutable Data.
class DummyData {
    var cnt: Int = 0
}

fun threadAndMutableData() = runBlocking {
    val dummyData: DummyData = DummyData()
    // 1st Coroutine.
    async(Dispatchers.Default) {
        for (i in 11..20) {
            dummyData.cnt += i
            println("1st async accesses cnt value: ${dummyData.cnt}")
            delay(500L)
        }
    }

    // 2nd Coroutine
    async(Dispatchers.Default) {
        for (i in 1..10) {
            dummyData.cnt++
            println("2nd async accesses cnt value: ${dummyData.cnt}")
            delay(300L)
        }
    }
}

// Below code snippet shows the way to ensure immutability of variable cnt. This also ensure the low coupling, since
// a task a changes in one thread does not affect to another thread, even though both threads read same variable cnt.
// (Coupling refers code dependency between threads.)
fun ensureImmutabilityOfData() = runBlocking {
    val dummyData: DummyData = DummyData()

    // 1st Coroutine.
    async {
        // Copy of cnt. (Ensure immutability of original cnt variable.)
        var copyOfCnt = dummyData.cnt
        for (i in 11..20) {
            copyOfCnt += i
            println("1st async accesses cnt value: $copyOfCnt")
            delay(500L)
        }
    }

    async {
        // Copy of cnt. (Ensure immutability of original cnt variable.
        var copyOfCnt = dummyData.cnt
        for (i in 1..10) {
            copyOfCnt++
            println("2nd async accesses cnt value: $copyOfCnt")
            delay(300L)
        }
    }
}

// Advantage of Immutability - Referential Transparency
// Referential Transparency refers that an expression may be replaced by its value (or anything having the same value)
// without changing the result of the program. (In other words, function can be replaced by return value.)
