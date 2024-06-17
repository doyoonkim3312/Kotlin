package LeetCode_Solutions

/**
 * Leet Code Q316: Remove Duplicated Letter.
 *
 */

fun main() {
    CodeAnalysis.executionTime {
        println(q316RevisedSolution("bcabc"))
    }
}

fun q316InitialAttempt(s: String): String {
    val sorted = s.toCharArray().sorted()

    val st = ArrayDeque<Char>()
    for (c in sorted) {
        if (st.lastOrNull() != c) st.addLast(c)
    }

    return ""
}

/**
 * Identified counter-test case: "bcabc"
 */
fun q316RevisedSolution(s: String) : String {
    val inputDeque = ArrayDeque<Char>().also {
        it.addAll(s.toList())
    }
    val result = ArrayDeque<Char>()

    while (inputDeque.size != 0) {
        val temp = inputDeque.removeFirst()
        if (temp > inputDeque.first()) inputDeque.addLast(temp)
        else {
            result.addLast(temp)
            inputDeque.removeAll { it == temp }
        }
    }


    return CharArray(result.size) { result.removeFirst() }.joinToString("")
}