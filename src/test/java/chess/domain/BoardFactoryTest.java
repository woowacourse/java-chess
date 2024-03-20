package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.piece.Bishop;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

@DisplayName("보드 팩토리")
class BoardFactoryTest {

    @Test
    @DisplayName("보드를 생성한다.")
    void createBoard() {
        Board actual = BoardFactory.createBoard();

        Map<Position, Piece> expected = new HashMap<>();
        expected.put(new Position(File.a, Rank.EIGHT), new Rook(PieceColor.BLACK));
        expected.put(new Position(File.b, Rank.EIGHT), new Knight(PieceColor.BLACK));
        expected.put(new Position(File.c, Rank.EIGHT), new Bishop(PieceColor.BLACK));
        expected.put(new Position(File.d, Rank.EIGHT), new Queen(PieceColor.BLACK));
        expected.put(new Position(File.e, Rank.EIGHT), new King(PieceColor.BLACK));
        expected.put(new Position(File.f, Rank.EIGHT), new Bishop(PieceColor.BLACK));
        expected.put(new Position(File.g, Rank.EIGHT), new Knight(PieceColor.BLACK));
        expected.put(new Position(File.h, Rank.EIGHT), new Rook(PieceColor.BLACK));

        expected.put(new Position(File.a, Rank.SEVEN), new Pawn(PieceColor.BLACK));
        expected.put(new Position(File.b, Rank.SEVEN), new Pawn(PieceColor.BLACK));
        expected.put(new Position(File.c, Rank.SEVEN), new Pawn(PieceColor.BLACK));
        expected.put(new Position(File.d, Rank.SEVEN), new Pawn(PieceColor.BLACK));
        expected.put(new Position(File.e, Rank.SEVEN), new Pawn(PieceColor.BLACK));
        expected.put(new Position(File.f, Rank.SEVEN), new Pawn(PieceColor.BLACK));
        expected.put(new Position(File.g, Rank.SEVEN), new Pawn(PieceColor.BLACK));
        expected.put(new Position(File.h, Rank.SEVEN), new Pawn(PieceColor.BLACK));

        expected.put(new Position(File.a, Rank.ONE), new Rook(PieceColor.WHITE));
        expected.put(new Position(File.b, Rank.ONE), new Knight(PieceColor.WHITE));
        expected.put(new Position(File.c, Rank.ONE), new Bishop(PieceColor.WHITE));
        expected.put(new Position(File.d, Rank.ONE), new Queen(PieceColor.WHITE));
        expected.put(new Position(File.e, Rank.ONE), new King(PieceColor.WHITE));
        expected.put(new Position(File.f, Rank.ONE), new Bishop(PieceColor.WHITE));
        expected.put(new Position(File.g, Rank.ONE), new Knight(PieceColor.WHITE));
        expected.put(new Position(File.h, Rank.ONE), new Rook(PieceColor.WHITE));

        expected.put(new Position(File.a, Rank.TWO), new Pawn(PieceColor.WHITE));
        expected.put(new Position(File.b, Rank.TWO), new Pawn(PieceColor.WHITE));
        expected.put(new Position(File.c, Rank.TWO), new Pawn(PieceColor.WHITE));
        expected.put(new Position(File.d, Rank.TWO), new Pawn(PieceColor.WHITE));
        expected.put(new Position(File.e, Rank.TWO), new Pawn(PieceColor.WHITE));
        expected.put(new Position(File.f, Rank.TWO), new Pawn(PieceColor.WHITE));
        expected.put(new Position(File.g, Rank.TWO), new Pawn(PieceColor.WHITE));
        expected.put(new Position(File.h, Rank.TWO), new Pawn(PieceColor.WHITE));

        assertThat(actual).usingRecursiveComparison().isEqualTo(new Board(expected));
    }

}
