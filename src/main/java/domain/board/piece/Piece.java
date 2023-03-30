package domain.board.piece;

import domain.path.Path;

public final class Piece {

    private static final Piece BLACK_PAWN = Piece.of(PieceType.PAWN, Camp.BLACK);
    private static final Piece WHITE_PAWN = Piece.of(PieceType.PAWN, Camp.WHITE);
    private static final Piece BLACK_BISHOP = Piece.of(PieceType.BISHOP, Camp.BLACK);
    private static final Piece WHITE_BISHOP = Piece.of(PieceType.BISHOP, Camp.WHITE);
    private static final Piece BLACK_ROOK = Piece.of(PieceType.ROOK, Camp.BLACK);
    private static final Piece WHITE_ROOK = Piece.of(PieceType.ROOK, Camp.WHITE);
    private static final Piece BLACK_KNIGHT = Piece.of(PieceType.KNIGHT, Camp.BLACK);
    private static final Piece WHITE_KNIGHT = Piece.of(PieceType.KNIGHT, Camp.WHITE);
    private static final Piece BLACK_QUEEN = Piece.of(PieceType.QUEEN, Camp.BLACK);
    private static final Piece WHITE_QUEEN = Piece.of(PieceType.QUEEN, Camp.WHITE);
    private static final Piece BLACK_KING = Piece.of(PieceType.KING, Camp.BLACK);
    private static final Piece WHITE_KING = Piece.of(PieceType.KING, Camp.WHITE);
    private static final Piece EMPTY = Piece.of(PieceType.EMPTY, Camp.NONE);
    private final PieceState pieceState;

    private Piece(PieceState pieceState) {
        this.pieceState = pieceState;
    }

    public static Piece of(PieceType pieceType, Camp camp) {
        return new Piece(new PieceState(pieceType, camp));
    }

    public static Piece blackPawn() {
        return BLACK_PAWN;
    }

    public static Piece whitePawn() {
        return WHITE_PAWN;
    }

    public static Piece blackBishop() {
        return BLACK_BISHOP;
    }

    public static Piece whiteBishop() {
        return WHITE_BISHOP;
    }

    public static Piece blackRook() {
        return BLACK_ROOK;
    }

    public static Piece whiteRook() {
        return WHITE_ROOK;
    }

    public static Piece blackKnight() {
        return BLACK_KNIGHT;
    }

    public static Piece whiteKnight() {
        return WHITE_KNIGHT;
    }

    public static Piece blackQueen() {
        return BLACK_QUEEN;
    }

    public static Piece whiteQueen() {
        return WHITE_QUEEN;
    }

    public static Piece blackKing() {
        return BLACK_KING;
    }

    public static Piece whiteKing() {
        return WHITE_KING;
    }

    public static Piece empty() {
        return EMPTY;
    }

    public void validatePath(final Path path) {
        pieceState.getType()
            .getPathValidator()
            .validate(path);
    }

    public boolean isEmpty() {
        return pieceState.getType() == PieceType.EMPTY;
    }

    public double getScore() {
        return pieceState.getType().getScore();
    }

    public PieceType getType() {
        return pieceState.getType();
    }

    public Camp getCamp() {
        return pieceState.getCamp();
    }

    @Override
    public String toString() {
        return "Piece{" +
            "pieceState=" + pieceState +
            '}';
    }
}
