import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min
import kotlin.math.pow
import kotlin.math.sqrt

private const val DAY = "09"


fun main() {
    data class Point(val x: Int, val y: Int)

    fun area(p1: Point, p2: Point): Long {
        return (abs(p1.x - p2.x) + 1).toLong() * (abs(p1.y - p2.y) + 1).toLong()
    }

    fun part1(input: List<String>): Long {
        var answer = 0L
        val points = input.map {
            val (x, y) = it.split(",")
            Point(x.toInt(), y.toInt())
        }
        for (i in 0 until points.size) {
            for (j in i + 1 until points.size) {
                answer = max(answer, area(points[i], points[j]))
            }
        }
        return answer
    }

    fun part2(input: List<String>): Long {
        var answer = 0L
        val points = input.map {
            val (x, y) = it.split(",")
            Point(x.toInt(), y.toInt())
        }
        val rects = mutableListOf<Pair<Point, Point>>()
        for (i in 0 until points.size) {
            for (j in i + 1 until points.size) {
                val p1 = points[i]
                val p2 = points[j]
                rects.add(Point(min(p1.x, p2.x), min(p1.y, p2.y)) to Point(max(p1.x, p2.x), max(p1.y, p2.y)))
            }
        }
        val rectsByArea = rects.sortedByDescending { area(it.first, it.second) }
        val perimeter = mutableListOf<Pair<Point, Point>>()
        for (i in 0 until points.size) {
            val j = if (i == points.size - 1) 0 else i + 1
            perimeter.add(Pair(points[i], points[j]))
        }
        for (rect in rectsByArea) {
            if (perimeter.none {
                    val lxmin = min(it.first.x, it.second.x)
                    val lymin = min(it.first.y, it.second.y)
                    val lxmax = max(it.first.x, it.second.x)
                    val lymax = max(it.first.y, it.second.y)
                    var cross = false
                    // vertical line
                    if (lxmin == lxmax && rect.first.x < lxmin && rect.second.x > lxmin) {
                        cross = (rect.first.y in lymin..<lymax) || (rect.second.y in (lymin + 1)..lymax)
                    }
                    // horizontal line
                    if (lymin == lymax && rect.first.y < lymin && rect.second.y > lymin) {
                        cross = (rect.first.x in lxmin..<lxmax) || (rect.second.x in (lxmin + 1)..lxmax)
                    }
                    cross
                }) {
                answer = area(rect.first, rect.second)
                break
            }

        }
        return answer
    }

    val testInput = readInput("Day${DAY}_test")
    checkAnMeasureTime(50) { part1(testInput) }
    checkAnMeasureTime(24) { part2(testInput) }

    val input = readInput("Day${DAY}")
    checkAnMeasureTime(4772103936) { part1(input) }
    checkAnMeasureTime(1529675217) { part2(input) }
}
