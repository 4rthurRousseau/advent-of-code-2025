class Day06 : Day(6) {

    val pattern = "( ){2,}".toRegex()

    override fun part1(input: List<String>): Any {
        val problems = input.toProblems()

        return problems.sumOf { it.solve() }
    }

    override fun part2(input: List<String>): Any {
        return 0
    }

    fun List<String>.toProblems(): List<Problem> {
        val grid = this.map {
            it.replace(pattern, " ").trim().split(" ")
        }

        return (0..grid[0].lastIndex).map { col ->
            val numbers = (0..<grid.lastIndex).map { row ->
                grid[row][col].toLong()
            }

            val operation = grid.last()[col]

            Problem(operation = operation, numbers = numbers)
        }
    }

    data class Problem(
        val operation: String,
        val numbers: List<Long>
    ) {
        fun solve(): Long {
            return when (operation) {
                "+" -> numbers.sum()
                "*" -> numbers.fold(initial = 1) { x, y -> x * y }
                else -> throw IllegalArgumentException("Invalid operation : $operation")
            }
        }
    }
}

fun main() {
    Day06().solve()
}