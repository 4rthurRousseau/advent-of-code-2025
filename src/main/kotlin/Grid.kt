abstract class Grid<T>(
    protected open val map: List<List<T>>
) {

    operator fun get(point: Point): T {
        return map.getOrNull(point.y)?.get(point.x) ?: throw IndexOutOfBoundsException()
    }
}

class ImmutableGrid<T>(override val map: List<List<T>>) : Grid<T>(map), List<List<T>> by map

class MutableGrid<T>(override val map: List<MutableList<T>>) : Grid<T>(map), List<MutableList<T>> by map {
    operator fun set(point: Point, value: T) {
        map[point.y][point.x] = value
    }
}

data class Point(val x: Int, val y: Int)

fun List<String>.asGrid(): ImmutableGrid<Char> {
    return ImmutableGrid(this.map { it.map { it } })
}

fun <T> List<String>.asGrid(block: (Char) -> T): ImmutableGrid<T> {
    return ImmutableGrid(this.map { it.map { block(it) } })
}

fun List<String>.asMutableGrid(): MutableGrid<Char> {
    return MutableGrid(this.map { it.map { it }.toMutableList() })
}