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

public class PawnTest {

    private static final Board emptyBoard = new Board(HashMap::new);

    @Test
    @DisplayName("흰색 폰이 이동할 수 있는 범위로 이동")
    void forwardWhite() {
        Pawn pawn = new Pawn(Color.WHITE);

        assertThatCode(() -> pawn.checkMovingRange(emptyBoard, Position.from("a2"), Position.from("a3")))
                .doesNotThrowAnyException();
    }

    @DisplayName("흰색 폰이 이동할 수 없는 범위로 이동 시 예외 발생")
    @ParameterizedTest
    @ValueSource(strings = {"f6", "f5", "f4", "e4", "d4", "d5", "d6"})
    void forwardWhiteException(String to) {
        Pawn pawn = new Pawn(Color.WHITE);

        assertThatThrownBy(() -> pawn.checkMovingRange(emptyBoard, Position.from("e5"), Position.from(to)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("폰은 앞으로 한 칸만 이동할 수 있습니다.");
    }

    @DisplayName("시작 위치에 있는 흰색 폰이 이동할 수 있는 범위로 이동")
    @ParameterizedTest
    @ValueSource(strings = {"a3", "a4"})
    void forwardToInitialPositionWhite(String to) {
        Pawn pawn = new Pawn(Color.WHITE);

        assertThatCode(() -> pawn.checkMovingRange(emptyBoard, Position.from("a2"), Position.from(to)))
                .doesNotThrowAnyException();
    }

    @DisplayName("흰색 폰이 첫 시작 위치에서 움직인 곳에 다른 기물이 있는 경우 예외 발생")
    @ParameterizedTest
    @ValueSource(strings = {"a3", "a4"})
    void forwardToInitialPositionWhiteException(String to) {
        Pawn pawn = new Pawn(Color.WHITE);

        final Board mockBoard = new Board(() -> new HashMap<>(Map.of(Position.from(to), new Pawn(Color.BLACK))));

        assertThatThrownBy(() -> pawn.checkMovingRange(mockBoard, Position.from("a2"), Position.from(to)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("폰은 앞으로 한 칸만 이동할 수 있습니다.");
    }

    @Test
    @DisplayName("흰색 폰 대각선에 검정 폰이 있는 경우 이동 가능")
    void moveDiagonalWhite() {
        Pawn pawn = new Pawn(Color.WHITE);

        final Board mockBoard = new Board(() -> new HashMap<>(Map.of(Position.from("b3"), new Pawn(Color.BLACK))));

        assertThatCode(() -> pawn.checkMovingRange(mockBoard, Position.from("a2"), Position.from("b3")))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("흰색 폰 대각선에 검정 폰이 없는 경우 이동 시 예외 발생")
    void moveDiagonalWhiteException() {
        Pawn pawn = new Pawn(Color.WHITE);

        assertThatThrownBy(() -> pawn.checkMovingRange(emptyBoard, Position.from("a2"), Position.from("b3")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("폰은 앞으로 한 칸만 이동할 수 있습니다.");
    }

    @Test
    @DisplayName("검은색 폰이 이동할 수 있는 범위로 이동")
    void forwardBlack() {
        Pawn pawn = new Pawn(Color.BLACK);

        assertThatCode(() -> pawn.checkMovingRange(emptyBoard, Position.from("a7"), Position.from("a6")))
                .doesNotThrowAnyException();
    }

    @DisplayName("검은색 폰이 이동할 수 없는 범위로 이동 시 예외 발생")
    @ParameterizedTest
    @ValueSource(strings = {"d4", "d5", "d6", "e6", "f6", "f5", "f4"})
    void forwardBlackException() {
        Pawn pawn = new Pawn(Color.BLACK);

        assertThatThrownBy(() -> pawn.checkMovingRange(emptyBoard, Position.from("e5"), Position.from("a6")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("폰은 앞으로 한 칸만 이동할 수 있습니다.");
    }

    @DisplayName("시작 위치에 있는 검은색 폰이 이동할 수 있는 범위로 이동")
    @ParameterizedTest
    @ValueSource(strings = {"a6", "a5"})
    void forwardToInitialPositionBlack(String to) {
        Pawn pawn = new Pawn(Color.BLACK);

        assertThatCode(() -> pawn.checkMovingRange(emptyBoard, Position.from("a7"), Position.from(to)))
                .doesNotThrowAnyException();
    }

    @DisplayName("검은색 폰이 첫 시작 위치에서 움직인 곳에 다른 기물이 있는 경우 예외 발생")
    @ParameterizedTest
    @ValueSource(strings = {"a6", "a5"})
    void forwardToInitialPositionBlackException(String to) {
        Pawn pawn = new Pawn(Color.WHITE);

        final Board mockBoard = new Board(() -> new HashMap<>(Map.of(Position.from(to), new Pawn(Color.BLACK))));

        assertThatThrownBy(() -> pawn.checkMovingRange(mockBoard, Position.from("a7"), Position.from(to)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("폰은 앞으로 한 칸만 이동할 수 있습니다.");
    }

    @Test
    @DisplayName("검정 폰 대각선에 흰색 폰이 있는 경우 이동가능")
    void moveDiagonalBlack() {
        Pawn pawn = new Pawn(Color.BLACK);

        final Board mockBoard = new Board(() -> new HashMap<>(Map.of(Position.from("c6"), new Pawn(Color.WHITE))));

        assertThatCode(() -> pawn.checkMovingRange(mockBoard, Position.from("d7"), Position.from("c6")))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("검정 폰 대각선에 흰색 폰이 없는 경우 이동 시 예외 발생")
    void moveDiagonalBlackException() {
        Pawn pawn = new Pawn(Color.BLACK);

        assertThatThrownBy(() -> pawn.checkMovingRange(emptyBoard, Position.from("d7"), Position.from("c6")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("폰은 앞으로 한 칸만 이동할 수 있습니다.");
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
