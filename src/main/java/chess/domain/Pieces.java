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
        makePiece(deque, pieceList);
        makePawns(pieceList, 1);
    }

    private static void makePawns(final List<Piece> pieceList, final int file) {
        for (int rank = FIRST_RANK; rank <= LAST_RANK; rank++) {
            pieceList.add(Piece.of((char) rank, file, Shape.PAWN));
        }
    }

    private static void makePiece(final Deque<Character> deque, final List<Piece> pieceList) {
        for (int rank = FIRST_RANK; rank < MIDDLE_RANK; rank++) {
            if (!deque.isEmpty()) {
                Character shapeValue = deque.pollFirst();
                Shape shape = Shape.findByWhiteName(shapeValue);
                pieceList.add(Piece.of((char) rank, 0, shape));
                pieceList.add(Piece.of((char) (201 - rank), 0, shape));
            }
        }

        pieceList.add(Piece.of('d', 0, Shape.QUEEN));
        pieceList.add(Piece.of('e', 0, Shape.KING));
    }

    public long getShapeCount(final Shape shape) {
        return pieces.stream()
                .filter(piece -> piece.isSameShape(shape))
                .count();
    }
}
