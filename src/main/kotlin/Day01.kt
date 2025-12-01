class Day01 : Day(1) {

    companion object {
        const val DIRECTION_LEFT = -1
        const val DIRECTION_RIGHT = 1
    }
    override fun part1(input: List<String>): Any {
        var index = 50
        var password = 0

        input.forEach {
            val direction = if (it.first() == 'L') DIRECTION_LEFT else DIRECTION_RIGHT
            val count = it.substring(1).toInt()
            val move = count * direction
            index = (index + move) % 100
            if (index < 0) {
                index += 100
            }

            if (index == 0) {
                password++
            }
        }

        return password
    }

    override fun part2(input: List<String>): Any {
        var index = 50
        var password = 0

        input.forEach {
            val direction = if (it.first() == 'L') DIRECTION_LEFT else DIRECTION_RIGHT
            val count = it.substring(1).toInt()

            password += count / 100
            repeat(count % 100) {
                index += direction

                if (direction == DIRECTION_LEFT && index < 0) {
                    index = 99
                }

                if (direction == DIRECTION_RIGHT && index == 100) {
                    index = 0
                }

                if (index == 0) {
                    password++
                }
            }
        }

        return password
    }
}

fun main() {
    Day01().solve()
}