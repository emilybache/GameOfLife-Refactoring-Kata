package org.sammancoaching

class GameOfLifeStory() {
    private lateinit var game: ConwayGame
    private var printout: String = ""

    fun arrange(game: ConwayGame) {
        this.game = game
        printout += "Starting position:\n"
        printGrid()
    }

    private fun printGrid() {
        printout += "\n"
        printout += GridPrinter.print(game.data())
        printout += "\n"
    }

    fun act(action: String) {
        printout += "\n*** $action ***\n"
        printGrid()
    }

    override fun toString(): String {
        return printout
    }
}