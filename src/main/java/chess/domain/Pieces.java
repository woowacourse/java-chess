package chess.domain;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.Optional;
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

    public Pieces() {
        this.pieces = Collections.emptyList();
    }

    public Pieces createBlackPieces() {
        Pieces pieces = Pieces.from(this.pieces);
        List<Piece> pawns = getBlackPawns(pieces);
        List<Piece> piecesWithoutPawns = getPiecesWithoutPawn(pieces);
        return getBlackPieces(pawns, piecesWithoutPawns);
    }

    private List<Piece> getBlackPawns(final Pieces whitePieces) {
        return whitePieces.pieces.stream()
                .filter(piece -> piece.isSameShape(Shape.PAWN))
                .map(piece -> piece.getNewPiece(LAST_FILE_OF_BLACK))
                .collect(Collectors.toList());
    }

    private List<Piece> getPiecesWithoutPawn(final Pieces whitePieces) {
        return whitePieces.pieces.stream()
                .filter(piece -> !piece.isSameShape(Shape.PAWN))
                .map(piece -> piece.getNewPiece(FIRST_FILE_OF_BLACK))
                .collect(Collectors.toList());
    }

    private Pieces getBlackPieces(final List<Piece> pawns, final List<Piece> piecesWithoutPawns) {
        List<Piece> pieceList = Stream.concat(pawns.stream(), piecesWithoutPawns.stream())
                .collect(Collectors.toList());
        return new Pieces(pieceList);
    }

    public Pieces createWhitePieces() {
        List<Piece> pieceList = new ArrayList<>();
        addWhitePieces(pieceList);
        return new Pieces(pieceList);
    }

    private void addWhitePieces(final List<Piece> pieceList) {
        makeRookAndBishopAndKnight(pieceList);
        makePawns(pieceList, LAST_FILE_OF_WHITE);
        makeQueenAndKing(pieceList);
    }

    private void makePawns(final List<Piece> pieceList, final int rank) {
        for (int file = FIRST_RANK; file <= LAST_RANK; file++) {
            pieceList.add(Piece.from(rank, (char) file, Shape.PAWN));
        }
    }

    private void makeRookAndBishopAndKnight(final List<Piece> pieceList) {
        final Deque<Shape> whitePieceNames = new ArrayDeque<>(
                List.of(ROOK,KNIGHT,BISHOP)
        );

        for (int frontPosition = FIRST_RANK; frontPosition < MIDDLE_RANK; frontPosition++) {
            Shape shape = whitePieceNames.pollFirst();
            addPiecePairs(pieceList, frontPosition, shape);
        }
    }

    private void addPiecePairs(final List<Piece> pieceList, final int frontPosition, final Shape shape) {
        pieceList.add(Piece.from(FIRST_FILE_OF_WHITE, (char) frontPosition, shape));

        int backPosition = FIRST_RANK + LAST_RANK - frontPosition;
        pieceList.add(Piece.from(FIRST_FILE_OF_WHITE, (char) backPosition, shape));
    }

    private void makeQueenAndKing(final List<Piece> pieceList) {
        pieceList.add(Piece.from(FIRST_FILE_OF_WHITE, QUEEN_DEFAULT_RANK_POSITION, Shape.QUEEN));
        pieceList.add(Piece.from(FIRST_FILE_OF_WHITE, KING_DEFAULT_RANK_POSITION, Shape.KING));
    }

    public static Pieces from(List<Piece> dbPieces) {
        return new Pieces(dbPieces);
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

    public Optional<Piece> remove(final Position changedPosition) {
        Optional<Piece> findPiece = pieces.stream()
                .filter(piece -> piece.isSamePosition(changedPosition))
                .findFirst();

        findPiece.ifPresent(pieces::remove);

        return findPiece;
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
