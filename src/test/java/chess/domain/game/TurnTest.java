package chess.domain.game;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.piece.Team;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TurnTest {

    private Turn turn;

    @BeforeEach
    void setUp() {
        turn = new Turn(Team.WHITE);
    }

    @Test
    @DisplayName("Team.NONE 일 때 턴을 넘길 경우 에러를 발생시킨다.")
    void passTurn_WhenTeamNONE() {
        assertThatThrownBy(() -> new Turn(Team.NONE).passTurn())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 입력된 Team 은 게임을 할 수 없습니다.");
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