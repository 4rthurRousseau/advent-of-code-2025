import kotlin.math.max

class Day05 : Day(5) {

    override fun part1(input: List<String>): Any {
        val (fresh, ingredients) = input.filter { it.isNotBlank() }.partition { it.contains("-") }
        val ranges = fresh.toSortedRanges()

        return ingredients.count {
            val id = it.toLong()
            ranges.any { range ->
                id in range
            }
        }
    }

    override fun part2(input: List<String>): Any {
        val ranges = input.filter { it.contains("-") }.toSortedRanges()

        var tail = ranges.first()
        val merged = mutableListOf<LongRange>()

        ranges.drop(1).forEach { range ->
            if (range.first <= tail.last + 1) {
                val updated = tail.first..max(range.last, tail.last)
                tail = updated
            } else {
                merged.add(tail)
                tail = range
            }
        }

        merged.add(tail)

        return merged.sumOf {
            it.last - it.first + 1
        }
    }

    fun List<String>.toSortedRanges(): List<LongRange> {
        return this.map {
            val (from, to) = it.split("-")
            LongRange(from.toLong(), to.toLong())
        }.sortedBy { it.first }.toMutableList()
    }
}

fun main() {
    Day05().solve()
}