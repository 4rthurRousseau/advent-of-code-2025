class Day07 : Day(7) {

    override fun part1(input: List<String>): Any {
        val grid = input.asMutableGrid()
        var split = 0

        grid.forEachIndexed { y, row ->
            row.forEachIndexed { x, cell ->
                val topCell = grid.getOrNull(y - 1)?.get(x)

                if (cell == '^' && topCell == '|') {
                    row[x - 1] = '|'
                    row[x + 1] = '|'
                    split++
                } else if (cell == '.' && (topCell == '|' || topCell == 'S')) {
                    row[x] = '|'
                }
            }
        }

        return split
    }

    override fun part2(input: List<String>): Any {
        // Sans doute quelque chose à faire en appelant la même méthode que pour la partie 1, mais de façon récursive
        return 0
    }
}

fun main() {
    Day07().solve()
}