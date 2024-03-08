fun main() {
    val str = """o
o
oo"""

    val figure = Figure.fromString(str)
    figure.rotations.forEach { println(it.print()) }
}

interface Printable {
    companion object {
        const val CELL = 'o'
        const val EMPTY = '.'

    }

    fun print(): String
}

class PlaceableCells(cells: List<Cell>) {

}

class Figure(cells: List<Cell>) {
    private val neighbors: List<Cell>
    val rotations: List<FigureRotation>

    init {
        neighbors = calculateNeighbors()
        rotations = calculateRotations(cells)
    }

    private fun calculateRotations(cells: List<Cell>): List<FigureRotation> {
        val res = mutableSetOf(FigureRotation(cells))
        while (true) {
            val next = res.last().rotate()
            if (!res.contains(next)) {
                res.add(next)
            } else {
                break
            }
        }
        return res.toList()
    }

    private fun calculateNeighbors(): List<Cell> {
        val res: MutableList<Cell> = mutableListOf()
        return res
    }

    companion object {
        fun fromString(str: String): Figure {
            val lines = str.split("\n")

            return Figure(lines.withIndex()
                .flatMap { (index, line) ->
                    line.withIndex()
                        .filter { it.value == Printable.CELL }
                        .map { Cell(it.index, lines.size - index - 1) }
                })
        }
    }
}

class FigureRotation(val cells: List<Cell>) : Printable {
    val maxX: Int = cells.maxOf { it.x }
    val maxY: Int = cells.maxOf { it.y }

    /**
     * [algorithm](https://stackoverflow.com/a/8664879/19933941)
     */
    fun rotate(): FigureRotation {
        val newCells = cells.map { it.transpose().reverseColumns(maxX) }.sorted()
        return FigureRotation(newCells)
    }

    override fun print(): String {
        val sortedForView = cells.sortedWith { a, b -> if(a.y == b.y) a.x.compareTo(b.x) else b.y.compareTo(a.y) }
        val maxY = cells.maxOf { it.y }
        val maxX = cells.maxOf { it.x }
        var cellIndex = 0

        val sb = StringBuilder()
        for (y in maxY downTo 0) {
            for (x in 0..maxX) {
                if (cellIndex < sortedForView.size && with(sortedForView[cellIndex]) { this.x == x && this.y == y }) {
                    sb.append(Printable.CELL)
                    cellIndex++
                } else {
                    sb.append(Printable.EMPTY)
                }
            }
            sb.appendLine()
        }
        return sb.toString()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is FigureRotation) return false
        if (cells != other.cells) return false

        return true
    }

    override fun hashCode(): Int {
        return cells.hashCode()
    }
}

data class Cell(val x: Int, val y: Int) : Comparable<Cell> {
    fun transpose(): Cell {
        return Cell(y, x)
    }

    fun reverseColumns(maxY: Int): Cell {
        return Cell(x, maxY - y)
    }

    override fun compareTo(other: Cell): Int {
        return if (x == other.x) other.y.compareTo(y) else x.compareTo(other.x)
    }
}