package chess.domain.piece;

import static chess.domain.piece.Color.BLACK;
import static chess.domain.piece.Color.WHITE;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;

import chess.domain.piece.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PawnTest {

    @DisplayName("백색 폰은 앞으로 한칸 전진할 수 있다.")
    @Test
    void move_whiteForward() {
        Pawn pawn = new Pawn(WHITE, Position.of("a2"));
        pawn.move(Position.of("a3"));

        Pawn expected = new Pawn(WHITE, Position.of("a3"));

        assertThat(pawn).isEqualTo(expected);
    }

    @DisplayName("흑색 폰은 앞으로 한칸 전진할 수 있다.")
    @Test
    void move_blackForward() {
        Pawn pawn = new Pawn(BLACK, Position.of("a7"));
        pawn.move(Position.of("a6"));

        Pawn expected = new Pawn(BLACK, Position.of("a6"));

        assertThat(pawn).isEqualTo(expected);
    }

    @DisplayName("폰이 후진하려는 경우, 예외가 발생한다.")
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
        pawn.move(Position.of("a4"));

        Pawn expected = new Pawn(WHITE, Position.of("a4"));

        assertThat(pawn).isEqualTo(expected);
    }

    @DisplayName("초기화된 위치가 아닌 경우 두칸 전진하려는 경우 예외가 발생한다.")
    @Test
    void move_exceptionOnJumpingAtNonInitialPosition() {
        Pawn pawn = new Pawn(WHITE, Position.of("a3"));

        assertThatCode(() -> pawn.move(Position.of("a5")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이동할 수 없는 위치입니다.");
    }

    @DisplayName("색과 위치가 동일한 Pawn 인스턴스는 서로 동일하다고 간주된다.")
    @Test
    void equals_sameOnSameColorAndPosition() {
        Pawn actual = new Pawn(BLACK, Position.of("a2"));
        Pawn expected = new Pawn(BLACK, Position.of("a2"));

        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("다른 위치에 있는 Pawn 인스턴스는 색이 같더라도 서로 다른 것으로 간주된다.")
    @Test
    void equals_differentOnPositionDifference() {
        Pawn pawn1 = new Pawn(BLACK, Position.of("a2"));
        Pawn pawn2 = new Pawn(BLACK, Position.of("b2"));

        assertThat(pawn1).isNotEqualTo(pawn2);
    }

    @DisplayName("같은 색과 위치의 Pawn 인스턴스의 해쉬코드 값은 서로 같다.")
    @Test
    void hashCode_sameOnSameColorAndPosition() {
        int actual = new Pawn(BLACK, Position.of("a2")).hashCode();
        int expected = new Pawn(BLACK, Position.of("a2")).hashCode();

        assertThat(actual).isEqualTo(expected);
    }
}
