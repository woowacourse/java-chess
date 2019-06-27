package model.game;

import model.board.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RefereeTest {
    Game testGame;

    @BeforeEach
    void setUp() {
        testGame = new Game();
    }

    @Test
    void isKingAliveTest() {
        assertThat(Referee.isKingAlive(testGame)).isTrue();
    }

    @Test
    void isKingDeadTest() {
        testGame.board().removePieceAt(Position.of("e8"));
        testGame.turn().endTurn();
        assertThat(Referee.isKingAlive(testGame)).isFalse();
    }
}