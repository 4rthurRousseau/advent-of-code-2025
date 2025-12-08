class Day04 : Day(4) {

    companion object {
        const val ROLL = '@'
    }

    override fun part1(input: List<String>): Any {
        val grid = input.map { it.map { it }.toMutableList() }.toMutableList()
        return findRemovableRolls(grid).size
    }

    override fun part2(input: List<String>): Any {
        val grid = input.map { it.map { it }.toMutableList() }.toMutableList()

        var count = 0
        while (true) {
            val removable = findRemovableRolls(grid)
            if (removable.isEmpty()) {
                break
            }

            count += removable.size
            removable.forEach { (row, col) ->
                grid[row][col] = '.'
            }
        }

        return count
    }

    private fun findRemovableRolls(grid: List<List<Char>>): List<Pair<Int, Int>> {
        val removable = mutableListOf<Pair<Int, Int>>()

        grid.forEachIndexed { y, row ->
            row.forEachIndexed { x, cell ->
                if (cell != ROLL) {
                    return@forEachIndexed
                }

                if (isRemovable(grid, x, y)) {
                    removable.add(Pair(y, x))
                }
            }
        }

        return removable
    }

    fun isRemovable(grid: List<List<Char>>, x: Int, y: Int): Boolean {
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

        return count < 4
    }
}

fun main() {
    Day04().solve()
}