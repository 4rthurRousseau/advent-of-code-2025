class Day02 : Day(2) {

    override fun part1(input: List<String>): Any {
        val ranges = input.first().split(",").map {
            val (from, to) = it.split("-")
            Pair(from.toLong(), to.toLong())
        }

        val sum = ranges.sumOf {
            (it.first..it.second).sumOf {
                val text = "$it"

                if (text.length % 2 == 1) {
                    return@sumOf 0
                }

                val length = text.length / 2
                (0..<length).forEach {
                    val l1 = text[it]
                    val l2 = text[length + it]

                    if (l1 != l2) {
                        return@sumOf 0
                    }
                }

                it
            }
        }

        return sum
    }

    override fun part2(input: List<String>): Any {
        return 0
    }
}

fun main() {
    Day02().solve()
}