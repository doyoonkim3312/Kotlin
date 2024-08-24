package LeetCode_Solutions

/**
 * Q3. Longest Substring without Repeating
 * Return a length of the longest substring with no duplicated characters.
 *
 * Example 1:
 * Input: s = "abcabcbb"
 * Output: 3
 * Explanation: The answer is "abc", with the length of 3.
 *
 * Example 2:
 * Input: s = "bbbbb"
 * Output: 1
 * Explanation: The answer is "b", with the length of 1.
 *
 * Example 3:
 * Input: s = "pwwkew"
 * Output: 3
 * Explanation: The answer is "wke", with the length of 3.
 * Notice that the answer must be a substring, "pwke" is a subsequence and not a substring.
 *
 */

fun main() {
    CodeAnalysis.executionTime("Initial Attempt") {
        // Q3.initialSolution("pwwkew").also { println(it) }
        Q3.enhancedSolution("pwwkew").also { println(it) }
    }
}

class Q3 {
    companion object {
        // Initial Attempt
        fun initialSolution(s: String): Int {
            // Early Return
            if (s.length == 1) return 1
            // Approach: Utilize 'HashSet', Nested For-Loop.
            // Expected Time Complexity: O(n^2)
            var validate = hashSetOf<Char>()
            var result = 0

            for (i in s.indices) {
                validate.add(s[i])
                for (j in i + 1 until s.length) {
                    // Early-return
                    if (validate.contains(s[j])) break
                    validate.add(s[j])
                }

                result = Math.max(result, validate.size)
                validate.clear()
            }

            return result
        }

        fun enhancedSolution(s: String): Int {
            // approach: Sliding-Window with Two-pointer technique
            // Early Return
            if (s.length == 1) return 1
            var result = 0
            val validate = hashSetOf<Char>()

            // Two Pointer
            var start = 0
            var end = 0

            while (end < s.length - 1 && start <= end) {
                validate.clear().also { validate.addAll(s.substring(start, end + 1).toList()) }
                if (validate.contains(s[end + 1])) start++
                else end++

                result = Math.max(result, validate.size)
            }


            return result
        }
    }
}