package LeetCode_Solutions

/*
    LeetCode Q.328: Odd Even Linked List.
    Given the head of a singly linked list, group all the nodes with odd indices together followed by the nodes with
    even indices, and return the reordered list. The first node is considered odd, and the second node is even, and so on.
    Note that the relative order inside both the even and odd groups should remain as it was in the input.

    You must solve the problem in O(1) extra space complexity and O(n) time complexity.
 */

fun main() {
    val l1 = ListNode(1)
    l1.next = ListNode(2)
    l1.next?.next = ListNode(3)
    l1.next?.next?.next = ListNode(4)
    l1.next?.next?.next?.next = ListNode(5)

    CodeAnalysis.executionTime("Initial Approach") {
        var result = solution_initial_328(l1)

        while (result?.next != null) {
            print("${result.`val`} ")
            result = result.next
        }
    }
}

fun solution_initial_328(head: ListNode?): ListNode? {
    // Pointer that traverse given linked list.
    var pt = head       // Initialize with Head.

    // Head of each linked list. (Odd & Even)
    val oddHead = ListNode(0)
    val evenHead = ListNode(0)

    var trackingOdd = oddHead
    var trackingEven = evenHead

    while (pt?.next != null) {
        trackingOdd.next = pt
        trackingEven.next = pt.next

        trackingOdd = trackingOdd.next!!
        trackingEven = trackingEven.next!!

        // Move pt.
        pt = pt.next?.next
    }

    if (pt != null) {
        println(pt.`val`)
        println(trackingOdd.`val`)

        trackingOdd.next = pt
        trackingOdd = trackingOdd.next!!
    }

    trackingOdd.next = evenHead.next
    return oddHead.next
}