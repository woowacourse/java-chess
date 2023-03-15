package chess.domain;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    private Pieces(final List<Piece> pieces) {
        this.pieces = pieces;
    }

    public static Pieces createBlackPieces(Pieces whitePieces) {
        List<Piece> pawns = getBlackPawns(whitePieces);
        List<Piece> piecesWithoutPawns = getPiecesWithoutPawn(whitePieces);
        return getBlackPieces(pawns, piecesWithoutPawns);
    }

    private static List<Piece> getBlackPawns(Pieces whitePieces) {
        return whitePieces.pieces.stream()
                .filter(piece -> piece.isSameShape(Shape.PAWN))
                .map(piece -> piece.getNewPiece(LAST_FILE_OF_BLACK))
                .collect(Collectors.toList());
    }

    private static List<Piece> getPiecesWithoutPawn(Pieces whitePieces) {
        return whitePieces.pieces.stream()
                .filter(piece -> !piece.isSameShape(Shape.PAWN))
                .map(piece -> piece.getNewPiece(FIRST_FILE_OF_BLACK))
                .collect(Collectors.toList());
    }

    private static Pieces getBlackPieces(List<Piece> pawns, List<Piece> piecesWithoutPawns) {
        List<Piece> pieceList = Stream.concat(pawns.stream(), piecesWithoutPawns.stream())
                .collect(Collectors.toList());
        return new Pieces(pieceList);
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

    private static void makePawns(final List<Piece> pieceList, final int rank) {
        for (int file = FIRST_RANK; file <= LAST_RANK; file++) {
            pieceList.add(Piece.from(rank, (char) file, Shape.PAWN));
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
        pieceList.add(Piece.from(FIRST_FILE_OF_WHITE, (char) frontPosition,shape));

        int backPosition = FIRST_RANK + LAST_RANK - frontPosition;
        pieceList.add(Piece.from(FIRST_FILE_OF_WHITE, (char) backPosition, shape));
    }

    private static void makeQueenAndKing(final List<Piece> pieceList, final int rank) {
        pieceList.add(Piece.from(rank, QUEEN_DEFAULT_RANK_POSITION, Shape.QUEEN));
        pieceList.add(Piece.from(rank, KING_DEFAULT_RANK_POSITION, Shape.KING));
    }

    public long getShapeCount(final Shape shape) {
        return pieces.stream()
                .filter(piece -> piece.isSameShape(shape))
                .count();
    }

    @Override
    public String toString() {
        return "Pieces{" +
                "pieces=" + pieces +
                '}';
    }
}
