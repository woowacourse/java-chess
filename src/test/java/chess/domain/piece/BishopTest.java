package chess.domain.piece;

import chess.domain.board.Team;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class BishopTest {
    @Test
    void create() {
        assertThatCode(() -> new Bishop(Team.BLACK)).doesNotThrowAnyException();
    }

    @DisplayName("팀 확인하기")
    @Test
    void isSameTeam() {
        Bishop bishop = new Bishop(Team.BLACK);
        assertThat(bishop.isSameTeam(Team.BLACK)).isTrue();
    }

    @DisplayName("차례가 아닐 때 움직일 수 없다.")
    @Test
    void team() {
        Bishop bishop = new Bishop(Team.BLACK);
        assertThatThrownBy(() -> bishop.confirmTurn(Team.WHITE))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("킹인지 확인하기")
    @Test
    void isKing() {
        Bishop bishop = new Bishop(Team.BLACK);
        assertThat(bishop.isKing()).isFalse();
    }

    @DisplayName("폰인지 확인하기")
    @Test
    void isPawn() {
        Bishop bishop = new Bishop(Team.BLACK);
        assertThat(bishop.isPawn()).isFalse();
    }

}