private const val DAY = "06"

fun main() {

    fun part1(input: List<String>): Long {
        var answer = 0L
        val ops = input.last().split(" ").filter { it.isNotEmpty() }.map { it.trim() }
        val nums = mutableListOf<List<Long>>()

        for (i in 0..<input.lastIndex) {
            nums.add(input[i].split(" ").filter { it.isNotEmpty() }.map { it.trim().toLong() })
        }
        ops.forEachIndexed { idx, op ->
            when (op) {
                "+" -> answer += nums.sumOf { it[idx] }
                "*" -> answer += nums.map { it[idx] }.reduce { a, b -> a * b }
            }
        }

        return answer
    }

    fun part2(input: List<String>): Long {
        var answer = 0L

        val ops = mutableListOf<IntRange>()
        var start = 0
        var end = 0
        val opsstr = input.last()
        while (end < opsstr.length) {
            if (opsstr[end] != ' ' && start != end) {
                ops.add(start..<end)
                start = end
            }
            end++
        }
        ops.add(start..end)
        val numsstr = input.subList(0, input.lastIndex)
        ops.forEach { op ->
            val ops = opsstr[op.first]
            val nums = numsstr.map { it.substring(op.first, op.last) }
            val longs = mutableListOf<Long>()
            for (i in 0..<nums.first().length) {
                var intstr = ""
                for (j in 0..<nums.size) {
                    intstr += nums[j][i]
                }
                longs.add(intstr.trim().toLong())
            }
            when (ops) {
                '+' -> answer += longs.sum()
                '*' -> answer += longs.reduce { a, b -> a * b }
            }
        }
        return answer
    }

    val testInput = readInput("Day${DAY}_test")
    checkAnMeasureTime(4277556) { part1(testInput) }
    checkAnMeasureTime(3263827) { part2(testInput) }

    val input = readInput("Day${DAY}")
    checkAnMeasureTime(5667835681547) { part1(input) }
    checkAnMeasureTime(9434900032651) { part2(input) }
}
