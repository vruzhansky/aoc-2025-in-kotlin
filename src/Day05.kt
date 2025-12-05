import kotlin.math.max

private const val DAY = "05"

fun main() {

    fun part1(input: List<String>): Long {
        var answer = 0L
        val ranges = mutableListOf<LongRange>()
        val ids = mutableSetOf<Long>()
        input.forEach { line ->
            if (line.contains('-')) {
                val (start, end) = line.split("-")
                ranges.add(start.toLong()..end.toLong())
            } else if (line.isNotEmpty()) {
                ids.add(line.toLong())
            }
        }
        ids.forEach { id ->
            for (range in ranges) {
                if (range.contains(id)) {
                    answer++
                    break
                }
            }
        }

        return answer
    }

    fun part2(input: List<String>): Long {
        var answer = 0L
        val ranges = mutableListOf<LongRange>()
        input.forEach { line ->
            if (line.contains('-')) {
                val (start, end) = line.split("-")
                ranges.add(start.toLong()..end.toLong())
            }
        }
        ranges.sortBy { it.first }
        val mergedRanges = mutableListOf<LongRange>()
        var current: LongRange? = null
        for (range in ranges) {
            if (current == null) {
                current = range
            } else {
                if (range.first in current) {
                    val end = max(current.last, range.last)
                    current = current.first..end
                } else {
                    mergedRanges.add(current)
                    current = range
                }
            }
        }
        mergedRanges.add(current!!)
        mergedRanges.forEach { merged ->
            answer += merged.last - merged.first + 1
        }
        return answer
    }

    val testInput = readInput("Day${DAY}_test")
    checkAnMeasureTime(3) { part1(testInput) }
    checkAnMeasureTime(14) { part2(testInput) }

    val input = readInput("Day${DAY}")
    checkAnMeasureTime(517) { part1(input) }
    checkAnMeasureTime(336173027056994) { part2(input) }
}
