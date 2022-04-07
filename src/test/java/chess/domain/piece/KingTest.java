package chess.domain.piece;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;

import chess.domain.position.Position;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class KingTest {

    @DisplayName("킹은 상하좌우 혹은 대각선 방향으로 한칸 이동할 수 있다.")
    @Test
    void move() {
        King king = new King(Color.WHITE, Position.of("e1"));
        Position d2 = Position.of("d2");

        king.move(d2);

        assertThat(king.getPosition()).isEqualTo(d2);
    }

    @DisplayName("킹은 두칸 이상 이동하려는 경우 예외가 발생한다.")
    @Test
    void move_exception() {
        King king = new King(Color.WHITE, Position.of("e1"));

        assertThatCode(() -> king.move(Position.of("c3")))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("이동할 수 없는 위치입니다.");
    }

    @DisplayName("킹은 attack시 move와 같은 방향으로 이동한다.")
    @Test
    void attack_likeMove() {
        King king = new King(Color.WHITE, Position.of("d1"));
        Position d2 = Position.of("d2");

        king.attack(d2);

        assertThat(king.getPosition()).isEqualTo(d2);
    }

    @DisplayName("킹은 이동시 Path를 가지고 있지 않다.")
    @Test
    void getPositionsInPath() {
        King king = new King(Color.WHITE, Position.of("d1"));

        List<Position> actual = king.getPositionsInPath(Position.of("d2"));
        List<Position> expected = List.of();

        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("킹은 King이다.")
    @Test
    void isKing_false() {
        King king = new King(Color.WHITE, Position.of("a1"));

        boolean actual = king.isKing();

        assertThat(actual).isTrue();
    }

    @DisplayName("킹의 name은 king")
    @Test
    void getName() {
        String actual = new King(Color.BLACK, Position.of("a1")).getName();
        String expected = "king";

        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("색과 위치가 동일한 King 인스턴스는 서로 동일하다고 간주된다.")
    @Test
    void equals_sameOnSameColorAndPosition() {
        King actual = new King(Color.BLACK, Position.of("e1"));
        King expected = new King(Color.BLACK, Position.of("e1"));

        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("다른 색의 King 인스턴스는 위치가 같더라도 서로 다른 것으로 간주된다.")
    @Test
    void equals_differentOnPositionDifference() {
        King blackKing = new King(Color.BLACK, Position.of("e1"));
        King whiteKing = new King(Color.WHITE, Position.of("e1"));

        assertThat(blackKing).isNotEqualTo(whiteKing);
    }

    @DisplayName("같은 색과 위치의 King 인스턴스의 해쉬코드 값은 서로 같다.")
    @Test
    void hashCode_sameOnSameColorAndPosition() {
        int actual = new King(Color.BLACK, Position.of("e1")).hashCode();
        int expected = new King(Color.BLACK, Position.of("e1")).hashCode();

        assertThat(actual).isEqualTo(expected);
    }

}
