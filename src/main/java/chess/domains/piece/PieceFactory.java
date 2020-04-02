package chess.domains.piece;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PieceFactory {
    private static final List<Piece> whitePieces;
    private static final List<Piece> whitePawnsPieces;
    private static final List<Piece> blackPieces;
    private static final List<Piece> blackPawnsPieces;
    private static final List<Piece> blankPieces;

    static {
        whitePieces = createBundleByColor(PieceColor.WHITE);
        whitePawnsPieces = createPawnsBundleByColor(PieceColor.WHITE);
        blackPieces = createBundleByColor(PieceColor.BLACK);
        blackPawnsPieces = createPawnsBundleByColor(PieceColor.BLACK);
        blankPieces = createBlankBundle();
    }

    private PieceFactory() {
    }

    private static List<Piece> createBundleByColor(PieceColor color) {
        return new ArrayList<>(Arrays.asList(
                new Rook(color), new Knight(color), new Bishop(color), new King(color),
                new Queen(color), new Bishop(color), new Knight(color), new Rook(color)));
    }


    private static List<Piece> createPawnsBundleByColor(PieceColor color) {
        return new ArrayList<>(Arrays.asList(
                new Pawn(color), new Pawn(color), new Pawn(color), new Pawn(color),
                new Pawn(color), new Pawn(color), new Pawn(color), new Pawn(color)));
    }

    private static List<Piece> createBlankBundle() {
        return new ArrayList<>(Arrays.asList(
                new Blank(), new Blank(), new Blank(), new Blank(),
                new Blank(), new Blank(), new Blank(), new Blank()));
    }

    public static List<Piece> getWhitePieces() {
        return whitePieces;
    }

    public static List<Piece> getWhitePawnsPieces() {
        return whitePawnsPieces;
    }

    public static List<Piece> getBlackPieces() {
        return blackPieces;
    }

    public static List<Piece> getBlackPawnsPieces() {
        return blackPawnsPieces;
    }

    public static List<Piece> getBlankPieces() {
        return blankPieces;
    }
}
