package chess.domain.piece;

import chess.domain.board.MoveOrder;
import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PawnTest {

    @Test
    @DisplayName("흰색 폰 한 칸 앞으로 전진")
    void forwardWhite() {
        Pawn pawn = new Pawn(Color.WHITE);

        assertThatCode(() -> pawn.checkMoveRange(new MoveOrder(new HashSet<>(), Position.from("a2"), Position.from("a3"))))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("흰색 폰 첫 이동이 아닌 경우에 두 칸 앞으로 전진 시 예외 발생")
    void invalidForwardWhiteTwo() {
        Pawn pawn = new Pawn(Color.WHITE);

        assertThatThrownBy(() -> pawn.checkMoveRange(new MoveOrder(new HashSet<>(), Position.from("a1"), Position.from("a3"))))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("이동 불가한 위치입니다.");
    }

    @Test
    @DisplayName("흰색 폰이 앞으로 두 칸 이동시 도착지점에 기물이 있는 경우 예외 발생")
    void forwardWhiteTwoStep() {
        Pawn pawn = new Pawn(Color.WHITE);

        assertThatThrownBy(() -> pawn.checkMoveRange(new MoveOrder(new HashSet<>(Set.of(Position.from("a4"))), Position.from("a2"), Position.from("a4"))))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이동 불가한 위치입니다.");
    }

    @Test
    @DisplayName("흰색 첫 이동 시 폰 두 칸 앞으로 전진 가능")
    void forwardWhiteTwo() {
        Pawn pawn = new Pawn(Color.WHITE);

        assertThatCode(() -> pawn.checkMoveRange(new MoveOrder(new HashSet<>(), Position.from("a2"), Position.from("a4"))))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("흰색 폰이 앞으로 한 칸 이동시 도착지점에 기물이 있는 경우 예외발생")
    void forwardWhiteOneStep() {
        Pawn pawn = new Pawn(Color.WHITE);

        assertThatThrownBy(() -> pawn.checkMoveRange(new MoveOrder(new HashSet<>(Set.of(Position.from("a3"))), Position.from("a2"), Position.from("a3"))))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이동 불가한 위치입니다.");
    }

    @Test
    @DisplayName("흰색 폰 뒤로 이동 시 예외 발생")
    void backWhite() {
        Pawn pawn = new Pawn(Color.WHITE);

        assertThatThrownBy(() -> pawn.checkMoveRange(new MoveOrder(new HashSet<>(), Position.from("a4"), Position.from("a3"))))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("이동 불가한 위치입니다.");
    }

    @Test
    @DisplayName("흰색 폰 대각선으로 이동 시 예외 발생")
    void forwardWhiteDiagonal() {
        Pawn pawn = new Pawn(Color.WHITE);

        assertThatThrownBy(() -> pawn.checkMoveRange(new MoveOrder(new HashSet<>(), Position.from("a2"), Position.from("b3"))))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이동 불가한 위치입니다.");
    }

    @Test
    @DisplayName("흰색 폰 대각선에 기물이 있는 경우 이동 가능")
    void moveDiagonalWhite() {
        Pawn pawn = new Pawn(Color.WHITE);

        assertThatCode(() -> pawn.checkMoveRange(new MoveOrder(new HashSet<>(Set.of(Position.from("b3"))), Position.from("a2"), Position.from("b3"))))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("흰색 폰 2칸을 초과해서 이동할 경우 예외 발생")
    void invalidMoveWhitePawn() {
        Pawn pawn = new Pawn(Color.WHITE);

        assertThatThrownBy(() -> pawn.checkMoveRange(new MoveOrder(new HashSet<>(), Position.from("a2"), Position.from("a5"))))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이동 불가한 위치입니다.");
    }

    @Test
    @DisplayName("검정 폰 앞으로 한 칸 전진")
    void forwardBlack() {
        Pawn pawn = new Pawn(Color.BLACK);

        assertThatCode(() -> pawn.checkMoveRange(new MoveOrder(new HashSet<>(), Position.from("a7"), Position.from("a6"))))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("검정 폰 두 칸 앞으로 전진 시 예외 발생")
    void invalidForwardBlackTwo() {
        Pawn pawn = new Pawn(Color.BLACK);

        assertThatThrownBy(() -> pawn.checkMoveRange(new MoveOrder(new HashSet<>(), Position.from("a8"), Position.from("a6"))))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("이동 불가한 위치입니다.");
    }

    @Test
    @DisplayName("검정 폰 첫 이동 시 폰 두 칸 앞으로 전진 가능")
    void forwardBlackTwo() {
        Pawn pawn = new Pawn(Color.BLACK);

        assertThatCode(() -> pawn.checkMoveRange(new MoveOrder(new HashSet<>(), Position.from("a7"), Position.from("a5"))))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("검정 폰 뒤로 이동 시 예외 발생")
    void backBlack() {
        Pawn pawn = new Pawn(Color.BLACK);

        assertThatThrownBy(() -> pawn.checkMoveRange(new MoveOrder(new HashSet<>(), Position.from("a5"), Position.from("a6"))))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("이동 불가한 위치입니다.");
    }

    @Test
    @DisplayName("검정 폰 대각선으로 이동 시 예외 발생")
    void forwardBlackDiagonal() {
        Pawn pawn = new Pawn(Color.BLACK);

        assertThatThrownBy(() -> pawn.checkMoveRange(new MoveOrder(new HashSet<>(), Position.from("a7"), Position.from("b6"))))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("이동 불가한 위치입니다.");
    }

    @Test
    @DisplayName("검정 폰 대각선에 흰색 폰이 있는 경우 이동가능")
    void MoveDiagonal() {
        Pawn pawn = new Pawn(Color.BLACK);

        assertThatCode(() -> pawn.checkMoveRange(new MoveOrder(new HashSet<>(Set.of(Position.from("c6"))), Position.from("d7"), Position.from("c6"))))
                .doesNotThrowAnyException();
    }
}
