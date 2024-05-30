package LeetCode_Solutions

/**
 * Q92_ReverseLinkedList_Two
 *
 * Description: Given the head of a singly linked list and two integers left and right where left <= right, reverse the
 * nodes of the list from position left to position right, and return the reversed list.
 */

fun main() {

    val testSet = ListNode(1)
    testSet.next = ListNode(2)
    testSet.next?.next = ListNode(3)
    testSet.next?.next?.next = ListNode(4)

    var result = reverseLinkedList(testSet, null)
    while (result != null) {
        print("${result.`val`} ")
        result = result.next
    }

}

fun q92InitialSolution(head: ListNode?, left: Int, right: Int): ListNode? {
    var h = head
    var tracking = head

    var targetTracking = ListNode(0)
    val targetHead = targetTracking

    var st: ListNode? = null

    var cnt = 1

    while (tracking?.next != null) {
        if (cnt == left - 1) st = tracking
        if (cnt in left..right) {
            targetTracking.next = tracking

            targetTracking = targetTracking.next!!
            tracking = tracking.next
        } else {
            tracking = tracking.next
        }
    }

    return null

}


fun reverseLinkedList(head: ListNode?, prev: ListNode?): ListNode? {
    if (head == null) return prev

    val oldNext = head.next
    head.next = prev
    return reverseLinkedList(oldNext, head)
}