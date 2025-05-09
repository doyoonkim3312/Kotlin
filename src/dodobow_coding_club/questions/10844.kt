package dodobow_coding_club.questions

import java.io.BufferedReader
import java.io.InputStreamReader

val MOD = 1000000000
fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val n = readLine().toInt()
    // 2-D dp
    val dp = Array<LongArray>(n) { LongArray(10) { 0 } }.apply {
        repeat(9) { this[0][it] = 1 }
    }

    for (i in 1 until n) {
        for (j in 0 until 10) {
            if (j == 0) {
                dp[i][j] = dp[i-1][j+1] % MOD
            } else if (j == 9) {
                dp[i][j] = dp[i-1][j-1] % MOD
            } else {
                dp[i][j] = (dp[i-1][j-1] + dp[i-1][j+1]) % MOD
            }
        }
    }

    println(dp[n-1].sum() % MOD)
}