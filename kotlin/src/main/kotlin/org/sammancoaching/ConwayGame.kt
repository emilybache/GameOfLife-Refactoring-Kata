package org.sammancoaching

class ConwayGame(val width : Int, val height : Int) {

    private val size = width * height
    var data: ByteArray = ByteArray(size)

    /**
     *
     * Iterates the game one step forward
     *
     */
    fun iterate() {
        val prev = ByteArray(size)
        System.arraycopy(data, 0, prev, 0, size)
        val next = ByteArray(size)
        for (i in 0 until width) {
            for (j in 0 until height) {
                val type = isAlive(i, j, prev)
                setAliveAt(type, next, j, i)
            }
        }
        System.arraycopy(next, 0, data, 0, size)
    }

    fun setAliveAt(i: Int, j: Int) {
        setAliveAt(1, data, j, i)
    }

    private fun setAliveAt(type: Int, next: ByteArray, j: Int, i: Int) {
        if (type > 0) {
            setAliveAt(next, j, i)
        } else {
            setDeadAt(next, j, i)
        }
    }

    private fun setDeadAt(next: ByteArray, j: Int, i: Int) {
        val pos = j * width + i
        if (pos >= 0 && pos < size - 1) {
            next[pos] = 0
        }
    }

    private fun setAliveAt(next: ByteArray, j: Int, i: Int) {
        val pos = j * width + i
        if (pos >= 0 && pos < size - 1)
            next[pos] = 1
    }

    protected fun isAlive(x: Int, y: Int, d: ByteArray): Int {
        var count = 0
        val pos1 = y * width + x
        for (i in x - 1..x + 1) {
            for (j in y - 1..y + 1) {
                val pos = j * width + i
                if (pos >= 0 && pos < size - 1 && pos != pos1) {
                    val alive : Byte = 1
                    if (d[pos] == alive) {
                        count++
                    }
                }
            }
        }

        //dead
        val dead : Byte = 0
        if (d[pos1] == dead) {
            if (count == 3) { //becomes alive.
               return 1
            } else return 0
            //still dead
        } else { //live
            if (count < 2 || count > 3) { //Dies
               return 0
            } else return 1
            //lives
        }
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
        val alive : Byte = 1
        if (data[pos] == alive) {
            return "*"
        } else {
            return "."
        }
    }

}
