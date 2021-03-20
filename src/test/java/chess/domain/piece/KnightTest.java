package chess.domain.piece;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class KnightTest {
    @Test
    void create() {
        assertThatCode(() -> new Knight(Team.BLACK)).doesNotThrowAnyException();
    }

    @DisplayName("팀 확인하기")
    @Test
    void isSameTeam() {
        Knight knight = new Knight(Team.BLACK);
        assertThat(knight.isSameTeam(Team.BLACK)).isTrue();
    }

    @DisplayName("차례가 아닐 때 움직일 수 없다.")
    @Test
    void team() {
        Knight knight = new Knight(Team.BLACK);
        assertThatThrownBy(() -> knight.validateCurrentTurn(Team.WHITE))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("킹인지 확인하기")
    @Test
    void isKing() {
        Knight knight = new Knight(Team.BLACK);
        assertThat(knight.isKing()).isFalse();
    }

    @DisplayName("폰인지 확인하기")
    @Test
    void isPawn() {
        Knight knight = new Knight(Team.BLACK);
        assertThat(knight.isPawn()).isFalse();
    }
}