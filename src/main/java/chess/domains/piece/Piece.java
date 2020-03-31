package chess.domains.piece;

import chess.domains.position.Position;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static chess.domains.board.Board.COLUMN_SIZE;

public abstract class Piece {
    static final int NO_MOVE = 0;
    static final int ONE_BLOCK = 1;
    static final int TWO_BLOCKS = 2;
    static final int ONE_BLOCK_UP = 1;
    static final int ONE_BLOCK_DOWN = -1;
    static final int TWO_BLOCKS_UP = 2;
    static final int TWO_BLOCKS_DOWN = -2;

    private static final List<Piece> whitePieces;
    private static final List<Piece> blackPieces;
    private static final List<Piece> blankPieces;

    protected final PieceColor pieceColor;
    private final PieceType type;

    static {
        whitePieces = createBundleByColor(PieceColor.WHITE);
        blackPieces = createBundleByColor(PieceColor.BLACK);
        blankPieces = createBlankBundle();
    }

    public Piece(PieceColor pieceColor, PieceType type) {
        this.pieceColor = pieceColor;
        this.type = type;
    }

    private static List<Piece> createBundleByColor(PieceColor color) {
        List<Piece> bundle = new ArrayList<>(Arrays.asList(
                new Rook(color), new Knight(color), new Bishop(color), new King(color),
                new Queen(color), new Bishop(color), new Knight(color), new Rook(color)));
        for (int i = 0; i < COLUMN_SIZE; i++) {
            bundle.add(new Pawn(color));
        }
        return bundle;
    }

    private static List<Piece> createBlankBundle() {
        return new ArrayList<>(Arrays.asList(
                new Blank(), new Blank(), new Blank(), new Blank(),
                new Blank(), new Blank(), new Blank(), new Blank()));
    }

    public abstract boolean isValidMove(Position currentPosition, Position targetPosition);

    public List<Position> findRoute(Position source, Position target) {
        return source.findRoute(target);
    }

    public boolean isMine(PieceColor pieceColor) {
        return this.pieceColor == pieceColor;
    }

    public boolean isMine(Piece piece) {
        return isMine(piece.pieceColor);
    }

    public boolean is(PieceType pieceType) {
        return this.type == pieceType;
    }

    public static List<Piece> getBlackPieces() {
        return blackPieces;
    }

    public static List<Piece> getWhitePieces() {
        return whitePieces;
    }

    public static List<Piece> getBlankPieces() {
        return blankPieces;
    }

    public String name() {
        if (pieceColor == PieceColor.BLACK) {
            return type.getName().toUpperCase();
        }
        return type.getName();
    }

    public double score() {
        return type.getScore();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Piece)) return false;
        Piece piece = (Piece) o;
        return pieceColor == piece.pieceColor &&
                type == piece.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pieceColor, type);
    }
}
