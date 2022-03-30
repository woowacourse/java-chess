package chess.domain.board;

import chess.domain.Turn;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ChessboardTest {

    private Map<Position, Piece> board;

    @BeforeEach
    void setUp() {
        board = Chessboard.initializedChessboard().getBoard();
    }

    @Test
    @DisplayName("체스판 map 사이즈 확인")
    void checkSizeOfChessboard() {
        assertThat(board.keySet().size()).isEqualTo(64);
    }

    @Test
    @DisplayName("현재 위치와 이동하려는 위치가 같은 경우 예외 발생")
    void checkSamePosition() {
        Chessboard chessboard = Chessboard.initializedChessboard();

        assertThatThrownBy(() -> chessboard.movePiece(new Position(0, 0),
                new Position(0, 0), new Turn()))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("현재 위치와 같은 위치로 이동할 수 없습니다.");
    }

    @Test
    @DisplayName("이동하려는 위치에 기물이 없는 경우 예외 발생")
    void checkBlankTarget() {
        Chessboard chessboard = Chessboard.initializedChessboard();

        assertThatThrownBy(() -> chessboard.movePiece(new Position(2, 0),
                new Position(3, 0), new Turn()))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("이동하려는 위치에 기물이 없습니다.");
    }

    @Test
    @DisplayName("상대편의 기물을 움직이려는 경우 예외 발생")
    void checkWrongTurn() {
        Chessboard chessboard = Chessboard.initializedChessboard();
        Turn turn = new Turn();
        assertThatThrownBy(() -> chessboard.movePiece(new Position(1, 0),
                new Position(1, 1), turn))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("상대편의 기물은 움직일 수 없습니다.");
    }

    @Test
    @DisplayName("주어진 좌표 후보들중에 기물이 있는 좌표가 있다면 예외 발생")
    void checkCandidatesOfPossibleCoordinates() {
        Chessboard chessboard = Chessboard.initializedChessboard();
        Turn turn = new Turn();
        assertThatThrownBy(() -> chessboard.movePiece(new Position(7, 0),
                new Position(5, 0), turn))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("가로막는 기물이 있습니다.");
    }
}
