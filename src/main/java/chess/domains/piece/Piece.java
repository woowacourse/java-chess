package chess.domains.piece;

import chess.domains.position.Position;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public abstract class Piece {
    private static final List<Piece> whitePieces;
    private static final List<Piece> blackPieces;

    static {
        whitePieces = createBundleByColor(PieceColor.WHITE);
        blackPieces = createBundleByColor(PieceColor.BLACK);
    }

    protected final PieceColor pieceColor;
    private final String name;

    public abstract boolean isValidMove(Position currentPosition, Position targetPosition);

    public Piece(PieceColor pieceColor, String name) {
        this.pieceColor = pieceColor;
        this.name = selectName(pieceColor, name);
    }

    private static List<Piece> createBundleByColor(PieceColor color) {
        List<Piece> bundle = new ArrayList<>(Arrays.asList(
                new Rook(color), new Knight(color), new Bishop(color), new King(color),
                new Queen(color), new Bishop(color), new Knight(color), new Rook(color)));
        for (int i = 0; i < 8; i++) {
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
