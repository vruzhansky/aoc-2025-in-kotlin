private const val DAY = "11"


fun main() {
    
    fun dfs(curr: String, target: String, memo: MutableMap<String, Long>, devices: Map<String, List<String>>): Long {
        if (curr == target) {
            return 1
        }
        if (memo.containsKey(curr)) {
            return memo[curr]!!
        }
        
        val count = devices[curr]?.sumOf { dfs(it, target, memo, devices) } ?: 0
        memo[curr] = count
        return memo[curr]!!
    }
    
    fun part1(input: List<String>): Long {
        val devices = mutableMapOf<String, List<String>>()
        input.forEach {
            val (device, connected) = it.split(": ")
            devices[device] = connected.split(" ")
        }
        return dfs("you", "out", mutableMapOf(), devices)
    }
    
    fun part2(input: List<String>): Long {
        val devices = mutableMapOf<String, List<String>>()
        input.forEach {
            val (device, connected) = it.split(": ")
            devices[device] = connected.split(" ")
        }
        val fft = dfs("svr", "fft", mutableMapOf(), devices)
        println("fft $fft")
        val dac = dfs("fft", "dac", mutableMapOf(), devices)
        println("dac $dac")
        val out = dfs("dac", "out", mutableMapOf(), devices)
        println("out $out")
        val dac2 = dfs("svr", "dac", mutableMapOf(), devices)
        println("dac2 $dac2")
        val fft2 = dfs("dac", "fft", mutableMapOf(), devices)
        println("fft2 $fft2")
        val out2 = dfs("fft", "out", mutableMapOf(), devices)
        println("out2 $out2")
        return fft*dac*out + dac2*fft2*out2
    }

    val testInput = readInput("Day${DAY}_test")
//    checkAnMeasureTime(5) { part1(testInput) }
    checkAnMeasureTime(2) { part2(testInput) }

    val input = readInput("Day${DAY}")
//    checkAnMeasureTime(758) { part1(input) }
    checkAnMeasureTime(490695961032000) { part2(input) }
}
