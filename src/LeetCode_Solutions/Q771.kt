package LeetCode_Solutions

/**
 * Q771
 * You are given strings "jewels" representing the types of stones that are jewels, and "stones" representing the
 * stones you have. Each character in stones is a type of stone you have. You want to know how many o9f the stones
 * you have also a jewels.
 *
 * Example:
 *      [Input]: Jewels: "aA",      Stones: "aAAbbb"
 *      [Output]: 3
 */
fun main() {
    Q771.initialSolution("aA", "aAAbbb").also { println(it) }
}

class Q771 {
    companion object {
        // Initial Solution utilizes HashMap
        fun initialSolution(jewels: String, stones: String): Int {
            var result = 0
            val jewelsMap = hashMapOf<Char, Int>()

            // Initialize jewelsMap with character in jewels.
            jewels.forEach {
                jewelsMap[it] = 0
            }

            // Increase number for each detected jewels.
            stones.forEach {
                if (jewelsMap.containsKey(it)) jewelsMap[it] = jewelsMap[it]!! + 1
            }

            jewelsMap.values.forEach { result += it }


            return result
        }
    }
}