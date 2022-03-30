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

class BishopTest {

    private PieceRule bishop;
    private Position source;

    @BeforeEach
    void setUp() {
        bishop = new Bishop();
        source = Position.of('d', '4');
    }

    @Test
    @DisplayName("비숍 기물 생성")
    void createBishop() {
        assertThat(bishop).isInstanceOf(Bishop.class);
    }

    @ParameterizedTest
    @CsvSource(value = {"b,5", "c,7", "d,6"})
    @DisplayName("목표 지점이 이동 가능 경로를 벗어나면 예외 발생")
    void emptyDirection(char col, char row) {
        Position target = Position.of(col, row);
        ChessBoard chessBoard = new ChessBoard(Map.of(source, createWhitePiece(new Bishop())));

        assertThatThrownBy(() -> bishop.move(source, target, chessBoard))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("움직일 수 없는 곳입니다.");
    }

    @Test
    @DisplayName("가로막히고 목표지점이 비어 있으면 예외 발생")
    void isMovableToNotClearEmptyPosition() {
        Position target = Position.of('b', '6');
        ChessBoard chessBoard = new ChessBoard(Map.of(
                source, createWhitePiece(new Bishop()),
                Position.of('c', '5'), createBlackPiece(new Bishop())));

        assertThatThrownBy(() -> bishop.move(source, target, chessBoard))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("움직일 수 없는 곳입니다.");
    }

    @Test
    @DisplayName("가로막히고 목표지점에 기물이 존재시 예외 발생")
    void isMovableToNotClearPiecePosition() {
        Position target = Position.of('b', '6');
        ChessBoard chessBoard = new ChessBoard(Map.of(
                source, createWhitePiece(new Bishop()),
                Position.of('c', '5'), createBlackPiece(new Bishop()),
                target, createBlackPiece(new Bishop())));

        assertThatThrownBy(() -> bishop.move(source, target, chessBoard))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("움직일 수 없는 곳입니다.");
    }
}
