package model.game;

import model.board.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.offset;

class GameTest {
    private Game testGame;

    @BeforeEach
    void setUp() {
        testGame = new Game();
    }

    @Test
    void getCurrentScoreOfInitialBoardTest() {
        assertThat(testGame.getCurrentScore(Player.WHITE)).isEqualTo(38.0, offset(0.0000001));
    }

    @Test
    void getCurrentScoreTest() {
        testGame.board().removePieceAt(Position.of("d1"));
        testGame.board().removePieceAt(Position.of("g1"));
        assertThat(testGame.getCurrentScore(Player.WHITE)).isEqualTo(26.5, offset(0.0000001));
    }
}