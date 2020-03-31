package chess.domains.piece;

import chess.domains.position.Position;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public abstract class Piece {
    public static final int NO_MOVE = 0;
    public static final int ONE_BLOCK = 1;
    public static final int TWO_BLOCKS = 2;
    public static final int ONE_BLOCK_UP = 1;
    public static final int ONE_BLOCK_DOWN = -1;
    public static final int TWO_BLOCKS_UP = 2;
    public static final int TWO_BLOCKS_DOWN = -2;
    public static final int INITIAL_ROW_OF_WHITE_PAWN = 2;
    public static final int INITIAL_ROW_OF_BLACK_PAWN = 7;
    public static final int COLUMN_SIZE = 8;
    private static final List<Piece> whitePieces;
    private static final List<Piece> blackPieces;

    static {
        whitePieces = createBundleByColor(PieceColor.WHITE);
        blackPieces = createBundleByColor(PieceColor.BLACK);
    }

    protected final PieceColor pieceColor;
    private final String name;
    private final double score;

    public abstract boolean isValidMove(Position currentPosition, Position targetPosition);

    public Piece(PieceColor pieceColor, String name, double score) {
        this.pieceColor = pieceColor;
        this.name = selectName(pieceColor, name);
        this.score = score;
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

    private String selectName(PieceColor pieceColor, String name) {
        if (pieceColor == PieceColor.BLACK) {
            return name.toUpperCase();
        }
        return name;
    }

    public List<Position> findRoute(Position source, Position target) {
        return source.findRoute(target);
    }

    public boolean isMine(Piece piece) {
        return this.pieceColor == piece.pieceColor;
    }

    public boolean isMine(PieceColor pieceColor) {
        return this.pieceColor == pieceColor;
    }

    public static List<Piece> getBlackPieces() {
        return blackPieces;
    }

    public static List<Piece> getWhitePieces() {
        return whitePieces;
    }

    public String getName() {
        return name;
    }

    public double getScore() {
        return score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Piece)) return false;
        Piece piece = (Piece) o;
        return pieceColor == piece.pieceColor &&
                Objects.equals(name, piece.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pieceColor, name);
    }
}
