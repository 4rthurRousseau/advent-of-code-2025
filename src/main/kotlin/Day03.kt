class Day03 : Day(3) {

    override fun part1(input: List<String>): Any {
        val batteries = input.asGrid { it.digitToInt() }

        val sum = batteries.sumOf {
            val x = it.dropLast(1).max()
            val ix = it.indexOf(x)

            val y = it.filterIndexed { index, _ ->
                index > ix
            }.max()

            val best = x * 10 + y
            println(best)

            best
        }

        return sum
    }

    override fun part2(input: List<String>): Any {
        return 0
    }
}

fun main() {
    Day03().solve()
}