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

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

class BoardFactoryTest {

    @Test
    @DisplayName("보드를 생성한다.")
    void createBoard() {
        final Board actual = BoardFactory.createBoard();

        final Map<Square, Piece> expected = createExpectedBoard();

        assertThat(actual).usingRecursiveComparison().isEqualTo(new Board(expected));
    }

    private Map<Square, Piece> createExpectedBoard() {
        final List<PieceType> piecesArrangement = List.of(
                PieceType.ROOK, PieceType.KNIGHT, PieceType.BISHOP, PieceType.QUEEN,
                PieceType.KING, PieceType.BISHOP, PieceType.KNIGHT, PieceType.ROOK);

        final Map<Square, Piece> expected = new HashMap<>();

        IntStream.range(0, piecesArrangement.size())
                .forEach(i -> expected.put(new Square(File.values()[i], Rank.EIGHT),
                        new Piece(piecesArrangement.get(i), PieceColor.BLACK)));

        IntStream.range(0, piecesArrangement.size())
                .forEach(i -> expected.put(new Square(File.values()[i], Rank.SEVEN),
                        new Piece(PieceType.PAWN, PieceColor.BLACK)));

        IntStream.range(0, piecesArrangement.size())
                .forEach(i -> expected.put(new Square(File.values()[i], Rank.SIX),
                        new Piece(PieceType.EMPTY, PieceColor.NONE)));

        IntStream.range(0, piecesArrangement.size())
                .forEach(i -> expected.put(new Square(File.values()[i], Rank.FIVE),
                        new Piece(PieceType.EMPTY, PieceColor.NONE)));

        IntStream.range(0, piecesArrangement.size())
                .forEach(i -> expected.put(new Square(File.values()[i], Rank.FOUR),
                        new Piece(PieceType.EMPTY, PieceColor.NONE)));

        IntStream.range(0, piecesArrangement.size())
                .forEach(i -> expected.put(new Square(File.values()[i], Rank.THREE),
                        new Piece(PieceType.EMPTY, PieceColor.NONE)));

        IntStream.range(0, piecesArrangement.size())
                .forEach(i -> expected.put(new Square(File.values()[i], Rank.TWO),
                        new Piece(PieceType.PAWN, PieceColor.WHITE)));

        IntStream.range(0, piecesArrangement.size())
                .forEach(i -> expected.put(new Square(File.values()[i], Rank.ONE),
                        new Piece(piecesArrangement.get(i), PieceColor.WHITE)));

        return expected;
    }
}
