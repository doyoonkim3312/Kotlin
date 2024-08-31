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
            
            // Sort HashMap by its values: https://stackoverflow.com/questions/51021580/kotlin-sort-hashmap-in-descending-order
            elementFrequency.toSortedMap( compareByDescending { it } )
            
          	return IntArray(k) { 
                elementFrequency.keys.toList()[it]
            }
    	}
    }
}
