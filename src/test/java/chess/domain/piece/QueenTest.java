package chess.domain.piece;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;

import chess.domain.position.Position;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class QueenTest {

    @DisplayName("퀸은 상하좌우 방향으로 이동할 수 있다.")
    @Test
    void move_horizontal() {
        Queen queen = new Queen(Color.WHITE, Position.of("d1"));
        Position d8 = Position.of("d8");

        queen.move(d8);

        assertThat(queen.getPosition()).isEqualTo(d8);
    }

    @DisplayName("퀸은 대각선 방향으로 이동할 수 있다.")
    @Test
    void move_diagonal() {
        Queen queen = new Queen(Color.WHITE, Position.of("d1"));
        Position a4 = Position.of("a4");

        queen.move(a4);

        assertThat(queen.getPosition()).isEqualTo(a4);
    }

    @DisplayName("퀸은 상하좌우 혹은 대각선 이외의 방향으로 이동하려는 경우 예외가 발생한다.")
    @Test
    void move_exception() {
        Queen queen = new Queen(Color.WHITE, Position.of("d1"));

        assertThatCode(() -> queen.move(Position.of("e3")))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("이동할 수 없는 위치입니다.");
    }

    @DisplayName("퀸은 attack시 move와 같은 방향으로 이동한다.")
    @Test
    void attack_likeMove() {
        Queen queen = new Queen(Color.WHITE, Position.of("d1"));
        Position a4 = Position.of("a4");

        queen.attack(a4);

        assertThat(queen.getPosition()).isEqualTo(a4);
    }

    @DisplayName("퀸이 d1에서 a4로 이동할 시, 사이에 있는 position은 c2, b3이다.")
    @Test
    void getPositionsInPath() {
        Queen queen = new Queen(Color.WHITE, Position.of("d1"));

        List<Position> actual = queen.getPositionsInPath(Position.of("a4"));
        List<Position> expected = List.of(Position.of("c2"), Position.of("b3"));

        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("퀸은 King이 아니다.")
    @Test
    void isKing_false() {
        Queen queen = new Queen(Color.WHITE, Position.of("a1"));

        boolean actual = queen.isKing();

        assertThat(actual).isFalse();
    }

    @DisplayName("퀸의 name은 queen이다.")
    @Test
    void getName() {
        String actual = new Queen(Color.BLACK, Position.of("a1")).getName();
        String expected = "queen";

        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("색과 위치가 동일한 Queen 인스턴스는 서로 동일하다고 간주된다.")
    @Test
    void equals_sameOnSameColorAndPosition() {
        Queen actual = new Queen(Color.BLACK, Position.of("d1"));
        Queen expected = new Queen(Color.BLACK, Position.of("d1"));

        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("다른 색의 Queen 인스턴스는 위치가 같더라도 서로 다른 것으로 간주된다.")
    @Test
    void equals_differentOnPositionDifference() {
        Queen blackQueen = new Queen(Color.BLACK, Position.of("d1"));
        Queen whiteQueen = new Queen(Color.WHITE, Position.of("d1"));

        assertThat(blackQueen).isNotEqualTo(whiteQueen);
    }

    @DisplayName("같은 색과 위치의 Queen 인스턴스의 해쉬코드 값은 서로 같다.")
    @Test
    void hashCode_sameOnSameColorAndPosition() {
        int actual = new Queen(Color.BLACK, Position.of("d1")).hashCode();
        int expected = new Queen(Color.BLACK, Position.of("d1")).hashCode();

        assertThat(actual).isEqualTo(expected);
    }

}
