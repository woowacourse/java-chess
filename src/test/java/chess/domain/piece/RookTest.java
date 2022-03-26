package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class RookTest {

    private static final Board emptyBoard = new Board(HashMap::new);

    @DisplayName("흰 룩 앞으로 전진")
    @ParameterizedTest
    @ValueSource(strings = {"a4", "a7"})
    void forward(String toPosition) {
        Piece rook = new Rook(Color.WHITE);

        assertThatCode(() -> rook.checkPieceMoveRange(emptyBoard, Position.from("a3"), Position.from(toPosition)))
                .doesNotThrowAnyException();
    }

    @DisplayName("흰 룩 뒤로 이동")
    @ParameterizedTest
    @ValueSource(strings = {"e4", "e3"})
    void back(String toPosition) {
        Piece rook = new Rook(Color.WHITE);

        assertThatCode(() -> rook.checkPieceMoveRange(emptyBoard, Position.from("e5"), Position.from(toPosition)))
                .doesNotThrowAnyException();
    }

    @DisplayName("흰 룩 오른쪽으로 이동")
    @ParameterizedTest
    @ValueSource(strings = {"b3", "h3"})
    void right(String toPosition) {
        Piece rook = new Rook(Color.WHITE);

        assertThatCode(() -> rook.checkPieceMoveRange(emptyBoard, Position.from("a3"), Position.from(toPosition)))
                .doesNotThrowAnyException();
    }

    @DisplayName("흰 룩 왼쪽으로 이동")
    @ParameterizedTest
    @ValueSource(strings = {"d5", "a5"})
    void left(String toPosition) {
        Piece rook = new Rook(Color.WHITE);

        assertThatCode(() -> rook.checkPieceMoveRange(emptyBoard, Position.from("e5"), Position.from(toPosition)))
                .doesNotThrowAnyException();
    }

    @DisplayName("흰 룩 대각선 이동시 예외")
    @ParameterizedTest
    @ValueSource(strings = {"e5", "c5", "c3", "e3"})
    void diagonal(String toPosition) {
        Piece rook = new Rook(Color.WHITE);

        assertThatThrownBy(() -> rook.checkPieceMoveRange(emptyBoard, Position.from("d4"), Position.from(toPosition)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("룩은 대각선으로 이동할 수 없습니다.");
    }

    @DisplayName("룩 이동거리 사이에 기물이 있는 경우 예외")
    @ParameterizedTest
    @ValueSource(strings = {"e5", "c5", "c3", "e3"})
    void invalid() {
        final Board mockBoard = new Board(() -> new HashMap<>(Map.of(Position.from("a2"), new Rook(Color.WHITE))));

        Piece rook = new Rook(Color.WHITE);

        assertThatThrownBy(() -> rook.checkPieceMoveRange(mockBoard, Position.from("a1"), Position.from("a3")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("이동 경로에 기물이 존재합니다.");
    }
}
