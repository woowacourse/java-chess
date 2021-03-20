package chess.domain.piece;

import chess.domain.board.Team;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.*;

class KingTest {

    @Test
    void create() {
        assertThatCode(() -> new King(Team.BLACK)).doesNotThrowAnyException();
    }

    @DisplayName("팀 확인하기")
    @Test
    void isSameTeam() {
        King king = new King(Team.BLACK);
        assertThat(king.isSameTeam(Team.BLACK)).isTrue();
    }

    @DisplayName("차례가 아닐 때 움직일 수 없다.")
    @Test
    void team() {
        King king = new King(Team.BLACK);
        assertThatThrownBy(() -> king.confirmTurn(Team.WHITE))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("킹인지 확인하기")
    @Test
    void isKing() {
        King king = new King(Team.BLACK);
        assertThat(king.isKing()).isTrue();
    }

    @DisplayName("폰인지 확인하기")
    @Test
    void isPawn() {
        King king = new King(Team.BLACK);
        assertThat(king.isPawn()).isFalse();
    }
}