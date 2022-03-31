package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class KingTest {

    private static final Board emptyBoard = new Board(HashMap::new);

    @DisplayName("킹이 이동할 수 있는 범위로 이동")
    @ParameterizedTest
    @ValueSource(strings = {"d6", "e6", "f6", "f5", "f4", "e4", "d4", "d5"})
    void isKingMoving(String to) {
        Piece king = new King(Color.WHITE);

        assertThatCode(() -> king.checkMovingRange(emptyBoard, Position.from("e5"), Position.from(to)))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("오른쪽으로 한 칸을 초과해서 이동하는 경우 예외 발생")
    void invalidMoveToRight() {
        assertThatThrownBy(() -> new King(Color.WHITE).checkMovingRange(emptyBoard, Position.from("e5"), Position.from("h5")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("킹은 모든 방향으로 한 칸 이동 가능합니다.");
    }

    @Test
    @DisplayName("왼쪽으로 한 칸을 초과해서 이동하는 경우 예외 발생")
    void invalidMoveToLeft() {
        assertThatThrownBy(() -> new King(Color.WHITE).checkMovingRange(emptyBoard, Position.from("e5"), Position.from("a5")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("킹은 모든 방향으로 한 칸 이동 가능합니다.");
    }

    @Test
    @DisplayName("위로 한 칸을 초과해서 이동하는 경우 예외 발생")
    void invalidMoveToTop() {
        assertThatThrownBy(() -> new King(Color.WHITE).checkMovingRange(emptyBoard, Position.from("e5"), Position.from("e8")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("킹은 모든 방향으로 한 칸 이동 가능합니다.");
    }

    @Test
    @DisplayName("아래쪽으로 한 칸을 초과해서 이동하는 경우 예외 발생")
    void invalidMoveToBottom() {
        assertThatThrownBy(() -> new King(Color.WHITE).checkMovingRange(emptyBoard, Position.from("e5"), Position.from("e3")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("킹은 모든 방향으로 한 칸 이동 가능합니다.");
    }

    @Test
    @DisplayName("올라가는 대각선 방향으로 한 칸을 초과해서 이동하는 경우 예외 발생")
    void invalidMoveToRisingDiagonal() {
        assertThatThrownBy(() -> new King(Color.WHITE).checkMovingRange(emptyBoard, Position.from("e5"), Position.from("h8")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("킹은 모든 방향으로 한 칸 이동 가능합니다.");
    }

    @Test
    @DisplayName("내려가는 대각선 방향으로 한 칸을 초과해서 이동하는 경우 예외 발생")
    void invalidMoveToDescendingDiagonal() {
        assertThatThrownBy(() -> new King(Color.WHITE).checkMovingRange(emptyBoard, Position.from("e5"), Position.from("a1")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("킹은 모든 방향으로 한 칸 이동 가능합니다.");
    }

    @Test
    @DisplayName("폰인지 확인")
    void isPawn() {
        Piece king = new King(Color.WHITE);

        assertThat(king.isPawn()).isFalse();
    }

    @Test
    @DisplayName("나이트인지 확인")
    void isKnight() {
        Piece king = new King(Color.WHITE);

        assertThat(king.isKnight()).isFalse();
    }

    @Test
    @DisplayName("킹인지 확인")
    void isKing() {
        Piece king = new King(Color.WHITE);

        assertThat(king.isKing()).isTrue();
    }
}
