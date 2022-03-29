package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class RookTest {

    private static final Board emptyBoard = new Board(HashMap::new);

    @DisplayName("흰 룩 앞으로 전진")
    @ParameterizedTest
    @ValueSource(strings = {"a4", "a7"})
    void forward(String toPosition) {
        Piece rook = new Rook(Color.WHITE);

        assertThatCode(() -> rook.checkMovingRange(emptyBoard, Position.from("a3"), Position.from(toPosition)))
                .doesNotThrowAnyException();
    }

    @DisplayName("흰 룩 뒤로 이동")
    @ParameterizedTest
    @ValueSource(strings = {"e4", "e3"})
    void back(String toPosition) {
        Piece rook = new Rook(Color.WHITE);

        assertThatCode(() -> rook.checkMovingRange(emptyBoard, Position.from("e5"), Position.from(toPosition)))
                .doesNotThrowAnyException();
    }

    @DisplayName("흰 룩 오른쪽으로 이동")
    @ParameterizedTest
    @ValueSource(strings = {"b3", "h3"})
    void right(String toPosition) {
        Piece rook = new Rook(Color.WHITE);

        assertThatCode(() -> rook.checkMovingRange(emptyBoard, Position.from("a3"), Position.from(toPosition)))
                .doesNotThrowAnyException();
    }

    @DisplayName("흰 룩 왼쪽으로 이동")
    @ParameterizedTest
    @ValueSource(strings = {"d5", "a5"})
    void left(String toPosition) {
        Piece rook = new Rook(Color.WHITE);

        assertThatCode(() -> rook.checkMovingRange(emptyBoard, Position.from("e5"), Position.from(toPosition)))
                .doesNotThrowAnyException();
    }

    @DisplayName("흰 룩 대각선 이동시 예외")
    @ParameterizedTest
    @ValueSource(strings = {"e5", "c5", "c3", "e3"})
    void diagonal(String toPosition) {
        Piece rook = new Rook(Color.WHITE);

        assertThatThrownBy(() -> rook.checkMovingRange(emptyBoard, Position.from("d4"), Position.from(toPosition)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("룩은 대각선으로 이동할 수 없습니다.");
    }

    @Test
    @DisplayName("킹인지 확인")
    void isKing() {
        Piece rook = new Rook(Color.WHITE);

        assertThat(rook.isKing()).isFalse();
    }
}
