package chess.domain.piece;

import static chess.domain.piece.Color.BLACK;
import static chess.domain.piece.Color.WHITE;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;

import chess.domain.piece.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class QueenTest {

    @DisplayName("퀸은 상하좌우 방향으로 이동할 수 있다.")
    @Test
    void move_horizontal() {
        Queen queen = new Queen(WHITE, Position.of("d1"));
        queen.move(Position.of("d8"));

        Queen expected = new Queen(WHITE, Position.of("d8"));

        assertThat(queen).isEqualTo(expected);
    }

    @DisplayName("퀸은 대각선 방향으로 이동할 수 있다.")
    @Test
    void move_diagonal() {
        Queen queen = new Queen(WHITE, Position.of("d1"));
        queen.move(Position.of("a4"));

        Queen expected = new Queen(WHITE, Position.of("a4"));

        assertThat(queen).isEqualTo(expected);
    }

    @DisplayName("퀸은 상하좌우 혹은 대각선 이외의 방향으로 이동하려는 경우 예외가 발생한다.")
    @Test
    void move_exception() {
        Queen queen = new Queen(WHITE, Position.of("d1"));

        assertThatCode(() -> queen.move(Position.of("e3")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이동할 수 없는 위치입니다.");
    }

    @DisplayName("색과 위치가 동일한 Queen 인스턴스는 서로 동일하다고 간주된다.")
    @Test
    void equals_sameOnSameColorAndPosition() {
        Queen actual = new Queen(BLACK, Position.of("d1"));
        Queen expected = new Queen(BLACK, Position.of("d1"));

        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("다른 색의 Queen 인스턴스는 위치가 같더라도 서로 다른 것으로 간주된다.")
    @Test
    void equals_differentOnPositionDifference() {
        Queen blackQueen = new Queen(BLACK, Position.of("d1"));
        Queen whiteQueen = new Queen(WHITE, Position.of("d1"));

        assertThat(blackQueen).isNotEqualTo(whiteQueen);
    }

    @DisplayName("같은 색과 위치의 Queen 인스턴스의 해쉬코드 값은 서로 같다.")
    @Test
    void hashCode_sameOnSameColorAndPosition() {
        int actual = new Queen(BLACK, Position.of("d1")).hashCode();
        int expected = new Queen(BLACK, Position.of("d1")).hashCode();

        assertThat(actual).isEqualTo(expected);
    }
}
