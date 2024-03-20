package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

class BoardFactoryTest {

    @Test
    @DisplayName("보드를 생성한다.")
    void createBoard() {
        Board actual = BoardFactory.createBoard();

        Map<Position, Piece> expected = new HashMap<>();
        expected.put(new Position(new Rank(8), File.a), new Piece(PieceName.ROOK, PieceColor.BLACK));
        expected.put(new Position(new Rank(8), File.b), new Piece(PieceName.KNIGHT, PieceColor.BLACK));
        expected.put(new Position(new Rank(8), File.c), new Piece(PieceName.BISHOP, PieceColor.BLACK));
        expected.put(new Position(new Rank(8), File.d), new Piece(PieceName.QUEEN, PieceColor.BLACK));
        expected.put(new Position(new Rank(8), File.e), new Piece(PieceName.KING, PieceColor.BLACK));
        expected.put(new Position(new Rank(8), File.f), new Piece(PieceName.BISHOP, PieceColor.BLACK));
        expected.put(new Position(new Rank(8), File.g), new Piece(PieceName.KNIGHT, PieceColor.BLACK));
        expected.put(new Position(new Rank(8), File.h), new Piece(PieceName.ROOK, PieceColor.BLACK));

        expected.put(new Position(new Rank(7), File.a), new Piece(PieceName.PAWN, PieceColor.BLACK));
        expected.put(new Position(new Rank(7), File.b), new Piece(PieceName.PAWN, PieceColor.BLACK));
        expected.put(new Position(new Rank(7), File.c), new Piece(PieceName.PAWN, PieceColor.BLACK));
        expected.put(new Position(new Rank(7), File.d), new Piece(PieceName.PAWN, PieceColor.BLACK));
        expected.put(new Position(new Rank(7), File.e), new Piece(PieceName.PAWN, PieceColor.BLACK));
        expected.put(new Position(new Rank(7), File.f), new Piece(PieceName.PAWN, PieceColor.BLACK));
        expected.put(new Position(new Rank(7), File.g), new Piece(PieceName.PAWN, PieceColor.BLACK));
        expected.put(new Position(new Rank(7), File.h), new Piece(PieceName.PAWN, PieceColor.BLACK));

        expected.put(new Position(new Rank(1), File.a), new Piece(PieceName.ROOK, PieceColor.WHITE));
        expected.put(new Position(new Rank(1), File.b), new Piece(PieceName.KNIGHT, PieceColor.WHITE));
        expected.put(new Position(new Rank(1), File.c), new Piece(PieceName.BISHOP, PieceColor.WHITE));
        expected.put(new Position(new Rank(1), File.d), new Piece(PieceName.QUEEN, PieceColor.WHITE));
        expected.put(new Position(new Rank(1), File.e), new Piece(PieceName.KING, PieceColor.WHITE));
        expected.put(new Position(new Rank(1), File.f), new Piece(PieceName.BISHOP, PieceColor.WHITE));
        expected.put(new Position(new Rank(1), File.g), new Piece(PieceName.KNIGHT, PieceColor.WHITE));
        expected.put(new Position(new Rank(1), File.h), new Piece(PieceName.ROOK, PieceColor.WHITE));

        expected.put(new Position(new Rank(2), File.a), new Piece(PieceName.PAWN, PieceColor.WHITE));
        expected.put(new Position(new Rank(2), File.b), new Piece(PieceName.PAWN, PieceColor.WHITE));
        expected.put(new Position(new Rank(2), File.c), new Piece(PieceName.PAWN, PieceColor.WHITE));
        expected.put(new Position(new Rank(2), File.d), new Piece(PieceName.PAWN, PieceColor.WHITE));
        expected.put(new Position(new Rank(2), File.e), new Piece(PieceName.PAWN, PieceColor.WHITE));
        expected.put(new Position(new Rank(2), File.f), new Piece(PieceName.PAWN, PieceColor.WHITE));
        expected.put(new Position(new Rank(2), File.g), new Piece(PieceName.PAWN, PieceColor.WHITE));
        expected.put(new Position(new Rank(2), File.h), new Piece(PieceName.PAWN, PieceColor.WHITE));

        assertThat(actual).usingRecursiveComparison().isEqualTo(new Board(expected));
    }

}
