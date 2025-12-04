class Day04 : Day(4) {

    companion object {
        const val ROLL = '@'
    }

    override fun part1(input: List<String>): Any {
        val grid = input.map { it.map { it } }
        var count = 0

        grid.forEachIndexed { y, row ->
            row.forEachIndexed { x, cell ->
                if (cell != ROLL) {
                    return@forEachIndexed
                }

                val adjactentRolls = countAdjacentRolls(grid, x, y)
                if (adjactentRolls < 4) {
                    count++
                }
            }
        }

        return count
    }

    override fun part2(input: List<String>): Any {
        return 0
    }

    fun countAdjacentRolls(grid: List<List<Char>>, x: Int, y: Int) : Int {
        var count = 0

        val picks = listOf(
            -1 to -1,
            -1 to 0,
            -1 to 1,
            0 to -1,
            0 to 1,
            1 to -1,
            1 to 0,
            1 to 1
        )

        picks.forEach { (offsetX, offsetY) ->
            val updatedX = x + offsetX
            val updatedY = y + offsetY

            val value = grid.getOrNull(updatedY)?.getOrNull(updatedX)
            count += if (value == ROLL) 1 else 0
        }

        return count
    }
}

fun main() {
    Day04().solve()
}