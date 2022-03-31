package chess.domain.game;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.piece.Team;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class TurnTest {

    private Turn turn;

    @BeforeEach
    void setUp() {
        turn = new Turn(Team.WHITE);
    }

    @Test
    @DisplayName("Turn 을 넘긴다.")
    void passTurn() {
        turn.passTurn();

        assertThat(turn.isRightTurn(Team.BLACK)).isTrue();
    }

    @Test
    @DisplayName("현재 턴을 확인한다.")
    void isRightTurn() {
        assertThat(turn.isRightTurn(Team.WHITE)).isTrue();
    }
}