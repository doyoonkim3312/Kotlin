/**
 * Example:
 * var li = ListNode(5)
 * var v = li.`val`
 * Definition for singly-linked list.
 * class ListNode(var `val`: Int) {
 *     var next: ListNode? = null
 * }
 */
class Q23_MergeKSortedList {
    // Use expendible collection-type (Mutable List)
    val pq = mutableListOf<ListNode>().also { it.add(ListNode(0)) }
    var size = 0
    
    fun mergeKLists(lists: Array<ListNode?>): ListNode? {
        if (lists.size == 0) return null
        for (node in lists) {
            var temp = node
            while(temp != null) {
                add(temp)
                temp = temp.next
            }
        }
        
        
        return pqToLinkedList()
    }
    
    // Add element to Priority Queue
    fun add(n: ListNode) {
        pq.add(n)
        swim(++size)
    }
    
    // remove element from the top
    fun remove(): ListNode? {
        if (size == 0) return null
        
        val result = pq[1]
        pq[1] = pq[size]		// Also decrease size.
        pq.removeAt(size--)
        sink(1)
        
        return result
    }
    
    // Operations for implementing Priority-Queue
    // Swim (bottom-to-top.)
    fun swim(i: Int) {
        var k = i
        while(k > 1 && less(k, k/2)) {
            exchange(k, k/2)
            k = k/2
        }
    }
    
    //sink (top-to-bottom)
    fun sink(i: Int) {
        var k = i
        while (2 * k <= size) {
            var j = 2 * k
            if (j < size && less(j + 1, j)) j++
            if (!less(j, k)) break
            exchange(k, j)
            k = j
        }
    }
    
    // Function to compre value in each node.
    fun less(k: Int, l: Int): Boolean {
        // Need to clearify why ls or eq does not work properly.
        return pq[k].`val` < pq[l].`val`
    }
    
    // Function to exchange each node in Priority Queue
    fun exchange(k: Int, l: Int) {
        val temp = pq[k]
        pq[k] = pq[l]
        pq[l] = temp
    }
    
    private fun pqToLinkedList(): ListNode? {
        if (size == 0) return null
        
        // Copy current pq
        val temp = mutableListOf<ListNode>()
      	
        val head = remove().also { 
            it?.next = null
            if (it != null) temp.add(it!!)
        }
        var tracking = head
        
        while (size > 0 && tracking != null) {
            val node = remove().also { 
                it?.next = null
                temp.add(it!!)
            }
            tracking.next = node
            tracking = node
        }
        
        
        pq.addAll(temp)
		size = pq.size
        return head
    }
    
}

class ListNode(
    val `val`: Int,
    var next: ListNode? = null
)
