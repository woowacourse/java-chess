package chess.domain;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static chess.domain.Shape.BISHOP;
import static chess.domain.Shape.KNIGHT;
import static chess.domain.Shape.ROOK;

public final class Pieces {

    public static final char FIRST_RANK = 'a';
    public static final char LAST_RANK = 'h';
    public static final int FIRST_FILE_OF_WHITE = 0;
    public static final int FIRST_FILE_OF_BLACK = 7;
    private static final char MIDDLE_RANK = 'd';
    private static final char QUEEN_DEFAULT_RANK_POSITION = 'd';
    private static final char KING_DEFAULT_RANK_POSITION = 'e';
    private static final int LAST_FILE_OF_WHITE = 1;
    private static final int LAST_FILE_OF_BLACK = 6;

    private final List<Piece> pieces;

    private Pieces(final List<Piece> pieces) {
        this.pieces = pieces;
    }

    public static Pieces createBlackPieces(final Pieces whitePieces) {
        List<Piece> pawns = getBlackPawns(whitePieces);
        List<Piece> piecesWithoutPawns = getPiecesWithoutPawn(whitePieces);
        return getBlackPieces(pawns, piecesWithoutPawns);
    }

    private static List<Piece> getBlackPawns(final Pieces whitePieces) {
        return whitePieces.pieces.stream()
                .filter(piece -> piece.isSameShape(Shape.PAWN))
                .map(piece -> piece.getNewPiece(LAST_FILE_OF_BLACK))
                .collect(Collectors.toList());
    }

    private static List<Piece> getPiecesWithoutPawn(final Pieces whitePieces) {
        return whitePieces.pieces.stream()
                .filter(piece -> !piece.isSameShape(Shape.PAWN))
                .map(piece -> piece.getNewPiece(FIRST_FILE_OF_BLACK))
                .collect(Collectors.toList());
    }

    private static Pieces getBlackPieces(final List<Piece> pawns, final List<Piece> piecesWithoutPawns) {
        List<Piece> pieceList = Stream.concat(pawns.stream(), piecesWithoutPawns.stream())
                .collect(Collectors.toList());
        return new Pieces(pieceList);
    }

    public static Pieces createWhitePieces() {
        List<Piece> pieceList = new ArrayList<>();
        addWhitePieces(pieceList);
        return new Pieces(pieceList);
    }

    private static void addWhitePieces(final List<Piece> pieceList) {
        makeRookAndBishopAndKnight(pieceList);
        makePawns(pieceList, LAST_FILE_OF_WHITE);
        makeQueenAndKing(pieceList);
    }

    private static void makePawns(final List<Piece> pieceList, final int rank) {
        for (int file = FIRST_RANK; file <= LAST_RANK; file++) {
            pieceList.add(Piece.from(rank, (char) file, Shape.PAWN));
        }
    }

    private static void makeRookAndBishopAndKnight(final List<Piece> pieceList) {
        final Deque<Character> whiteNames = new ArrayDeque<>(
                List.of(ROOK.getWhiteName(), KNIGHT.getWhiteName(), BISHOP.getWhiteName())
        );

        for (int frontPosition = FIRST_RANK; frontPosition < MIDDLE_RANK; frontPosition++) {
            char shapeValue = whiteNames.pollFirst();
            Shape shape = Shape.findShapeByWhiteName(shapeValue);
            addPiecePairs(pieceList, frontPosition, shape);
        }
    }

    private static void addPiecePairs(final List<Piece> pieceList, final int frontPosition, final Shape shape) {
        pieceList.add(Piece.from(FIRST_FILE_OF_WHITE, (char) frontPosition, shape));

        int backPosition = FIRST_RANK + LAST_RANK - frontPosition;
        pieceList.add(Piece.from(FIRST_FILE_OF_WHITE, (char) backPosition, shape));
    }

    private static void makeQueenAndKing(final List<Piece> pieceList) {
        pieceList.add(Piece.from(FIRST_FILE_OF_WHITE, QUEEN_DEFAULT_RANK_POSITION, Shape.QUEEN));
        pieceList.add(Piece.from(FIRST_FILE_OF_WHITE, KING_DEFAULT_RANK_POSITION, Shape.KING));
    }

    public long getShapeCount(final Shape shape) {
        return pieces.stream()
                .filter(piece -> piece.isSameShape(shape))
                .count();
    }

    public Piece findPiece(final Position findPosition) {
        return pieces.stream()
                .filter(piece -> piece.isSamePosition(findPosition))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("위치를 확인해주세요."));
    }

    public boolean hasPosition(final Position findPosition) {
        return pieces.stream()
                .anyMatch(piece -> piece.isSamePosition(findPosition));
    }

    public void remove(final Position changedPosition) {
        pieces.removeIf(piece -> piece.isSamePosition(changedPosition));
    }

    public List<Piece> getPieces() {
        return pieces;
    }

    @Override
    public String toString() {
        return "Pieces{" +
                "pieces=" + pieces +
                '}';
    }

}
