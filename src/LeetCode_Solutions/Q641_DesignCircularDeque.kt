package LeetCode_Solutions

/**
 * LeetCode 641: Design Circular Deque.
 */
fun main() {
    val obj = MyCircularDeque(3)
    
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
