package chess.piece;

import java.util.Arrays;
import java.util.List;

public abstract class Piece {
    private static final List<Piece> whitePieces;
    private static final List<Piece> blackPieces;

    static {
        whitePieces = createBundleByColor(PieceColor.WHITE);
        blackPieces = createBundleByColor(PieceColor.BLACK);
    }

    protected final PieceColor pieceColor;

    public Piece(PieceColor pieceColor) {
        this.pieceColor = pieceColor;
    }

    private static List<Piece> createBundleByColor(PieceColor color) {
        List<Piece> bundle = Arrays.asList(
                new Rook(color), new Knight(color), new Bishop(color), new King(color),
                new Queen(color), new Bishop(color), new Knight(color), new Rook(color));
        for (int i = 0; i < 8; i++) {
            bundle.add(new Pawn(color));
        }
        return bundle;
    }

    public static List<Piece> getBlackPieces() {
        return blackPieces;
    }

    public static List<Piece> getWhitePieces() {
        return whitePieces;
    }
}
