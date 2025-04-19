package dodobow_coding_club.questions

// combination
fun combination(arr: List<Pair<Int, Int>>, n: Int): List<List<Pair<Int, Int>>> {
    if (n == 0) return listOf(emptyList())
    if (arr.isEmpty()) return emptyList()

    val result = mutableListOf<List<Pair<Int, Int>>>()
    for (i in arr.indices) {
        val selection = arr[i]
        val remaining = arr.drop(i + 1)
        for (c in combination(remaining, n - 1)) {
            result.add(listOf(selection) + c)
        }
    }
    return result
}