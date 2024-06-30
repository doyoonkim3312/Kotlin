package LeetCode_Solutions

fun main() {
    var obj = MyQueue()
    obj.push(1)
    obj.push(2)
    obj.push(3)

    println(obj.pop())
    println(obj.peek())
    println(obj.empty())
}

/**
 * MyQueue
 *
 * Actual implementation of custom Queue using Stack internally. (Use addLast, removeLast only.)
 *
 * push(x): Pushes element x to the back of the queue.
 * pop(): Removes the element from the front of the queue and returns it.
 * peek(): Returns the element at the front of the queue.
 * empty(): Returns true if the queue is empty, false otherwise.
 *
 * Your MyQueue object will be instantiated and called as such:
 * var obj = MyQueue()
 * obj.push(x)
 * var param_2 = obj.pop()
 * var param_3 = obj.peek()
 * var param_4 = obj.empty()
 */
class MyQueue() {
    // Declare Stack as private value.
    private val stack = ArrayDeque<Int>()
    private val temp = ArrayDeque<Int>()

    fun push(x: Int) {
        stack.addLast(x)
    }

    fun pop(): Int {
        while(stack.isNotEmpty()) {
            temp.addLast(stack.removeLast())
        }

        return temp.removeLast().also {
            while(temp.isNotEmpty()) {
                stack.addLast(temp.removeLast())
            }
        }
    }

    fun peek(): Int {
        while(stack.isNotEmpty()) {
            temp.addLast(stack.removeLast())
        }

        return temp.removeLast().also {
            stack.addLast(it)
            while(temp.isNotEmpty()) {
                stack.addLast(temp.removeLast())
            }
        }
    }

    fun empty(): Boolean {
        return stack.isEmpty()
    }

}

/**

 */