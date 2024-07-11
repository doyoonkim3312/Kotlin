package LeetCode_Solutions

/**
 * LeetCode 641: Design Circular Deque.
 */
fun main() {
//     val obj = MyCircularDeque(3)
    val obj = EnhancedCircularDeque(3)
    
    obj.insertLast(1).also { println(it) }
    obj.insertLast(2).also { println(it) }
    obj.insertFront(3).also { println(it) }
    obj.insertFront(4).also { println(it) }
    
    obj.getRear().also { println(it) }
    obj.isFull().also { println(it) }
    obj.deleteLast().also { println(it) }
    obj.insertFront(4).also { println(it) }
    obj.getFront().also { println(it) }
}

/**
 * Initial Attempt. (Utilize Doubley-Linked List with Custom-defined Node.)
 */
class MyCircularDeque(k: Int) {
    
    class Node(val item: Int, var prev: Node? = null, var next: Node? = null)
    
    /* Necessary Variable */
    private val size = k
    private var front: Node? = null
    private var last: Node? = null
    private var cnt = 0

    fun insertFront(value: Int): Boolean {
        return if (this.isFull()) {
            false
        } else {
            val oldFront = front
            front = Node(value).also {
                if (this.isEmpty()) last = front
            }
            
            front?.next = oldFront
            oldFront?.prev = front
            cnt++
            
            if (this.isFull()) {
                front?.prev = last
                last?.next = front
            }
            
            true
        }
    }

    fun insertLast(value: Int): Boolean {
        return if (this.isFull()) {
            false
        } else {
            val oldLast = last
            last = Node(value).also {
                if (this.isEmpty()) front = last
            }
            
            oldLast?.next = last
            last?.prev = oldLast
            cnt++
            
            if (this.isFull()) {
                front?.prev = last
                last?.next = front
            }
            
            true
        }
    }

    fun deleteFront(): Boolean {
        return if (this.isEmpty()) {
            false
        } else {
            front = front?.next
            
            // Clear our any references pointed to the target to be removed.
            front?.prev = null
            last?.next = null
            
            cnt--
            
            true
        }
    }

    fun deleteLast(): Boolean {
        return if (this.isEmpty()) {
            false
        } else {
            last = last?.prev
            
            // Clear out any references pointed to the target to be removed.
            last?.next = null
            front?.prev = null
            
            cnt--
            true
        }
    }

    fun getFront(): Int {
        return front?.item ?: -1
    }

    fun getRear(): Int {
        return last?.item ?: -1
    }

    fun isEmpty(): Boolean {
        return cnt == 0
    }

    fun isFull(): Boolean {
        return cnt == size
    }

}


// Improved Solution
// "LinkedList is a data-type where an empty space does not exists."
class EnhancedCircularDeque(k: Int) {
    // Nested class defined as a static-nested class by default.package
    class EnhancedNode(val item: Int, var left: EnhancedNode? = null, var right: EnhancedNode? = null)
    
    private var head: EnhancedNode = EnhancedNode(-1)		// Dummy Node.
    private var tail: EnhancedNode = EnhancedNode(-1)		// Dummy Node.
    private var len: Int = 0
    private var size = k
    
    init {
        // Make a linkage between head and tail. Head & tail would play a role of start/end points.
        head.left = tail.also { it.right = head }
        head.right = tail.also { it.left = head }
    }
    
    fun insertFront(value: Int): Boolean {
        if (len == size) return false		// Early-return
        else {
            val temp = EnhancedNode(value)
            
            temp.right = head.right
            head.right?.left = temp
            temp.left = head
            head.right = temp
            
            len++
            return true
        }
    }
    
    fun insertLast(value: Int): Boolean {
        if (len == size) return false		// Early-return
        else {
            val temp = EnhancedNode(value)
            
            temp.left = tail.left
            tail.left?.right = temp
            temp.right = tail.also { it.left = temp }
            
            len++
            return true
        }
    }
    
    fun deleteFront(): Boolean {
        if (len == 0) return false		// Early-return
        else {
            head.right = head.right?.right?.also { it.left = head }
            len--
            
            return true
        }
    }
    
    fun deleteLast(): Boolean {
        if (len == 0) return false		// Early-return
        else {
            tail.left = tail.left?.left?.also { it.right = tail }
            len--
            
            return true
        }
    }
    
    fun getFront(): Int {
        return head.right?.item ?: -1
    }
    
    fun getRear(): Int {
        return tail.left?.item ?: -1
    }
    
    fun isEmpty(): Boolean {
        return len == 0
    }
    
    fun isFull(): Boolean {
        return len == size
    }
    
}
