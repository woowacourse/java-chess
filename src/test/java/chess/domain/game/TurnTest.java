package chess.domain.game;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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
    @DisplayName("Team.NONE 으로 Turn 을 생성하면 예외 발생")
    void constructor_WhenTeamNONE() {
        assertThatThrownBy(() -> new Turn(Team.NONE))
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