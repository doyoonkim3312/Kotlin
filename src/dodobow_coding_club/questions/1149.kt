package dodobow_coding_club.questions

import java.io.BufferedReader
import java.io.InputStreamReader

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val n = this.readLine().toInt()
    val prices = List(n) {
        this.readLine().split(" ").map { it.toInt() }
    }

    // 2-D DP table
    val dp = Array(n) { intArrayOf(1001, 1001, 1001) }.apply {
        this[0] = prices[0].toIntArray()
    }

    for (i in 1 until n) {
        for (j in 0 until 3) {
            dp[i][j] = prices[i][j] + dp[i-1][(j + 1) % 3].coerceAtMost(dp[i - 1][(j + 2) % 3])
        }
    }
    println(dp[n -1].min())
}