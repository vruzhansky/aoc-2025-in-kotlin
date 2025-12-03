private const val DAY = "03"

fun main() {

    fun find(digits: List<Int>, lim: Int = 2): Long {
        var limit = lim
        var answer = ""
        var idx = 0
        while (limit > 0) {
            var max = 0
            for (i in idx..digits.lastIndex - limit + 1) {
                if (digits[i] > max) {
                    max = digits[i]
                    idx = i + 1
                }
            }
            answer += digits[idx - 1]
            limit--
        }
        return answer.toLong()
    }

    fun part1(input: List<String>): Long {
        var answer = 0L
        input.forEach {
            answer += find(it.toCharArray().map { c -> c.digitToInt() })
        }

        return answer
    }

    fun part2(input: List<String>): Long {
        var answer = 0L
        input.forEach {
            answer += find(it.toCharArray().map { c -> c.digitToInt() }, 12)
        }
        return answer
    }

    val testInput = readInput("Day${DAY}_test")
    checkAnMeasureTime(357) { part1(testInput) }
    checkAnMeasureTime(3121910778619) { part2(testInput) }

    val input = readInput("Day${DAY}")
    checkAnMeasureTime(17100) { part1(input) }
    checkAnMeasureTime(170418192256861) { part2(input) }
}
