package LeetCode_Solutions
/**
* Leet Code Q622: DesignCircularQueue
*/

fun main() {
    val test = CircularQueue(5)
    println(test.rear())
    test.enqueue(10)
    test.enqueue(20)
    test.enqueue(30)
    test.enqueue(40)

    println(test.rear())

    test.dequeue()
    test.dequeue()
    test.enqueue(50)
    test.enqueue(60)

    println(test.rear())

    test.enqueue(70)
    println(test.isFull())
    println(test.front())

}

class CircularQueue(val size: Int) {
    class Node(val item: Int, var next: Node? = null)
    
    private var front: Node? = null
    private var rear: Node? = null
    private var cnt = 0
    
    fun enqueue(n: Int): Boolean {
        if (cnt >= size) return false
        if (cnt == 0) {
            front = Node(n).also { rear = it }
        } else {
            rear?.next = Node(n).also { rear = it }
        }
        
        cnt++
        if (cnt == size) rear?.next = front
        return true
    }
    
    fun dequeue(): Boolean {
        if (cnt < 1) return false
        
        front = front?.next
        rear?.next = null
        cnt--
        
        if (cnt == 0) rear = front
        return true
    }
    
    fun rear(): Int? = rear?.item ?: -1
    fun front(): Int? = front?.item ?: -1
    
    fun isFull(): Boolean {
        return cnt == size
    }
    
    fun isEmpty(): Boolean {
        return cnt == 0
    }
    
}
