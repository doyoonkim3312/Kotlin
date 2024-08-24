package LeetCode_Solutions

class CodeAnalysis {
    companion object {
        fun executionTime(msg: String = "", action: () -> Unit) {
            val start = System.currentTimeMillis()
            action.invoke()
            val end = System.currentTimeMillis()

            println("\n----------\tAnalysis Info\t----------")
            println("\n$msg\n[EXECUTION TIME] ${String.format("%.3f", (end - start).toFloat())}ms.")
            println("\n----------\tAnalysis End\t----------")
        }
    }
}