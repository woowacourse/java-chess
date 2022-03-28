package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.position.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class BishopTest {

    private static final Board emptyBoard = new Board(HashMap::new);
    private static Board board;

    @BeforeEach
    void beforeEach() {
        Map<Position, Piece> pieceExistBoard = new HashMap<>();

        final Bishop bishop = new Bishop(Color.BLACK);

        pieceExistBoard.put(Position.from("d6"), bishop);
        pieceExistBoard.put(Position.from("f6"), bishop);
        pieceExistBoard.put(Position.from("d4"), bishop);
        pieceExistBoard.put(Position.from("f4"), bishop);
        board = new Board(() -> pieceExistBoard);
    }

    @DisplayName("비숍 대각선 한 칸 이동")
    @ParameterizedTest
    @ValueSource(strings = {"a2", "c2", "a4", "c4"})
    void diagonal(String to) {
        Piece bishop = new Bishop(Color.WHITE);

        assertThatCode(() -> bishop.checkMoveRange(emptyBoard, Position.from("b3"), Position.from(to)))
                .doesNotThrowAnyException();
    }

    @DisplayName("비숍 대각선이 아닌 방향으로 이동 시 예외 발생")
    @ParameterizedTest
    @ValueSource(strings = {"a3", "b2", "b4", "c3", "c5"})
    void notDiagonal(String to) {
        Piece bishop = new Bishop(Color.WHITE);

        assertThatThrownBy(() -> bishop.checkMoveRange(emptyBoard, Position.from("b3"), Position.from(to)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("비숍은 대각선 방향만 이동할 수 있습니다.");
    }

    @DisplayName("비숍 이동 경로에 기물이 있을 경우 예외발생")
    @ParameterizedTest
    @ValueSource(strings = {"b8", "h8", "a1", "h2"})
    void invalid(String to) {
        Piece bishop = new Bishop(Color.WHITE);

        assertThatThrownBy(() -> bishop.checkMoveRange(board, Position.from("e5"), Position.from(to)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("이동 경로에 기물이 존재합니다.");
    }
}
