class Day06 : Day(6) {

    val pattern = "( ){2,}".toRegex()

    override fun part1(input: List<String>): Any {
        val problems = input.toProblems()

        return problems.sumOf { it.solve() }
    }

    override fun part2(input: List<String>): Any {
        val problemsAsGrid = input.toGrid()
        return problemsAsGrid.sumOf { it.solve() }
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

    fun List<String>.toGrid(): List<ProblemAsGrid> {
        val operationsRow = last()

        val regex = "[*+]\\s*(?=\\s)".toRegex()
        val operations = regex.findAll(operationsRow).map { it.range }

        val grid = operations.map { operation ->
            val numbers = (0..size - 2).map {
                this[it].substring(operation).map { it }
            }

            ProblemAsGrid(operation = this.last()[operation.first], numbers = numbers)
        }.toList()

        return grid
    }

    data class ProblemAsGrid(
        val operation: Char, val numbers: List<List<Char>>
    ) {
        fun solve(): Long {
            val numbers = (0..numbers.first().lastIndex).map { col ->
                var number = 0L
                (0..numbers.lastIndex).forEach { row ->
                    val n = numbers[row].getOrNull(col)?.digitToIntOrNull() ?: return@forEach
                    number = (number * 10) + n
                }

                number
            }

            return when (operation) {
                '+' -> numbers.sum()
                '*' -> numbers.fold(initial = 1) { x, y -> x * y }
                else -> throw IllegalArgumentException("Invalid operation : $operation")
            }
        }
    }

    data class Problem(
        val operation: String, val numbers: List<Long>
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