package chess.domain.board;

import chess.domain.piece.Bishop;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceColor;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.position.Column;
import chess.domain.position.Position;
import chess.domain.position.Row;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("보드 팩토리")
class BoardFactoryTest {

    @Test
    @DisplayName("보드를 생성한다.")
    void createBoard() {
        Board actual = BoardFactory.createBoard();

        Map<Position, Piece> expected = new HashMap<>();
        expected.put(new Position(Column.a, Row.EIGHT), new Rook(PieceColor.BLACK));
        expected.put(new Position(Column.b, Row.EIGHT), new Knight(PieceColor.BLACK));
        expected.put(new Position(Column.c, Row.EIGHT), new Bishop(PieceColor.BLACK));
        expected.put(new Position(Column.d, Row.EIGHT), new Queen(PieceColor.BLACK));
        expected.put(new Position(Column.e, Row.EIGHT), new King(PieceColor.BLACK));
        expected.put(new Position(Column.f, Row.EIGHT), new Bishop(PieceColor.BLACK));
        expected.put(new Position(Column.g, Row.EIGHT), new Knight(PieceColor.BLACK));
        expected.put(new Position(Column.h, Row.EIGHT), new Rook(PieceColor.BLACK));

        expected.put(new Position(Column.a, Row.SEVEN), new Pawn(PieceColor.BLACK));
        expected.put(new Position(Column.b, Row.SEVEN), new Pawn(PieceColor.BLACK));
        expected.put(new Position(Column.c, Row.SEVEN), new Pawn(PieceColor.BLACK));
        expected.put(new Position(Column.d, Row.SEVEN), new Pawn(PieceColor.BLACK));
        expected.put(new Position(Column.e, Row.SEVEN), new Pawn(PieceColor.BLACK));
        expected.put(new Position(Column.f, Row.SEVEN), new Pawn(PieceColor.BLACK));
        expected.put(new Position(Column.g, Row.SEVEN), new Pawn(PieceColor.BLACK));
        expected.put(new Position(Column.h, Row.SEVEN), new Pawn(PieceColor.BLACK));

        expected.put(new Position(Column.a, Row.ONE), new Rook(PieceColor.WHITE));
        expected.put(new Position(Column.b, Row.ONE), new Knight(PieceColor.WHITE));
        expected.put(new Position(Column.c, Row.ONE), new Bishop(PieceColor.WHITE));
        expected.put(new Position(Column.d, Row.ONE), new Queen(PieceColor.WHITE));
        expected.put(new Position(Column.e, Row.ONE), new King(PieceColor.WHITE));
        expected.put(new Position(Column.f, Row.ONE), new Bishop(PieceColor.WHITE));
        expected.put(new Position(Column.g, Row.ONE), new Knight(PieceColor.WHITE));
        expected.put(new Position(Column.h, Row.ONE), new Rook(PieceColor.WHITE));

        expected.put(new Position(Column.a, Row.TWO), new Pawn(PieceColor.WHITE));
        expected.put(new Position(Column.b, Row.TWO), new Pawn(PieceColor.WHITE));
        expected.put(new Position(Column.c, Row.TWO), new Pawn(PieceColor.WHITE));
        expected.put(new Position(Column.d, Row.TWO), new Pawn(PieceColor.WHITE));
        expected.put(new Position(Column.e, Row.TWO), new Pawn(PieceColor.WHITE));
        expected.put(new Position(Column.f, Row.TWO), new Pawn(PieceColor.WHITE));
        expected.put(new Position(Column.g, Row.TWO), new Pawn(PieceColor.WHITE));
        expected.put(new Position(Column.h, Row.TWO), new Pawn(PieceColor.WHITE));

        assertThat(actual).usingRecursiveComparison().isEqualTo(new Board(expected));
    }

}
