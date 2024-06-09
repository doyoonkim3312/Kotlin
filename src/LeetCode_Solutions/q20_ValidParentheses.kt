package LeetCode_Solutions

/**
 * Leet Code Q20: Valid Parentheses
 */


fun main() {
    val test = "(){}[]"

    println("Result: ${q20InitialAttempt(test)}")
}

fun q20InitialAttempt(input: String): Boolean {
//    val closing = mapOf(Pair('(', ')'), Pair('{', '}'), Pair('[', ']'))
    val closing = mapOf('(' to ')', '{' to '}','[' to ']')

    // Use stack to validate parentheses.
    val st = ArrayDeque<Char>()

    for (c in input) {
        if (closing[st.lastOrNull()] == c) st.removeLast()
        else st.addLast(c)
    }

    return st.size == 0
}