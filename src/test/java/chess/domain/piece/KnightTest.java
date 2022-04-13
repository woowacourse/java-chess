package chess.domain.piece;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;

import chess.domain.position.Position;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class KnightTest {

    @DisplayName("나이트는 상하좌우 한 칸을 이동하고, 해당 방향을 기준으로 대각선으로 한 칸 이동한다.")
    @Test
    void move() {
        Knight knight = new Knight(Color.WHITE, Position.of("b1"));
        Position c3 = Position.of("c3");

        knight.move(c3);

        assertThat(knight.getPosition()).isEqualTo(c3);
    }

    @DisplayName("나이트는 이동할 수 없는 방향으로 이동하려는 경우 예외가 발생한다.")
    @Test
    void move_exception() {
        Knight knight = new Knight(Color.WHITE, Position.of("b1"));

        assertThatCode(() -> knight.move(Position.of("b3")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이동할 수 없는 위치입니다.");
    }

    @DisplayName("나이트는 attack시 move와 같은 방향으로 이동한다.")
    @Test
    void attack_likeMove() {
        Knight knight = new Knight(Color.WHITE, Position.of("b1"));
        Position c3 = Position.of("c3");

        knight.attack(c3);

        assertThat(knight.getPosition()).isEqualTo(c3);
    }

    @DisplayName("나이트는 이동 시 Path를 가지고 있지 않다..")
    @Test
    void getPositionsInPath() {
        Knight knight = new Knight(Color.WHITE, Position.of("b1"));

        List<Position> actual = knight.getPositionsInPath(Position.of("c3"));
        List<Position> expected = List.of();

        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("나이트는 King이 아니다.")
    @Test
    void isKing_false() {
        Knight knight = new Knight(Color.WHITE, Position.of("a1"));

        boolean actual = knight.isKing();

        assertThat(actual).isFalse();
    }

    @DisplayName("나이트의 이름은 knight이다.")
    @Test
    void getName() {
        String actual = new Knight(Color.BLACK, Position.of("a1")).getName();
        String expected = "knight";

        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("색과 위치가 동일한 Knight 인스턴스는 서로 동일하다고 간주된다.")
    @Test
    void equals_sameOnSameColorAndPosition() {
        Knight actual = new Knight(Color.BLACK, Position.of("b1"));
        Knight expected = new Knight(Color.BLACK, Position.of("b1"));

        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("다른 위치에 있는 Knight 인스턴스는 색이 같더라도 서로 다른 것으로 간주된다.")
    @Test
    void equals_differentOnPositionDifference() {
        Knight knight1 = new Knight(Color.BLACK, Position.of("b1"));
        Knight knight2 = new Knight(Color.BLACK, Position.of("g1"));

        assertThat(knight1).isNotEqualTo(knight2);
    }

    @DisplayName("같은 색과 위치의 Knight 인스턴스의 해쉬코드 값은 서로 같다.")
    @Test
    void hashCode_sameOnSameColorAndPosition() {
        int actual = new Knight(Color.BLACK, Position.of("b1")).hashCode();
        int expected = new Knight(Color.BLACK, Position.of("b1")).hashCode();

        assertThat(actual).isEqualTo(expected);
    }

}
