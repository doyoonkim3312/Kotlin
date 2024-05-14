package LeetCode_Solutions

fun main() {
    val l1 = ListNode(1)
    l1.next = ListNode(2)
    l1.next!!.next = ListNode(3)
    l1.next!!.next!!.next = ListNode(4)

    val nullNode = null
    val singleNode = ListNode(1)

    CodeAnalysis.executionTime {
        var result = solution_recursive(l1)

        while (result != null) {
            print("${result.`val`} ")
            result = result.next
        }
    }
}

fun solution_initial(head: ListNode?): ListNode? {
    // Exceptional Case: provided node has one or less than one node.
    if (head?.next == null) return head

    var tracking: ListNode? = head
    var endOfPair: ListNode = ListNode(0)          // Instantiate with temporary node.
    val result = endOfPair

    while (tracking?.next != null) {
        // Declare temporary nodes.
        val nodeA = tracking
        val nodeB = tracking.next

        val oldNextNodeB = nodeB?.next
        // Swap
        nodeB?.next = nodeA
        nodeA.next = null

        endOfPair.next = nodeB
        endOfPair = nodeA

        tracking = oldNextNodeB
    }

    // Hanlde odd number of LinkedList.
    if (tracking != null) endOfPair.next = tracking

    // Skip temporary node.
    return result.next
}

fun solution_improved(head: ListNode?): ListNode? {
    // Exceptional Case Handling: Provieded node is null or just have a signle node.
    if (head?.next == null) return head

    val result = ListNode(0).also { it.next = head }    // Instantiate with temorary node.
    var node = result

    while (node.next != null && node.next?.next != null) {
        // node -> a -> b -> c
        val a = node.next
        val b = node.next?.next

        // a -> c
        a?.next = b?.next
        // b -> a -> c
        b?.next = a
        // node -> b -> a -> c
        node.next = b

        // move node for next swap
        node = node.next?.next!!       // at this point, node.next.next has to be non-null.
    }

    // Skip the temporary node.
    return result.next
}

fun solution_recursive(head: ListNode?): ListNode? {

    if(head?.next != null) {
        // A -> B -> C

        val result = head.next
        // A -> C
        head.next = solution_recursive(head.next?.next)
        // B -> A
        result?.next = head

        return result
    }
    return head
}
