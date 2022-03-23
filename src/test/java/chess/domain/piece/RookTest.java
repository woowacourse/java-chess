package chess.domain.piece;

import static chess.domain.piece.Color.BLACK;
import static chess.domain.piece.Color.WHITE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.ChessBoard;
import chess.domain.Position;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class RookTest {

    private Rook rook;
    private ChessBoard chessBoard;

    @BeforeEach
    void setUp() {
        rook = new Rook(Color.WHITE, new Position('c', '3'));

        Piece whietePiece = new Rook(WHITE, new Position('c', '5'));
        Piece darkPiece = new Rook(BLACK, new Position('e', '3'));
        chessBoard = new ChessBoard(Map.of(
                whietePiece.getPosition(), whietePiece,
                darkPiece.getPosition(), darkPiece));
    }

    @Test
    @DisplayName("룩 기물 생성")
    void createRook() {
        assertThat(rook).isInstanceOf(Rook.class);
    }

    @ParameterizedTest
    @CsvSource(value = {"b,4", "b,2", "d,2", "d,4"})
    @DisplayName("룩 위치 이동 불가 예외발생")
    void moveException(char row, char col) {
        Position movePosition = new Position(row, col);

        assertThatThrownBy(() -> rook.move(movePosition, chessBoard))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("룩은 상하좌우 방향으로만 움직일 수 있습니다.");
    }

    @Test
    @DisplayName("해당 위치에 같은 색상의 기물 존재시 예외발생")
    void moveExceptionByExistEqualsColorPiece() {
        Position position = new Position('c', '5');

        assertThatThrownBy(() -> rook.move(position, chessBoard))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("해당 위치에 같은 색상의 기물이 존재하여 움직일 수 없습니다.");
    }
}
