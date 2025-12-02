class Day02 : Day(2) {

    override fun part1(input: List<String>): Any {
        val ranges = input.toRanges()

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
        val ranges = input.toRanges()
        var sum = 0L

        ranges.forEach {
            (it.first..it.second).forEach outer@{ value ->
                val text = "$value"

                val limit = text.length / 2
                limit.downTo(1).filter { text.length % it == 0 }.forEach {
                    val pattern = text.take(it)
                    val chunkSize = pattern.length
                    val chunks = text.length / chunkSize

                    val invalid = (0..<chunks).all { i ->
                        val value = text.substring(chunkSize * i, chunkSize * (i + 1))
                        value == pattern
                    }

                    if (invalid) {
                        sum += value
                        return@outer
                    }
                }
            }
        }

        return sum
    }

    private fun List<String>.toRanges(): List<Pair<Long, Long>> {
        return first().split(",").map {
            val (from, to) = it.split("-")
            Pair(from.toLong(), to.toLong())
        }
    }
}

fun main() {
    Day02().solve()
}