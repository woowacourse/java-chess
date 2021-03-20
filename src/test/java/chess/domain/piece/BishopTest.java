package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
        assertThatThrownBy(() -> bishop.validateCurrentTurn(Team.WHITE))
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