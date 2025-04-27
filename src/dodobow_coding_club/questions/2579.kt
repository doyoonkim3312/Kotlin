package dodobow_coding_club.questions

// Global variable for memoization
lateinit var memo: Array<Int>

fun main() {
    val steps = List<Int>(readln().toInt() + 1) {
        if (it > 0) readln().toInt()
        else 0
    }

    // Initialize array for memoization
    memo = Array<Int>(steps.size) { 0 }.apply {
        // Manual initialization for first two steps (To take 2nd step (i=1), it is guaranteed that 1st step must be taken)
        this[1] = steps[1]
        if (steps.size > 2) this[2] = steps[1] + steps[2]       // In case where there is only one step.
    }

    // To take Nth step, it is necessary to take either (n-1)th step or (n-2)th step.
    // case1 -> take (n-1)th step => It is necessary to take (n-3)th step. (Avoid three-in-a-row)
    // case2 -> take (n-2)th step => It is necessary to take nth step directly. (Avoid three-in-a-row)

    for (i in 3 until steps.size) {
        memo[i] = (memo[i-3] + steps[i-1] + steps[i]).coerceAtLeast(memo[i-2] + steps[i])
    }

    println(memo.last())
}