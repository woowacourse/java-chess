package chess.domain.board;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.piece.Piece;
import chess.domain.piece.PieceColor;
import chess.domain.piece.PieceType;
import chess.domain.square.File;
import chess.domain.square.Rank;
import chess.domain.square.Square;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

@DisplayName("보드 팩토리")
class BoardFactoryTest {

    @Test
    @DisplayName("보드를 생성한다.")
    void createBoard() {
        final Board actual = BoardFactory.createBoard();

        final Map<Square, Piece> expected = Map.ofEntries(
                Map.entry(new Square(File.a, Rank.EIGHT), new Piece(PieceType.ROOK, PieceColor.BLACK)),
                Map.entry(new Square(File.b, Rank.EIGHT), new Piece(PieceType.KNIGHT, PieceColor.BLACK)),
                Map.entry(new Square(File.c, Rank.EIGHT), new Piece(PieceType.BISHOP, PieceColor.BLACK)),
                Map.entry(new Square(File.d, Rank.EIGHT), new Piece(PieceType.QUEEN, PieceColor.BLACK)),
                Map.entry(new Square(File.e, Rank.EIGHT), new Piece(PieceType.KING, PieceColor.BLACK)),
                Map.entry(new Square(File.f, Rank.EIGHT), new Piece(PieceType.BISHOP, PieceColor.BLACK)),
                Map.entry(new Square(File.g, Rank.EIGHT), new Piece(PieceType.KNIGHT, PieceColor.BLACK)),
                Map.entry(new Square(File.h, Rank.EIGHT), new Piece(PieceType.ROOK, PieceColor.BLACK)),

                Map.entry(new Square(File.a, Rank.SEVEN), new Piece(PieceType.PAWN, PieceColor.BLACK)),
                Map.entry(new Square(File.b, Rank.SEVEN), new Piece(PieceType.PAWN, PieceColor.BLACK)),
                Map.entry(new Square(File.c, Rank.SEVEN), new Piece(PieceType.PAWN, PieceColor.BLACK)),
                Map.entry(new Square(File.d, Rank.SEVEN), new Piece(PieceType.PAWN, PieceColor.BLACK)),
                Map.entry(new Square(File.e, Rank.SEVEN), new Piece(PieceType.PAWN, PieceColor.BLACK)),
                Map.entry(new Square(File.f, Rank.SEVEN), new Piece(PieceType.PAWN, PieceColor.BLACK)),
                Map.entry(new Square(File.g, Rank.SEVEN), new Piece(PieceType.PAWN, PieceColor.BLACK)),
                Map.entry(new Square(File.h, Rank.SEVEN), new Piece(PieceType.PAWN, PieceColor.BLACK)),

                Map.entry(new Square(File.a, Rank.ONE), new Piece(PieceType.ROOK, PieceColor.WHITE)),
                Map.entry(new Square(File.b, Rank.ONE), new Piece(PieceType.KNIGHT, PieceColor.WHITE)),
                Map.entry(new Square(File.c, Rank.ONE), new Piece(PieceType.BISHOP, PieceColor.WHITE)),
                Map.entry(new Square(File.d, Rank.ONE), new Piece(PieceType.QUEEN, PieceColor.WHITE)),
                Map.entry(new Square(File.e, Rank.ONE), new Piece(PieceType.KING, PieceColor.WHITE)),
                Map.entry(new Square(File.f, Rank.ONE), new Piece(PieceType.BISHOP, PieceColor.WHITE)),
                Map.entry(new Square(File.g, Rank.ONE), new Piece(PieceType.KNIGHT, PieceColor.WHITE)),
                Map.entry(new Square(File.h, Rank.ONE), new Piece(PieceType.ROOK, PieceColor.WHITE)),

                Map.entry(new Square(File.a, Rank.TWO), new Piece(PieceType.PAWN, PieceColor.WHITE)),
                Map.entry(new Square(File.b, Rank.TWO), new Piece(PieceType.PAWN, PieceColor.WHITE)),
                Map.entry(new Square(File.c, Rank.TWO), new Piece(PieceType.PAWN, PieceColor.WHITE)),
                Map.entry(new Square(File.d, Rank.TWO), new Piece(PieceType.PAWN, PieceColor.WHITE)),
                Map.entry(new Square(File.e, Rank.TWO), new Piece(PieceType.PAWN, PieceColor.WHITE)),
                Map.entry(new Square(File.f, Rank.TWO), new Piece(PieceType.PAWN, PieceColor.WHITE)),
                Map.entry(new Square(File.g, Rank.TWO), new Piece(PieceType.PAWN, PieceColor.WHITE)),
                Map.entry(new Square(File.h, Rank.TWO), new Piece(PieceType.PAWN, PieceColor.WHITE)));

        assertThat(actual).usingRecursiveComparison().isEqualTo(new Board(expected));
    }

}
