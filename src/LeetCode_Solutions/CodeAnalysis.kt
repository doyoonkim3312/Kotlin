package LeetCode_Solutions

class CodeAnalysis {
    companion object {
        fun executionTime(msg: String = "", action: () -> Unit) {
            val start = System.currentTimeMillis()
            action.invoke()
            val end = System.currentTimeMillis()

            println("$msg\n\t[EXECUTION TIME] ${String.format("%.3f", (end - start).toFloat())}ms.")
        }
    }
}