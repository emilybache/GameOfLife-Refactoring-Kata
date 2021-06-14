package org.sammancoaching

import org.approvaltests.Approvals
import org.junit.jupiter.api.Test

class ConwayGameTest {

    val story = GameOfLifeStory()

    @Test
    fun empty() {
        val game = ConwayGame(3, 3)
        story.arrange(game)
        game.iterate()
        story.act("Iterate")

        Approvals.verify(story)
    }

    @Test
    fun one_death() {
        val game = ConwayGame(3, 3)
        game.setAliveAt(1, 1)
        story.arrange(game)

        game.iterate()
        story.act("Iterate")

        Approvals.verify(story)
    }

    @Test
    fun stable_foursome() {
        val game = ConwayGame(4, 4)
        game.setAliveAt(1, 1)
        game.setAliveAt(1, 2)
        game.setAliveAt(2, 1)
        game.setAliveAt(2, 2)
        story.arrange(game)
        game.iterate()
        story.act("Iterate")

        Approvals.verify(story)
    }

    @Test
    fun blinker() {
        val game = ConwayGame(4, 4)
        game.setAliveAt(1, 1)
        game.setAliveAt(1, 2)
        game.setAliveAt(1, 3)
        story.arrange(game)
        game.iterate()
        story.act("Iterate")
        game.iterate()
        story.act("Iterate")
        Approvals.verify(story)
    }

    @Test
    fun all_alive() {
        val game = ConwayGame(4, 3)
        for (i in 0..4) {
            for (j in 0..3) {
                game.setAliveAt(i, j)
            }
        }
        story.arrange(game)
        game.iterate()
        story.act("Iterate")

        Approvals.verify(story)
    }

}