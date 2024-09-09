import java.util.HashMap
/**
 * Q34: Incomplete Runners
 * With given information: participant and completion, Return those who fail to compete the marathon. 
 * https://school.programmers.co.kr/learn/courses/30/lessons/42576?language=java
 */
fun main() {
    Q34.solution(arrayOf("mislav", "stanko", "mislav", "ana"), arrayOf("stanko", "ana", "mislav")).also { println(it) }
}

class Q34 {
    companion object {
        fun solution(participant: Array<String>, completion: Array<String>): String {
            val validate = HashMap<String, Int>()
            
            for (runner in participant) {
                validate.put(runner, validate.getOrDefault(runner, 0) + 1)
            }
            
            for (completedRunner in completion) {
                validate[completedRunner] = validate[completedRunner]!! - 1
                if (validate[completedRunner] == 0) validate.remove(completedRunner)
            }
            
            return validate.keys.elementAt(0) ?: ""
        }
    }
}
