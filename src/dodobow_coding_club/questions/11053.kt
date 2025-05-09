package dodobow_coding_club.questions

fun main() {
    val size = readln().toInt()
    val sequence = readln().split(" ").map { it.toInt() }
    val memo = Array<Int>(size) { 1 }

    // Time Complexity -> n^2 at maximum
    for (i in 1 until sequence.size) {
        for (j in 0 until i) {
            if (sequence[i] > sequence[j]) memo[i] = memo[i].coerceAtLeast(memo[j] + 1)
        }
    }

    println(memo.max())
}