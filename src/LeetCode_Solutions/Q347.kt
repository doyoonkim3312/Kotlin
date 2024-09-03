package LeetCode_Solutions

/**
 * Q347: Top k Frequent Elements
 * Given an integer array nums and an integer k, return the k most frequent elements. You may return the answer in any order.
 * 
 * Example 1:
 * Input: nums = [1,1,1,2,2,3], k = 2
 * Output: [1,2]
 * 
 * Example 2:
 * Input: nums = [1], k = 1
 * Output: [1]
 */

fun main() {
    Q347.topKFrequent(intArrayOf(1), 1).also { it.forEach(::println) } 
}

class Q347 {
    companion object {
        fun topKFrequent(nums: IntArray, k: Int): IntArray {
        	val elementFrequency = HashMap<Int, Int>()
            
            nums.forEach {
                if (!elementFrequency.keys.contains(it)) elementFrequency.put(it, 0)
                elementFrequency[it] = elementFrequency[it]!! + 1
            }
            
            // thenByDescending: https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.comparisons/then-by-descending.html
           	val sorted = elementFrequency.keys.sortedWith<Int>(
            	compareBy<Int> { elementFrequency[it] }.thenByDescending { it }
            ).reversed()
            
          	return IntArray(k) { 
                sorted[it]
            }
    	}

        fun enhancedSolution(nums: IntArray, k: Int): IntArray {
            val frequencyByElement = HashMap<Int, Int>()
            
            nums.forEach { 
            	if (!frequencyByElement.keys.contains(it)) frequencyByElement.put(it, 0)
                frequencyByElement[it] = frequencyByElement[it]!! + 1
            }
            
            // Declare another HashMap that stores Element By Frequency
            val elementByFrequency = HashMap<Int, MutableList<Int>>()
            
            for ((k, v) in frequencyByElement) {
                if (!elementByFrequency.keys.contains(v)) elementByFrequency.put(v, mutableListOf<Int>())
                elementByFrequency[v]!!.add(k)
            }

            // Extract top K element
            var cnt = 0
            val result = mutableListOf<Int>()
            for (key in elementByFrequency.keys.reversed()) {
                
                for (element in elementByFrequency[key]!!) {
                    result.add(element).run { cnt++ }
                    if (cnt == k) return result.toIntArray()
                }
            }
            
            return intArrayOf()
        }
    }
}
