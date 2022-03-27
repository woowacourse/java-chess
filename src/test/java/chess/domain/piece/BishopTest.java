package chess.domain.piece;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;

import chess.domain.position.Position;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BishopTest {

    @DisplayName("비숍은 대각선 방향으로 이동할 수 있다.")
    @Test
    void move() {
        Bishop bishop = new Bishop(Color.WHITE, Position.of("c1"));
        bishop.move(Position.of("d2"));

        Bishop expected = new Bishop(Color.WHITE, Position.of("d2"));

        assertThat(bishop).isEqualTo(expected);
    }

    @DisplayName("비숍이 대각선이 아닌 방향으로 이동하려는 경우 예외가 발생한다.")
    @Test
    void move_exception() {
        Bishop bishop = new Bishop(Color.WHITE, Position.of("c1"));

        assertThatCode(() -> bishop.move(Position.of("c3")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이동할 수 없는 위치입니다.");
    }

    @DisplayName("비숍은 attack시 move와 같은 방향으로 이동한다.")
    @Test
    void attack_likeMove() {
        Bishop bishop = new Bishop(Color.WHITE, Position.of("d1"));
        bishop.attack(Position.of("a4"));

        Bishop expected = new Bishop(Color.WHITE, Position.of("a4"));

        assertThat(bishop).isEqualTo(expected);
    }

    @DisplayName("비숍이 d1에서 a4로 이동할 시, 사이에 있는 position은 c2, b3이다.")
    @Test
    void getPositionsInPath() {
        Bishop bishop = new Bishop(Color.WHITE, Position.of("d1"));

        List<Position> actual = bishop.getPositionsInPath(Position.of("a4"));
        List<Position> expected = List.of(Position.of("c2"), Position.of("b3"));

        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("비숍은 King이 아니다.")
    @Test
    void isKing_false() {
        Bishop bishop = new Bishop(Color.WHITE, Position.of("a1"));

        boolean actual = bishop.isKing();
        boolean expected = false;

        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("흑색의 비숍의 display는 ♙이다.")
    @Test
    void display_black() {
        String actual = new Bishop(Color.BLACK, Position.of("a1")).display();
        String expected = "♙";

        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("백색의 비숍의 display는 ♟이다.")
    @Test
    void display_white() {
        String actual = new Bishop(Color.WHITE, Position.of("a1")).display();
        String expected = "♟";

        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("색과 위치가 동일한 Bishop 인스턴스는 서로 동일하다고 간주된다.")
    @Test
    void equals_sameOnSameColorAndPosition() {
        Bishop actual = new Bishop(Color.BLACK, Position.of("c1"));
        Bishop expected = new Bishop(Color.BLACK, Position.of("c1"));

        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("다른 위치에 있는 Bishop 인스턴스는 색이 같더라도 서로 다른 것으로 간주된다.")
    @Test
    void equals_differentOnPositionDifference() {
        Bishop bishop1 = new Bishop(Color.BLACK, Position.of("c1"));
        Bishop bishop2 = new Bishop(Color.BLACK, Position.of("f1"));

        assertThat(bishop1).isNotEqualTo(bishop2);
    }

    @DisplayName("같은 색과 위치의 Bishop 인스턴스의 해쉬코드 값은 서로 같다.")
    @Test
    void hashCode_sameOnSameColorAndPosition() {
        int actual = new Bishop(Color.BLACK, Position.of("c1")).hashCode();
        int expected = new Bishop(Color.BLACK, Position.of("c1")).hashCode();

        assertThat(actual).isEqualTo(expected);
    }

}