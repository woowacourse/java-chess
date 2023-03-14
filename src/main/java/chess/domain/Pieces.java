package chess.domain;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public final class Pieces {

    private static final char FIRST_RANK = 'a';
    private static final char LAST_RANK = 'h';
    private static final char MIDDLE_RANK = 'd';
    private static final int FIRST_FILE_OF_WHITE = 0;
    private static final int LAST_FILE_OF_WHITE = 1;
    private static final int FIRST_FILE_OF_BLACK = 7;
    private static final int LAST_FILE_OF_BLACK = 6;
    private static final char QUEEN_DEFAULT_RANK_POSITION = 'd';
    private static final char KING_DEFAULT_RANK_POSITION = 'e';

    private final List<Piece> pieces;

    public Pieces(final List<Piece> pieces) {
        this.pieces = pieces;
    }

    public static Pieces createWhitePieces() {
        List<Piece> pieceList = new ArrayList<>();
        makeWhitePieces(pieceList);
        return new Pieces(pieceList);
    }

    private static void makeWhitePieces(final List<Piece> pieceList) {
        Deque<Character> deque = new ArrayDeque<>(List.of('r', 'n', 'b'));
        makeRookAndBishopAndKnight(deque, pieceList);
        makePawns(pieceList, LAST_FILE_OF_WHITE);
        makeQueenAndKing(pieceList, FIRST_FILE_OF_WHITE);
    }

    private static void makePawns(final List<Piece> pieceList, final int file) {
        for (int rank = FIRST_RANK; rank <= LAST_RANK; rank++) {
            pieceList.add(Piece.of((char) rank, file, Shape.PAWN));
        }
    }

    private static void makeRookAndBishopAndKnight(final Deque<Character> deque, final List<Piece> pieceList) {
        for (int frontPosition = FIRST_RANK; frontPosition < MIDDLE_RANK; frontPosition++) {
            Character shapeValue = deque.pollFirst();
            Shape shape = Shape.findByWhiteName(shapeValue);
            addPiecePairs(pieceList, frontPosition, shape);
        }
    }

    private static void addPiecePairs(List<Piece> pieceList, int frontPosition, Shape shape) {
        pieceList.add(Piece.of((char) frontPosition, FIRST_FILE_OF_WHITE, shape));

        int backPosition = FIRST_RANK + LAST_RANK - frontPosition;
        pieceList.add(Piece.of((char) backPosition, FIRST_FILE_OF_WHITE, shape));
    }

    private static void makeQueenAndKing(final List<Piece> pieceList, final int file) {
        pieceList.add(Piece.of(QUEEN_DEFAULT_RANK_POSITION, file, Shape.QUEEN));
        pieceList.add(Piece.of(KING_DEFAULT_RANK_POSITION, file, Shape.KING));
    }

    public long getShapeCount(final Shape shape) {
        return pieces.stream()
                .filter(piece -> piece.isSameShape(shape))
                .count();
    }
}
