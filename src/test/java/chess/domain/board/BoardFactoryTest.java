package chess.domain.board;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import chess.domain.piece.*;
import chess.domain.square.File;
import chess.domain.square.Rank;
import chess.domain.square.Square;
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

        Map<Square, Piece> expected = new HashMap<>();
        expected.put(new Square(File.a, Rank.EIGHT), new Rook(PieceColor.BLACK));
        expected.put(new Square(File.b, Rank.EIGHT), new Knight(PieceColor.BLACK));
        expected.put(new Square(File.c, Rank.EIGHT), new Bishop(PieceColor.BLACK));
        expected.put(new Square(File.d, Rank.EIGHT), new Queen(PieceColor.BLACK));
        expected.put(new Square(File.e, Rank.EIGHT), new King(PieceColor.BLACK));
        expected.put(new Square(File.f, Rank.EIGHT), new Bishop(PieceColor.BLACK));
        expected.put(new Square(File.g, Rank.EIGHT), new Knight(PieceColor.BLACK));
        expected.put(new Square(File.h, Rank.EIGHT), new Rook(PieceColor.BLACK));

        expected.put(new Square(File.a, Rank.SEVEN), new Pawn(PieceColor.BLACK));
        expected.put(new Square(File.b, Rank.SEVEN), new Pawn(PieceColor.BLACK));
        expected.put(new Square(File.c, Rank.SEVEN), new Pawn(PieceColor.BLACK));
        expected.put(new Square(File.d, Rank.SEVEN), new Pawn(PieceColor.BLACK));
        expected.put(new Square(File.e, Rank.SEVEN), new Pawn(PieceColor.BLACK));
        expected.put(new Square(File.f, Rank.SEVEN), new Pawn(PieceColor.BLACK));
        expected.put(new Square(File.g, Rank.SEVEN), new Pawn(PieceColor.BLACK));
        expected.put(new Square(File.h, Rank.SEVEN), new Pawn(PieceColor.BLACK));

        expected.put(new Square(File.a, Rank.ONE), new Rook(PieceColor.WHITE));
        expected.put(new Square(File.b, Rank.ONE), new Knight(PieceColor.WHITE));
        expected.put(new Square(File.c, Rank.ONE), new Bishop(PieceColor.WHITE));
        expected.put(new Square(File.d, Rank.ONE), new Queen(PieceColor.WHITE));
        expected.put(new Square(File.e, Rank.ONE), new King(PieceColor.WHITE));
        expected.put(new Square(File.f, Rank.ONE), new Bishop(PieceColor.WHITE));
        expected.put(new Square(File.g, Rank.ONE), new Knight(PieceColor.WHITE));
        expected.put(new Square(File.h, Rank.ONE), new Rook(PieceColor.WHITE));

        expected.put(new Square(File.a, Rank.TWO), new Pawn(PieceColor.WHITE));
        expected.put(new Square(File.b, Rank.TWO), new Pawn(PieceColor.WHITE));
        expected.put(new Square(File.c, Rank.TWO), new Pawn(PieceColor.WHITE));
        expected.put(new Square(File.d, Rank.TWO), new Pawn(PieceColor.WHITE));
        expected.put(new Square(File.e, Rank.TWO), new Pawn(PieceColor.WHITE));
        expected.put(new Square(File.f, Rank.TWO), new Pawn(PieceColor.WHITE));
        expected.put(new Square(File.g, Rank.TWO), new Pawn(PieceColor.WHITE));
        expected.put(new Square(File.h, Rank.TWO), new Pawn(PieceColor.WHITE));

        assertThat(actual).usingRecursiveComparison().isEqualTo(new Board(expected));
    }

}
