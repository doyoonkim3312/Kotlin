package LeetCode_Solutions

fun main() {

}

fun q225InitialSolution() {

}


/**
 * Your MyStack object will be instantiated and called as such:
 * var obj = MyStack()
 * obj.push(x)
 * var param_2 = obj.pop()
 * var param_3 = obj.top()
 * var param_4 = obj.empty()
 */

// Initial Attempt (062724; Should be validated)
class MyStack() {

    // addLast, removeFirst.
    private val queue = ArrayDeque<Int>()

    fun push(x: Int) {
        queue.addLast(x)
    }

    fun pop(): Int {
        val temp = ArrayDeque<Int>()
        while (queue.size > 1) {
            temp.addLast(queue.removeFirst())
        }
        val result = queue.removeFirst()

        while (temp.isNotEmpty()) {
            queue.addLast(temp.removeFirst())
        }

        return result
    }

    fun top(): Int {
        val result = this.pop()
        push(result)
    }

    fun empty(): Boolean {
        return queue.isEmpty()
    }

}