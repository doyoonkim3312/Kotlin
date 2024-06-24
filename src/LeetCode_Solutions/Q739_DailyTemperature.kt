package LeetCode_Solutions

import java.util.ArrayDeque

fun main() {
    val test = arrayOf(30,60,90)

    CodeAnalysis.executionTime {
        println(revisedSolution(test).forEach {
          print("$it ")
        })
        println()
    }
}


fun revisedSolution(arr: Array<Int>) : Array<Int> {
    // transform Array<Int> to Array<Pair<Int, Int>>
    val input = Array<Pair<Int, Int>>(arr.size) {
        Pair(arr[it], it)
    }       // can be enhanced by using .transform lambda expression

    val stack = ArrayDeque<Pair<Int, Int>>()
    val result = MutableList<Int>(arr.size) { 0 }

    for (temperature in input) {
        var peek = stack.lastOrNull()

        while (peek != null && peek.first < temperature.first) {
            val t = stack.removeLast()

            result[t.second] = temperature.second - t.second
            peek = stack.lastOrNull()
        }

        stack.addLast(temperature)
    }

    return result.toTypedArray()
}