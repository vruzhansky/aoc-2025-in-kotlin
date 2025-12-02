private const val DAY = "02"

fun main() {

    fun isValid(num: Long): Boolean {
        val numstr = num.toString()
        if (numstr.length % 2 != 0) return false
        val mid = numstr.length / 2

        return (numstr.take(mid) == numstr.substring(mid))
    }

    fun part1(input: List<String>): Long {
        val ranges = input.first().split(",")
        var answer = 0L
        ranges.forEach { rangeStr ->
            val (start, end) = rangeStr.split("-")
            for (i in start.toLong()..end.toLong()) {
                if (isValid(i)) answer += i
            }
        }

        return answer
    }

    fun isValid2(num: Long): Boolean {
        val numstr = num.toString()
        val mid = numstr.length / 2
        for (i in 1..mid) {
            val pattern = numstr.take(i)
            val newstr = pattern.repeat(numstr.length / pattern.length)
            if (numstr == newstr) return true
        }

        return false
    }

    fun part2(input: List<String>): Long {
        val ranges = input.first().split(",")
        var answer = 0L
        ranges.forEach { rangeStr ->
            val (start, end) = rangeStr.split("-")
            for (i in start.toLong()..end.toLong()) {
                if (isValid2(i)) answer += i
            }
        }

        return answer
    }

    val testInput = readInput("Day${DAY}_test")
    checkAnMeasureTime(1227775554) { part1(testInput) }
    checkAnMeasureTime(4174379265) { part2(testInput) }

    val input = readInput("Day${DAY}")
    checkAnMeasureTime(43952536386) { part1(input) }
    checkAnMeasureTime(54486209192) { part2(input) }
}
