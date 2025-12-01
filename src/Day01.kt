import kotlin.math.abs

private const val DAY = "01"

fun main() {

    fun part1(input: List<String>): Int {
        var answer = 0
        var next = 50
        input.forEach {
            val dir = it.first()
            val dist = it.substring(1).toInt()
            next = when (dir) {
                'L' -> next - dist
                'R' -> next + dist
                else -> throw IllegalArgumentException("Unknown direction: $dir")
            } % 100
            if (next == 0) answer++
        }
        return answer
    }

    fun part2(input: List<String>): Int {
        var answer = 0
        var prev = 50
        var next = prev
        input.forEach {
            val dir = it.first()
            val dist = it.substring(1).toInt()
            next = when (dir) {
                'L' -> next - dist
                'R' -> next + dist
                else -> throw IllegalArgumentException("Unknown direction: $dir")
            }
            answer += abs(next / 100)
            if (prev < 0 && next > 0 || prev > 0 && next < 0 || next == 0) answer++
            next %= 100
            prev = next
        }
        return answer
    }

    val testInput = readInput("Day${DAY}_test")
    checkAnMeasureTime(3) { part1(testInput) }
    checkAnMeasureTime(6) { part2(testInput) }

    val input = readInput("Day${DAY}")
    checkAnMeasureTime(1007) { part1(input) }
    checkAnMeasureTime(5820) { part2(input) }
}
