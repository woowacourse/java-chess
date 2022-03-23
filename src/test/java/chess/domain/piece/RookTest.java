package chess.domain.piece;

import static chess.domain.piece.Color.BLACK;
import static chess.domain.piece.Color.WHITE;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;

import chess.domain.piece.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RookTest {

    @DisplayName("룩은 상하좌우 방향으로 이동할 수 있다.")
    @Test
    void move() {
        Rook rook = new Rook(WHITE, Position.of("a1"));
        rook.move(Position.of("a8"));

        Rook expected = new Rook(WHITE, Position.of("a8"));

        assertThat(rook).isEqualTo(expected);
    }

    @DisplayName("룩은 상하좌우가 방향으로 이동하려는 경우 예외가 발생한다.")
    @Test
    void move_exception() {
        Rook rook = new Rook(WHITE, Position.of("a1"));

        assertThatCode(() -> rook.move(Position.of("b3")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이동할 수 없는 위치입니다.");
    }

    @DisplayName("색과 위치가 동일한 Rook 인스턴스는 서로 동일하다고 간주된다.")
    @Test
    void equals_sameOnSameColorAndPosition() {
        Rook actual = new Rook(BLACK, Position.of("a1"));
        Rook expected = new Rook(BLACK, Position.of("a1"));

        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("다른 위치에 있는 Rook 인스턴스는 색이 같더라도 서로 다른 것으로 간주된다.")
    @Test
    void equals_differentOnPositionDifference() {
        Rook rook1 = new Rook(BLACK, Position.of("a1"));
        Rook rook2 = new Rook(BLACK, Position.of("h1"));

        assertThat(rook1).isNotEqualTo(rook2);
    }

    @DisplayName("같은 색과 위치의 Rook 인스턴스의 해쉬코드 값은 서로 같다.")
    @Test
    void hashCode_sameOnSameColorAndPosition() {
        int actual = new Rook(BLACK, Position.of("a1")).hashCode();
        int expected = new Rook(BLACK, Position.of("a1")).hashCode();

        assertThat(actual).isEqualTo(expected);
    }
}
