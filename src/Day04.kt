import utils.Point
import utils.getAt
import utils.inGrid
import utils.toCharGrid

private const val DAY = "04"

fun main() {

    fun part1(input: List<String>): Int {
        var answer = 0
        val grid = input.toCharGrid()
        grid.forEachIndexed { r, row ->
            row.forEachIndexed { c, char ->
                if (char == '@') {
                    val count = Point(r, c).directedNeighbours2()
                        .map { it.value }
                        .filter { grid.inGrid(it) }
                        .count { grid.getAt(it) == '@' }

                    if (count < 4) answer++
                }
            }
        }
        return answer
    }

    fun withRemoved(grid: List<List<Char>>, removed: Set<Point>): Set<Point> {
        val newRemoved = mutableSetOf<Point>()
        grid.forEachIndexed { r, row ->
            row.forEachIndexed { c, char ->
                val point = Point(r, c)
                if (char == '@' && !removed.contains(point)) {
                    val count = point.directedNeighbours2()
                        .map { it.value }
                        .filter { grid.inGrid(it) }
                        .count { grid.getAt(it) == '@' && !removed.contains(it) }

                    if (count < 4) newRemoved.add(point)
                }
            }
        }
        return newRemoved
    }

    fun part2(input: List<String>): Int {
        var answer = 0
        val grid = input.toCharGrid()
        val removed = mutableSetOf<Point>()
        do {
            val newRemoved = withRemoved(grid, removed)
            removed.addAll(newRemoved)
            answer += newRemoved.size
        } while (newRemoved.isNotEmpty())

        return answer
    }

    val testInput = readInput("Day${DAY}_test")
    checkAnMeasureTime(13) { part1(testInput) }
    checkAnMeasureTime(43) { part2(testInput) }

    val input = readInput("Day${DAY}")
    checkAnMeasureTime(1356) { part1(input) }
    checkAnMeasureTime(8713) { part2(input) }
}
