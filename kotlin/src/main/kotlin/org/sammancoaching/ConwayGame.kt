package org.sammancoaching

class ConwayGame(val width : Int, val height : Int) {

    private val size = width * height
    private var data: ByteArray = ByteArray(size)
    companion object {
        val dead : Byte = 0
        val alive : Byte = 1
    }

    fun iterate() {
        val next = ByteArray(size)
        (0 until width).forEach { i ->
            (0 until height).forEach { j ->
                val currentlyAlive = isAliveAt(i, j)
                val neighbours = neighbourCount(i, j)
                setAliveAt(nextState(currentlyAlive, neighbours), next, j, i)
            }
        }
        data = next
    }

    private fun nextState(currently_alive: Boolean, neighbour_count: Int): Byte {
        return if (currently_alive) {
            when (neighbour_count) {
                3 -> alive
                2 -> alive
                else -> dead
            }
        } else {
            when (neighbour_count) {
                3 -> alive
                else -> dead
            }
        }
    }

    private fun isAliveAt(i: Int, j: Int): Boolean {
        val pos = j * width + i
        return pos >= 0 && pos < size - 1 && (data[pos] == alive)
    }

    fun setAliveAt(i: Int, j: Int) {
        setAliveAt(1, data, j, i)
    }

    private fun setAliveAt(nextState: Byte, next: ByteArray, j: Int, i: Int) {
        val pos = j * width + i
        if (pos >= 0 && pos < size - 1)
            next[pos] = nextState
    }

    data class Coord(val i: Int, val j: Int) {}

    private fun neighbourCount(x: Int, y: Int): Int {
        val neighbourCoords : List<Coord> =
            listOf(x-1, x, x+1).map { i ->
                listOf(y-1, y, y+1).map { j ->
                Coord(i, j)
            }
        }.flatten().filter{!(it.i == x && it.j == y)}

        return neighbourCoords.filter { isAliveAt(it.i, it.j) }.size
    }

    fun data(): Grid {
        return PrintableData(width, height, data)
    }
}

class PrintableData(val width: Int, val height: Int, val data: ByteArray) : Grid {
    override fun width(): Int {
        return width
    }

    override fun height(): Int {
        return height
    }

    override fun contentAt(x: Int, y: Int): String {
        val pos = y * width + x
        return if (data[pos] == ConwayGame.alive) {
            "*"
        } else {
            "."
        }
    }

}
