package dodobow_coding_club.questions

lateinit var dp: Array<Int>

fun main() {
    val n = readln().toInt()
    val wines = List<Int>(n) { readln().toInt() }

    dp = Array<Int>(n) { 0 }.apply {
        this[0] = wines[0]
        if (n > 1) this[1] = wines[0] + wines[1]
        if (n > 2) this[2] = (wines[2] + wines[0]).coerceAtLeast(wines[2] + wines[1])
    }
    if (n > 3) {
        for (i in 3 until wines.size) {
            val case1 = wines[i] + dp[i - 2]
            val case2 = wines[i] + wines[i - 1] + dp.slice(0..i - 3).max()
            dp[i] = case1.coerceAtLeast(case2)
        }
    }
    println(dp.max())
}