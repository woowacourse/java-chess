package chess.domain.board;

import chess.domain.square.File;
import chess.domain.square.Rank;
import chess.domain.square.Square;
import chess.domain.piece.ColorType;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("체스판 생성")
public class BoardFactoryTest {

    @DisplayName("체스판을 생성한다.")
    @Test
    void createBoard() {
        // given
        Map<Square, Piece> expected = createExpectedBoard();
        BoardFactory boardFactory = new BoardFactory();

        // when
        Map<Square, Piece> actual = boardFactory.create();

        // then
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    private Map<Square, Piece> createExpectedBoard() {
        Map<Square, Piece> expected = new HashMap<>();

        List<PieceType> pieceTypeOrder = List.of(PieceType.ROOK, PieceType.KNIGHT, PieceType.BISHOP, PieceType.QUEEN,
                PieceType.KING, PieceType.BISHOP, PieceType.KNIGHT, PieceType.ROOK);

        makeWhitePiece(pieceTypeOrder, expected);
        makeBlackPiece(pieceTypeOrder, expected);
        makeEmptyPiece(expected);

        return expected;
    }

    private void makeEmptyPiece(Map<Square, Piece> expected) {
        for (Rank rank : Arrays.copyOfRange(Rank.values(), 2, 6)) {
            for (File file : File.values()) {
                expected.put(Square.of(file, rank), new Piece(PieceType.EMPTY, ColorType.EMPTY));
            }
        }
    }

    private void makeBlackPiece(List<PieceType> pieceTypeOrder, Map<Square, Piece> expected) {
        Iterator<PieceType> pieceTypeIterator = pieceTypeOrder.iterator();
        Iterator<File> fileIterator = Arrays.stream(File.values()).iterator();

        while (fileIterator.hasNext() && pieceTypeIterator.hasNext()) {
            File file = fileIterator.next();
            expected.put(Square.of(file, Rank.EIGHT), new Piece(pieceTypeIterator.next(), ColorType.BLACK));
            expected.put(Square.of(file, Rank.SEVEN), new Piece(PieceType.PAWN, ColorType.BLACK));
        }
    }

    private void makeWhitePiece(List<PieceType> pieceTypeOrder, Map<Square, Piece> expected) {
        Iterator<File> fileIterator = Arrays.stream(File.values()).iterator();
        Iterator<PieceType> pieceTypeIterator = pieceTypeOrder.iterator();

        while (fileIterator.hasNext() && pieceTypeIterator.hasNext()) {
            File file = fileIterator.next();
            expected.put(Square.of(file, Rank.ONE), new Piece(pieceTypeIterator.next(), ColorType.WHITE));
            expected.put(Square.of(file, Rank.TWO), new Piece(PieceType.PAWN, ColorType.WHITE));
        }
    }
}
