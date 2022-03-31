package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PawnTest {

    private static final Board emptyBoard = new Board(HashMap::new);

    @Test
    @DisplayName("흰색 폰 한 칸 앞으로 전진")
    void forwardWhite() {
        Pawn pawn = new Pawn(Color.WHITE);

        assertThatCode(() -> pawn.checkMovingRange(emptyBoard, Position.from("a2"), Position.from("a3")))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("흰색 폰 두 칸 앞으로 전진 시 예외 발생")
    void invalidForwardWhiteTwo() {
        Pawn pawn = new Pawn(Color.WHITE);

        assertThatThrownBy(() -> pawn.checkMovingRange(emptyBoard, Position.from("a1"), Position.from("a3")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("폰은 앞으로 한 칸만 이동할 수 있습니다.");
    }

    @Test
    @DisplayName("흰색 첫 이동 시 폰 두 칸 앞으로 전진 가능")
    void forwardWhiteTwo() {
        Pawn pawn = new Pawn(Color.WHITE);

        assertThatCode(() -> pawn.checkMovingRange(emptyBoard, Position.from("a2"), Position.from("a4")))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("흰색 폰이 앞으로 한 칸 이동시 도착지점에 검정 폰이 있는 경우 예외 발생")
    void forwardWhiteOneStep() {
        Pawn pawn = new Pawn(Color.WHITE);

        final Board mockBoard = new Board(() -> new HashMap<>(Map.of(Position.from("a3"), new Pawn(Color.BLACK))));

        assertThatThrownBy(() -> pawn.checkMovingRange(mockBoard, Position.from("a2"), Position.from("a3")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("폰은 앞으로 한 칸만 이동할 수 있습니다.");
    }

    @Test
    @DisplayName("흰색 폰이 앞으로 두 칸 이동시 도착지점에 검정 폰이 있는 경우 예외 발생")
    void forwardWhiteTwoStep() {
        Pawn pawn = new Pawn(Color.WHITE);

        final Board mockBoard = new Board(() -> new HashMap<>(Map.of(Position.from("a4"), new Pawn(Color.BLACK))));

        assertThatThrownBy(() -> pawn.checkMovingRange(mockBoard, Position.from("a2"), Position.from("a4")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("폰은 앞으로 한 칸만 이동할 수 있습니다.");
    }

    @Test
    @DisplayName("흰색 폰 뒤로 이동 시 예외 발생")
    void backWhite() {
        Pawn pawn = new Pawn(Color.WHITE);

        assertThatThrownBy(() -> pawn.checkMovingRange(emptyBoard, Position.from("a4"), Position.from("a3")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("폰은 앞으로 한 칸만 이동할 수 있습니다.");
    }

    @Test
    @DisplayName("흰색 폰 대각선으로 이동 시 예외 발생")
    void forwardWhiteDiagonal() {
        Pawn pawn = new Pawn(Color.WHITE);

        assertThatThrownBy(() -> pawn.checkMovingRange(emptyBoard, Position.from("a2"), Position.from("b3")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("폰은 앞으로 한 칸만 이동할 수 있습니다.");
    }

    @Test
    @DisplayName("흰색 폰 대각선에 검정 폰이 있는경우 이동 가능")
    void moveDiagonalWhite() {
        Pawn pawn = new Pawn(Color.WHITE);

        final Board mockBoard = new Board(() -> new HashMap<>(Map.of(Position.from("b3"), new Pawn(Color.BLACK))));

        assertThatCode(() -> pawn.checkMovingRange(mockBoard, Position.from("a2"), Position.from("b3")))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("검정 폰 앞으로 한 칸 전진")
    void forwardBlack() {
        Pawn pawn = new Pawn(Color.BLACK);

        assertThatCode(() -> pawn.checkMovingRange(emptyBoard, Position.from("a7"), Position.from("a6")))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("검정 폰 두 칸 앞으로 전진 시 예외 발생")
    void invalidForwardBlackTwo() {
        Pawn pawn = new Pawn(Color.BLACK);

        assertThatThrownBy(() -> pawn.checkMovingRange(emptyBoard, Position.from("a8"), Position.from("a6")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("폰은 앞으로 한 칸만 이동할 수 있습니다.");
    }

    @Test
    @DisplayName("검정 폰 첫 이동 시 폰 두 칸 앞으로 전진 가능")
    void forwardBlackTwo() {
        Pawn pawn = new Pawn(Color.BLACK);

        assertThatCode(() -> pawn.checkMovingRange(emptyBoard, Position.from("a7"), Position.from("a5")))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("검정 폰 뒤로 이동 시 예외 발생")
    void backBlack() {
        Pawn pawn = new Pawn(Color.BLACK);

        assertThatThrownBy(() -> pawn.checkMovingRange(emptyBoard, Position.from("a5"), Position.from("a6")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("폰은 앞으로 한 칸만 이동할 수 있습니다.");
    }

    @Test
    @DisplayName("검정 폰 대각선으로 이동 시 예외 발생")
    void forwardBlackDiagonal() {
        Pawn pawn = new Pawn(Color.BLACK);

        assertThatThrownBy(() -> pawn.checkMovingRange(emptyBoard, Position.from("a7"), Position.from("b6")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("폰은 앞으로 한 칸만 이동할 수 있습니다.");
    }

    @Test
    @DisplayName("검정 폰 대각선에 흰색 폰이 있는 경우 이동가능")
    void MoveDiagonal() {
        Pawn pawn = new Pawn(Color.BLACK);

        final Board mockBoard = new Board(() -> new HashMap<>(Map.of(Position.from("c6"), new Pawn(Color.WHITE))));

        assertThatCode(() -> pawn.checkMovingRange(mockBoard, Position.from("d7"), Position.from("c6")))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("폰인지 확인")
    void isPawn() {
        Piece pawn = new Pawn(Color.WHITE);

        assertThat(pawn.isPawn()).isTrue();
    }

    @Test
    @DisplayName("나이트인지 확인")
    void isKnight() {
        Piece pawn = new Pawn(Color.WHITE);

        assertThat(pawn.isKnight()).isFalse();
    }

    @Test
    @DisplayName("킹인지 확인")
    void isKing() {
        Piece pawn = new Pawn(Color.WHITE);

        assertThat(pawn.isKing()).isFalse();
    }
}
