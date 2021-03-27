package chess.domain.piece;

import chess.domain.board.Team;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class RookTest {
    @Test
    void create() {
        assertThatCode(() -> new Rook(Team.BLACK)).doesNotThrowAnyException();
    }

    @DisplayName("팀 확인하기")
    @Test
    void isSameTeam() {
        Rook rook = new Rook(Team.BLACK);
        assertThat(rook.isSameTeam(Team.BLACK)).isTrue();
    }

    @DisplayName("차례가 아닐 때 움직일 수 없다.")
    @Test
    void team() {
        Rook rook = new Rook(Team.BLACK);
        assertThatThrownBy(() -> rook.confirmTurn(Team.WHITE))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("킹인지 확인하기")
    @Test
    void isKing() {
        Rook rook = new Rook(Team.BLACK);
        assertThat(rook.isKing()).isFalse();
    }

    @DisplayName("폰인지 확인하기")
    @Test
    void isRook() {
        Rook rook = new Rook(Team.BLACK);
        assertThat(rook.isPawn()).isFalse();
    }
}