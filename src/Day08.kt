import kotlin.math.pow
import kotlin.math.sqrt

private const val DAY = "08"


fun main() {
    data class Point(val x: Int, val y: Int, val z: Int)

    fun dist(p1: Point, p2: Point): Double {
        return sqrt(
            (p2.x - p1.x).toDouble().pow(2.0) + (p1.y - p2.y).toDouble().pow(2.0) + (p1.z - p2.z).toDouble().pow(2.0)
        )
    }

    fun part1(input: List<String>): Long {
        val points = input.map {
            val (x, y, z) = it.split(",")
            Point(x.toInt(), y.toInt(), z.toInt())
        }
        val expandedPoints = mutableListOf<Pair<Point, Point>>()
        for (i in 0 until points.size) {
            for (j in i + 1 until points.size) {
                expandedPoints.add(Pair(points[i], points[j]))
            }
        }
        expandedPoints.sortBy { dist(it.first, it.second) }
        val circuits = mutableListOf<MutableSet<Point>>()
        for (pair in expandedPoints.take(1000)) {
            if (!circuits.any { it.contains(pair.first) && it.contains(pair.second) }) {
                val first = circuits.firstOrNull { it.contains(pair.first) }
                val second = circuits.firstOrNull { it.contains(pair.second) }

                // merge
                if (first != null && second != null) {
                    first.addAll(second)
                    circuits.remove(second)
                } else if (first != null) {
                    first.add(pair.second)
                } else if (second != null) {
                    second.add(pair.first)
                } else {
                    circuits.add(mutableSetOf(pair.first, pair.second))
                }
            }
        }
        return circuits.sortedByDescending { it.size }.take(3).map { it.size }.reduce { a, b -> a * b }.toLong()
    }

    fun part2(input: List<String>): Long {
        var answer = 0L
        val points = input.map {
            val (x, y, z) = it.split(",")
            Point(x.toInt(), y.toInt(), z.toInt())
        }
        val expandedPoints = mutableListOf<Pair<Point, Point>>()
        for (i in 0 until points.size) {
            for (j in i + 1 until points.size) {
                expandedPoints.add(Pair(points[i], points[j]))
            }
        }
        expandedPoints.sortBy { dist(it.first, it.second) }
        val circuits = mutableListOf<MutableSet<Point>>()
        for (pair in expandedPoints) {
            if (!circuits.any { it.contains(pair.first) && it.contains(pair.second) }) {
                val first = circuits.firstOrNull { it.contains(pair.first) }
                val second = circuits.firstOrNull { it.contains(pair.second) }

                // merge
                if (first != null && second != null) {
                    first.addAll(second)
                    circuits.remove(second)
                } else if (first != null) {
                    first.add(pair.second)
                } else if (second != null) {
                    second.add(pair.first)
                } else {
                    circuits.add(mutableSetOf(pair.first, pair.second))
                }
            }
            if (circuits.size == 1 && circuits.first().size == points.size) {
                answer = pair.first.x.toLong() * pair.second.x.toLong()
                break
            }
        }
        return answer
    }

    val testInput = readInput("Day${DAY}_test")
//    checkAnMeasureTime(40) { part1(testInput) }
    checkAnMeasureTime(25272) { part2(testInput) }

    val input = readInput("Day${DAY}")
    checkAnMeasureTime(42315) { part1(input) }
    checkAnMeasureTime(8079278220) { part2(input) }
}
