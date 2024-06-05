package LeetCode_Solutions

/**
 * Q92_ReverseLinkedList_Two
 *
 * Description: Given the head of a singly linked list and two integers left and right where left <= right, reverse the
 * nodes of the list from position left to position right, and return the reversed list.
 */

fun main() {

    val testSet = ListNode(3)
    testSet.next = ListNode(5)
//    testSet.next?.next = ListNode(5)
//    testSet.next?.next?.next = ListNode(4)
//    testSet.next?.next?.next?.next = ListNode(5)
//    testSet.next?.next?.next?.next?.next = ListNode(6)
//    testSet.next?.next?.next?.next?.next?.next = ListNode(7)

    /*
    var result = reverseLinkedList(testSet, null)
    while (result != null) {
        print("${result.`val`} ")
        result = result.next
    }
     */

    var result = reverseBetween(testSet, 1, 2)
    while (result != null) {
        print("${result.`val`} ")
        result = result.next
    }

}

fun q92InitialSolution(head: ListNode?, left: Int, right: Int): ListNode? {
    if (head == null || left == right) return head

    val h = head
    var tracking = head

    var targetTracking: ListNode
    val targetHead = ListNode(0).also {
        targetTracking = it
    }

    var prev: ListNode? = null
    var cnt = 1

    while (tracking != null) {
        /*
        if (cnt == left - 1) st = prev
        if (cnt == right + 1) {
            // call reverseLinkedList
            targetTracking.next = null
            val reversed = reverseLinkedList(targetHead.next, null)
            targetHead.next?.next = tracking


            prev?.next = reversed
            return h
        }
         */
        if (cnt in left..right) {
            Log.d(msg = "Current Tracking :${tracking.`val`}")
            targetTracking.next = tracking
            targetTracking = targetTracking.next!!

            if (cnt == right) {
                val remainingHead = tracking.next
                targetTracking.next = null

                val reversed = reverseLinkedList(targetHead.next, null)
                Log.d(msg="${tracking.next?.`val`}")
                targetHead.next?.next = remainingHead

                if (prev == null) return reversed
                else prev.next = reversed
//                if (prev != null) prev.next = reversed
                return h
            }

        } else {
            prev = tracking
        }

        tracking = tracking.next
        cnt++
    }

    return null

}

// Solution Accepted.
fun reverseBetween(head: ListNode?, left: Int, right: Int): ListNode? {
    if (head == null || left == right) return head

    val h = head
    var tracking = h

    val targetHead = ListNode(0)
    var targetTracking = targetHead

    var prev: ListNode? = null
    var cnt = 1

    while (tracking != null) {
        if (cnt in left..right) {
            targetTracking.next = tracking
            targetTracking = targetTracking.next!!

            if (cnt == right) {
                val next = tracking.next
                targetTracking.next = null

                val reversed = reverseLinkedList(targetHead.next, null)
                targetHead.next?.next = next

                if (prev == null) return reversed
                else prev.next = reversed

                return h
            }
        } else {
            prev = tracking
        }

        tracking = tracking.next
        cnt++
    }

    return null
}


fun reverseLinkedList(head: ListNode?, prev: ListNode?): ListNode? {
    Log.d(msg = "reverseLinkedList has been called; head: ${head?.`val`} prev: ${prev?.`val`}")
    if (head == null) return prev

    val oldNext = head.next
    head.next = prev
    return reverseLinkedList(oldNext, head)
}