package chess.domain.piece;

import static chess.domain.piece.Color.WHITE;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;

import chess.domain.position.Position;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PawnTest {

    @DisplayName("백색 폰은 앞으로 한칸 전진할 수 있다.")
    @Test
    void move_whiteForward() {
        Pawn pawn = new Pawn(WHITE, Position.of("a2"));
        Position a3 = Position.of("a3");

        pawn.move(a3);

        assertThat(pawn.getPosition()).isEqualTo(a3);
    }

    @DisplayName("흑색 폰은 앞으로 한칸 전진할 수 있다.")
    @Test
    void move_blackForward() {
        Pawn pawn = new Pawn(Color.BLACK, Position.of("a7"));
        Position a6 = Position.of("a6");

        pawn.move(a6);

        assertThat(pawn.getPosition()).isEqualTo(a6);
    }

    @DisplayName("흑색 폰이 후진하려는 경우, 예외가 발생한다.")
    @Test
    void move_blackBackward_exception() {
        Pawn pawn = new Pawn(Color.BLACK, Position.of("a7"));
        Position a8 = Position.of("a8");

        assertThatCode(()-> pawn.move(a8))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("이동할 수 없는 위치입니다.");
    }

    @DisplayName("백색 폰이 후진하려는 경우, 예외가 발생한다.")
    @Test
    void move_exceptionOnMovingBackwards() {
        Pawn pawn = new Pawn(WHITE, Position.of("a4"));

        assertThatCode(() -> pawn.move(Position.of("a3")))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("이동할 수 없는 위치입니다.");
    }

    @DisplayName("폰이 직진 이외의 방향으로 이동하려는 경우, 예외가 발생한다.")
    @Test
    void move_exceptionOnMovingToDifferentFile() {
        Pawn pawn = new Pawn(WHITE, Position.of("a3"));

        assertThatCode(() -> pawn.move(Position.of("b4")))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("이동할 수 없는 위치입니다.");
    }

    @DisplayName("초기화된 위치에서는 두칸 전진할 수 있다.")
    @Test
    void move_canJumpOnInitialPosition() {
        Pawn pawn = new Pawn(WHITE, Position.of("a2"));
        Position a4 = Position.of("a4");

        pawn.move(a4);

        assertThat(pawn.getPosition()).isEqualTo(a4);
    }

    @DisplayName("초기화된 위치가 아닌 경우 두칸 전진하려는 경우 예외가 발생한다.")
    @Test
    void move_exceptionOnJumpingAtNonInitialPosition() {
        Pawn pawn = new Pawn(WHITE, Position.of("a3"));

        assertThatCode(() -> pawn.move(Position.of("a5")))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("이동할 수 없는 위치입니다.");
    }

    @DisplayName("white폰은 attack시 대각선 위 방향으로 한칸 이동할 수 있다.")
    @Test
    void attack_likeMoveWhite() {
        Pawn pawn = new Pawn(Color.WHITE, Position.of("a2"));
        Position b3 = Position.of("b3");

        pawn.attack(b3);

        assertThat(pawn.getPosition()).isEqualTo(b3);

    }

    @DisplayName("white폰은 attack시 대각선 아래 방향으로 한칸이 아닌 곳으로 이동할 시 예외가 발생한다.")
    @Test
    void attack_likeMoveExceptionWhite() {
        Pawn pawn = new Pawn(Color.WHITE, Position.of("a2"));

        assertThatCode(() -> pawn.attack(Position.of("b1")))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("공격할 수 없는 위치입니다.");
    }

    @DisplayName("black폰은 attack시 대각선 아래 방향으로 한칸 이동할 수 있다.")
    @Test
    void attack_likeMoveBlack() {
        Pawn pawn = new Pawn(Color.BLACK, Position.of("a7"));
        Position b6 = Position.of("b6");

        pawn.attack(b6);

        assertThat(pawn.getPosition()).isEqualTo(b6);
    }

    @DisplayName("black폰은 attack시 대각선 방향으로 한칸이 아닌 곳으로 이동할 시 예외가 발생한다.")
    @Test
    void attack_likeMoveExceptionBlack() {
        Pawn pawn = new Pawn(Color.BLACK, Position.of("a7"));

        assertThatCode(() -> pawn.attack(Position.of("b8")))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("공격할 수 없는 위치입니다.");
    }

    @DisplayName("폰이 a2에서 a4로 이동할 시, 사이에 있는 position은 a3이다.")
    @Test
    void getPositionsInPath() {
        Queen queen = new Queen(Color.WHITE, Position.of("a2"));

        List<Position> actual = queen.getPositionsInPath(Position.of("a4"));
        List<Position> expected = List.of(Position.of("a3"));

        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("폰은 King이 아니다.")
    @Test
    void isKing_false() {
        Pawn pawn = new Pawn(Color.WHITE, Position.of("a1"));

        boolean actual = pawn.isKing();

        assertThat(actual).isFalse();
    }

    @DisplayName("폰의 name은 pawn이다.")
    @Test
    void getName() {
        String actual = new Pawn(Color.BLACK, Position.of("a1")).getName();
        String expected = "pawn";

        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("색과 위치가 동일한 Pawn 인스턴스는 서로 동일하다고 간주된다.")
    @Test
    void equals_sameOnSameColorAndPosition() {
        Pawn actual = new Pawn(Color.BLACK, Position.of("a2"));
        Pawn expected = new Pawn(Color.BLACK, Position.of("a2"));

        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("다른 위치에 있는 Pawn 인스턴스는 색이 같더라도 서로 다른 것으로 간주된다.")
    @Test
    void equals_differentOnPositionDifference() {
        Pawn pawn1 = new Pawn(Color.BLACK, Position.of("a2"));
        Pawn pawn2 = new Pawn(Color.BLACK, Position.of("b2"));

        assertThat(pawn1).isNotEqualTo(pawn2);
    }

    @DisplayName("같은 색과 위치의 Pawn 인스턴스의 해쉬코드 값은 서로 같다.")
    @Test
    void hashCode_sameOnSameColorAndPosition() {
        int actual = new Pawn(Color.BLACK, Position.of("a2")).hashCode();
        int expected = new Pawn(Color.BLACK, Position.of("a2")).hashCode();

        assertThat(actual).isEqualTo(expected);
    }

}
