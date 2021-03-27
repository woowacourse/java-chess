package chess.domain.piece;

import chess.domain.board.Team;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class PawnTest {
    @Test
    void create() {
        assertThatCode(() -> new Pawn(Team.BLACK)).doesNotThrowAnyException();
    }

    @DisplayName("팀 확인하기")
    @Test
    void isSameTeam() {
        Pawn pawn = new Pawn(Team.BLACK);
        assertThat(pawn.isSameTeam(Team.BLACK)).isTrue();
    }

    @DisplayName("차례가 아닐 때 움직일 수 없다.")
    @Test
    void team() {
        Pawn pawn = new Pawn(Team.BLACK);
        assertThatThrownBy(() -> pawn.confirmTurn(Team.WHITE))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("킹인지 확인하기")
    @Test
    void isKing() {
        Pawn pawn = new Pawn(Team.BLACK);
        assertThat(pawn.isKing()).isFalse();
    }

    @DisplayName("폰인지 확인하기")
    @Test
    void isPawn() {
        Pawn pawn = new Pawn(Team.BLACK);
        assertThat(pawn.isPawn()).isTrue();
    }
}