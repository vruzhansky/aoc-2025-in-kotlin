import utils.Direction
import utils.Point
import utils.getAt
import utils.inGrid
import utils.toCharGrid
import java.util.Stack

private const val DAY = "07"

fun main() {

    fun part1(input: List<String>): Long {
        var answer = 0L
        val grid = input.toCharGrid()
        val stack = Stack<Point>()
        val visited = mutableSetOf<Point>()
        stack.push(Point(0, grid.first().size / 2))
        while (stack.isNotEmpty()) {
            val point = stack.pop().move(Direction.D)
            if (grid.inGrid(point) && !visited.contains(point)) {
                val char = grid.getAt(point)
                when (char) {
                    '.' -> {
                        visited.add(point)
                        stack.push(point)
                    }

                    '^' -> {
                        val left = point.move(Direction.L)
                        val right = point.move(Direction.R)
                        if (grid.inGrid(left) && !visited.contains(left) && grid.getAt(left) == '.') {
                            visited.add(left)
                            stack.push(left)
                        }
                        if (grid.inGrid(right) && !visited.contains(right) && grid.getAt(right) == '.') {
                            visited.add(right)
                            stack.push(right)
                        }
                        answer++
                    }
                }
            }
        }
        return answer
    }

    fun part2(input: List<String>): Long {
        val grid = input.toCharGrid()
        val start = Point(0, grid.first().size / 2)
        var map = mutableMapOf<Point, Long>()
        map[start] = 1
        var i = 1
        while (i < input.size) {
            val newMap = mutableMapOf<Point, Long>()
            map.forEach { (point, count) ->
                val down = point.move(Direction.D)
                if (grid.inGrid(down)) {
                    val char = grid.getAt(down)
                    when (char) {
                        '.' -> newMap.merge(down, count) { old, new -> old + new }
                        '^' -> {
                            val left = down.move(Direction.L)
                            val right = down.move(Direction.R)
                            if (grid.inGrid(left) && grid.getAt(left) == '.') {
                                newMap.merge(left, count) { old, new -> old + new }
                            }
                            if (grid.inGrid(right) && grid.getAt(right) == '.') {
                                newMap.merge(right, count) { old, new -> old + new }
                            }
                        }
                    }
                }
            }
            map = newMap
            i++
        }
        return map.values.sum()
    }

    val testInput = readInput("Day${DAY}_test")
    checkAnMeasureTime(21) { part1(testInput) }
    checkAnMeasureTime(40) { part2(testInput) }

    val input = readInput("Day${DAY}")
    checkAnMeasureTime(1609) { part1(input) }
    checkAnMeasureTime(12472142047197) { part2(input) }
}
