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

public class QueenTest {

    private static final Board emptyBoard = new Board(HashMap::new);

    @DisplayName("퀸은 상하좌우 대각선 방향으로 이동 가능")
    @ParameterizedTest
    @ValueSource(strings = {"e7", "g5", "e3", "c5", "c7", "g7", "g3", "c3"})
    void move(String to) {
        Piece queen = new Queen(Color.WHITE);

        assertThatCode(() -> queen.checkPieceMoveRange(emptyBoard, Position.from("e5"), Position.from(to)))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("퀸의 도착지가 상하좌우 대각선 방향이 아닌 경우 예외 발생")
    void invalidMove() {
        Piece queen = new Queen(Color.WHITE);

        assertThatThrownBy(() -> queen.checkPieceMoveRange(emptyBoard, Position.from("e5"), Position.from("f7")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("퀸은 상하좌우 대각선 방향으로만 이동할 수 있습니다.");
    }

    @DisplayName("퀸의 이동 경로에 다른 기물이 있을 경우 예외 발생")
    @ParameterizedTest
    @ValueSource(strings = {"e8", "e1", "a5", "h5", "b8", "h8", "a1", "h2"})
    void invalid(String to) {
        Map<Position, Piece> pieceExistBoard = new HashMap<>();
        final Piece other = new Queen(Color.WHITE);
        pieceExistBoard.put(Position.from("e6"), other);
        pieceExistBoard.put(Position.from("e4"), other);
        pieceExistBoard.put(Position.from("d5"), other);
        pieceExistBoard.put(Position.from("f5"), other);
        pieceExistBoard.put(Position.from("d6"), other);
        pieceExistBoard.put(Position.from("f6"), other);
        pieceExistBoard.put(Position.from("b2"), other);
        pieceExistBoard.put(Position.from("f4"), other);
        final Board mockBoard = new Board(() -> pieceExistBoard);

        Piece queen = new Queen(Color.WHITE);
        assertThatThrownBy(() -> queen.checkPieceMoveRange(mockBoard, Position.from("e5"), Position.from(to)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이동 경로에 기물이 존재합니다.");
    }

    @Test
    @DisplayName("킹인지 확인")
    void isKing() {
        Piece queen = new Queen(Color.WHITE);

        assertThat(queen.isKing()).isFalse();
    }
}
