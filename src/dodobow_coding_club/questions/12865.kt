package dodobow_coding_club.questions

import java.io.BufferedReader
import java.io.InputStreamReader

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val (n, k) = readLine().split(" ").map { it.toInt() }
    val p = List(n + 1) {
        if (it == 0) Pair(0, 0)
        else {
            readLine().split(" ").map { it.toInt() }.run {
                Pair(this[0], this[1])
            }
        }
    }.sortedBy { it.first }

    // 2D Array for DP
    val dp = Array(n + 1) { Array(k + 1) { 0 } }

    for (i in 1 until p.size) {
        for (j in dp[i].indices) {
            if (p[i].first > j) dp[i][j] = dp[i - 1][j]
            else {
                dp[i][j] = dp[i - 1][j].coerceAtLeast(p[i].second + dp[i - 1][j - p[i].first])
            }
        }
    }

    println(dp.flatten().toSet().max())
}