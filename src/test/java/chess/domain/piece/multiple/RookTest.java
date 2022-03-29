package chess.domain.piece.multiple;

import static chess.domain.piece.Piece.createBlackPiece;
import static chess.domain.piece.Piece.createWhitePiece;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.ChessBoard;
import chess.domain.Position;
import chess.domain.piece.PieceRule;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class RookTest {

    private PieceRule rook;
    private Position source;

    @BeforeEach
    void setUp() {
        rook = new Rook();
        source = Position.of('d', '4');
    }

    @Test
    @DisplayName("룩 기물 생성")
    void createRook() {
        assertThat(rook).isInstanceOf(Rook.class);
    }

    @ParameterizedTest
    @CsvSource(value = {"c,5", "c,6"})
    @DisplayName("목표 지점이 이동 가능 경로를 벗어나면 예외 발생")
    void emptyDirection(char col, char row) {
        Position target = Position.of(col, row);
        ChessBoard chessBoard = new ChessBoard(Map.of(source, createWhitePiece(new Rook())));

        assertThatThrownBy(() -> rook.move(source, target, chessBoard))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("움직일 수 없는 곳입니다.");
    }

    @Test
    @DisplayName("가로막히고 목표지점이 비어 있으면 이동 불가능")
    void isMovableToNotClearEmptyPosition() {
        Position target = Position.of('d', '8');
        ChessBoard chessBoard = new ChessBoard(Map.of(
                source, createWhitePiece(new Rook()),
                Position.of('d', '7'), createBlackPiece(new Rook())));

        assertThatThrownBy(() -> rook.move(source, target, chessBoard))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("움직일 수 없는 곳입니다.");
    }

    @Test
    @DisplayName("가로막히고 목표지점에 기물이 존재시 이동 불가능")
    void isMovableToNotClearPiecePosition() {
        Position target = Position.of('d', '8');
        ChessBoard chessBoard = new ChessBoard(Map.of(
                source, createWhitePiece(new Rook()),
                Position.of('d', '7'), createBlackPiece(new Rook()),
                target, createBlackPiece(new Rook())));

        assertThatThrownBy(() -> rook.move(source, target, chessBoard))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("움직일 수 없는 곳입니다.");
    }
}
