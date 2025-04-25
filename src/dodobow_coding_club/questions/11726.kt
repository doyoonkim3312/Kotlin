// Global variable for memoization
val memo = LongArray(1001) { 0 }

fun main() {
    println(fibonacci(readln().toInt()))
}

fun fibonacci(n: Int): Long {
    if (n < 3) return n.toLong()
    if (memo[n] != 0L) return memo[n]
    return ((fibonacci(n - 1) + fibonacci(n - 2)) % 10007).also { memo[n] = it }
}