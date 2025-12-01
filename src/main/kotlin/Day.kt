import java.nio.file.Paths
import kotlin.io.path.Path
import kotlin.time.measureTimedValue

abstract class Day(private val day: Int) {
    abstract fun part1(input: List<String>): Any
    abstract fun part2(input: List<String>): Any

    fun solve() {
        val testData = loadTestData()
        val data = loadData()

        solvePart(1) { part1(testData) }
        solvePart(1) { part1(data) }

        solvePart(2) { part2(testData) }
        solvePart(2) { part2(data) }
    }

    private fun solvePart(part: Int, block: () -> Any) {
        val (result, time) = measureTimedValue(block)
        println(
            """
            Day : $day - Part $part
            Result : $result
            Time : $time
            
        """.trimIndent()
        )
    }

    private fun loadTestData(): List<String> {
        val path = Paths.get("src", "main", "resources", "Day${String.format("%02d", day)}", "sample.txt")
        return readInput(path)
    }

    fun loadData(): List<String> {
        val path = Paths.get("src", "main", "resources", "Day${String.format("%02d", day)}", "input.txt")
        return readInput(path)
    }
}