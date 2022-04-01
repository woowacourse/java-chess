package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.board.MoveOrder;
import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class RookTest {

    private static final Board emptyBoard = new Board(HashMap::new);

    @DisplayName("흰 룩 앞으로 전진")
    @ParameterizedTest
    @ValueSource(strings = {"a4", "a7"})
    void forward(String to) {
        Piece rook = new Rook(Color.WHITE);

        assertThatCode(() -> rook.checkMoveRange(new MoveOrder(new HashSet<>(), Position.from("a3"), Position.from(to))))
                .doesNotThrowAnyException();
    }

    @DisplayName("흰 룩 뒤로 이동")
    @ParameterizedTest
    @ValueSource(strings = {"e4", "e3"})
    void back(String to) {
        Piece rook = new Rook(Color.WHITE);

        assertThatCode(() -> rook.checkMoveRange(new MoveOrder(new HashSet<>(), Position.from("e5"), Position.from(to))))
                .doesNotThrowAnyException();
    }

    @DisplayName("흰 룩 오른쪽으로 이동")
    @ParameterizedTest
    @ValueSource(strings = {"b3", "h3"})
    void right(String to) {
        Piece rook = new Rook(Color.WHITE);

        assertThatCode(() -> rook.checkMoveRange(new MoveOrder(new HashSet<>(), Position.from("a3"), Position.from(to))))
                .doesNotThrowAnyException();
    }

    @DisplayName("흰 룩 왼쪽으로 이동")
    @ParameterizedTest
    @ValueSource(strings = {"d5", "a5"})
    void left(String to) {
        Piece rook = new Rook(Color.WHITE);

        assertThatCode(() -> rook.checkMoveRange(new MoveOrder(new HashSet<>(), Position.from("e5"), Position.from(to))))
                .doesNotThrowAnyException();
    }

    @DisplayName("흰 룩 대각선 이동시 예외")
    @ParameterizedTest
    @ValueSource(strings = {"e5", "c5", "c3", "e3"})
    void diagonal(String to) {
        Piece rook = new Rook(Color.WHITE);

        assertThatThrownBy(() -> rook.checkMoveRange(new MoveOrder(new HashSet<>(), Position.from("d4"), Position.from(to))))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("이동 불가한 위치입니다.");
    }

    @Test
    @DisplayName("룩 이동거리 사이에 기물이 있는 경우 예외")
    void invalid() {
        Piece rook = new Rook(Color.WHITE);

        assertThatThrownBy(() -> rook.checkMoveRange(new MoveOrder(new HashSet<>(
                Set.of(Position.from("a2")
                )), Position.from("a1"), Position.from("a3"))))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("이동 불가한 위치입니다.");
    }
}
