package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.piece.notation.Color;
import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class KingTest {

    private static final Board emptyBoard = new Board(HashMap::new);

    @Test
    @DisplayName("오른쪽으로 한 칸을 초과해서 이동하는 경우 예외 발생")
    void invalidMoveToRight() {
        assertThatThrownBy(() -> new King(Color.WHITE).checkMoveRange(emptyBoard, Position.from("e5"), Position.from("h5")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("킹은 모든 방향으로 한 칸 이동 가능합니다.");
    }

    @Test
    @DisplayName("왼쪽으로 한 칸을 초과해서 이동하는 경우 예외 발생")
    void invalidMoveToLeft() {
        assertThatThrownBy(() -> new King(Color.WHITE).checkMoveRange(emptyBoard, Position.from("e5"), Position.from("a5")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("킹은 모든 방향으로 한 칸 이동 가능합니다.");
    }

    @Test
    @DisplayName("위로 한 칸을 초과해서 이동하는 경우 예외 발생")
    void invalidMoveToTop() {
        assertThatThrownBy(() -> new King(Color.WHITE).checkMoveRange(emptyBoard, Position.from("e5"), Position.from("e8")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("킹은 모든 방향으로 한 칸 이동 가능합니다.");
    }

    @Test
    @DisplayName("아래쪽으로 한 칸을 초과해서 이동하는 경우 예외 발생")
    void invalidMoveToBottom() {
        assertThatThrownBy(() -> new King(Color.WHITE).checkMoveRange(emptyBoard, Position.from("e5"), Position.from("e3")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("킹은 모든 방향으로 한 칸 이동 가능합니다.");
    }

    @Test
    @DisplayName("올라가는 대각선 방향으로 한 칸을 초과해서 이동하는 경우 예외 발생")
    void invalidMoveToRisingDiagonal() {
        assertThatThrownBy(() -> new King(Color.WHITE).checkMoveRange(emptyBoard, Position.from("e5"), Position.from("h8")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("킹은 모든 방향으로 한 칸 이동 가능합니다.");
    }

    @Test
    @DisplayName("내려가는 대각선 방향으로 한 칸을 초과해서 이동하는 경우 예외 발생")
    void invalidMoveToDescendingDiagonal() {
        assertThatThrownBy(() -> new King(Color.WHITE).checkMoveRange(emptyBoard, Position.from("e5"), Position.from("a1")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("킹은 모든 방향으로 한 칸 이동 가능합니다.");
    }

    @DisplayName("유효한 범위 이동시")
    @ParameterizedTest
    @ValueSource(strings = {"d6", "e6", "f6", "f5", "f4", "e4", "d4", "d5"})
    void validMove(final String validPosition) {
        assertThatCode(() -> new King(Color.WHITE).checkMoveRange(emptyBoard, Position.from("e5"), Position.from(validPosition)))
                .doesNotThrowAnyException();
    }
}
