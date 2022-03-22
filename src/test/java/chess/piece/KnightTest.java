package chess.piece;

import static chess.domain.piece.Color.BLACK;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import chess.domain.piece.Knight;
import chess.domain.piece.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class KnightTest {

    @DisplayName("색과 위치가 동일한 Knight 인스턴스는 서로 동일하다고 간주된다.")
    @Test
    void equals_sameOnSameColorAndPosition() {
        Knight actual = new Knight(BLACK, Position.of("b1"));
        Knight expected = new Knight(BLACK, Position.of("b1"));

        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("다른 위치에 있는 Knight 인스턴스는 색이 같더라도 서로 다른 것으로 간주된다.")
    @Test
    void equals_differentOnPositionDifference() {
        Knight knight1 = new Knight(BLACK, Position.of("b1"));
        Knight knight2 = new Knight(BLACK, Position.of("g1"));

        assertThat(knight1).isNotEqualTo(knight2);
    }

    @DisplayName("같은 색과 위치의 Knight 인스턴스의 해쉬코드 값은 서로 같다.")
    @Test
    void hashCode_sameOnSameColorAndPosition() {
        int actual = new Knight(BLACK, Position.of("b1")).hashCode();
        int expected = new Knight(BLACK, Position.of("b1")).hashCode();

        assertThat(actual).isEqualTo(expected);
    }
}
