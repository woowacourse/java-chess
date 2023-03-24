package chess.domain;

import chess.domain.chesspiece.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChessBoardFactory {

    public static ChessBoard generate() {
        return new ChessBoard(makePieces());
    }

    private static Map<Square, Piece> makePieces() {
        final HashMap<Square, Piece> pieces = new HashMap<>();
        addRooks(pieces);
        addKnights(pieces);
        addBishops(pieces);
        addQueens(pieces);
        addKings(pieces);
        addPawns(pieces);
        addEmptyPieces(pieces);
        return pieces;
    }

    private static void addRooks(final HashMap<Square, Piece> pieces) {
        final Rook blackRook = Rook.of(Side.BLACK);
        final Rook whiteRook = Rook.of(Side.WHITE);
        pieces.put(Square.of(Rank.EIGHT, File.A), blackRook);
        pieces.put(Square.of(Rank.EIGHT, File.H), blackRook);
        pieces.put(Square.of(Rank.ONE, File.A), whiteRook);
        pieces.put(Square.of(Rank.ONE, File.H), whiteRook);
    }

    private static void addKnights(final HashMap<Square, Piece> pieces) {
        final Knight blackKnight = Knight.of(Side.BLACK);
        final Knight whiteKnight = Knight.of(Side.WHITE);
        pieces.put(Square.of(Rank.EIGHT, File.B), blackKnight);
        pieces.put(Square.of(Rank.EIGHT, File.G), blackKnight);
        pieces.put(Square.of(Rank.ONE, File.B), whiteKnight);
        pieces.put(Square.of(Rank.ONE, File.G), whiteKnight);
    }

    private static void addBishops(final HashMap<Square, Piece> pieces) {
        final Bishop blackBishop = Bishop.of(Side.BLACK);
        final Bishop whiteBishop = Bishop.of(Side.WHITE);
        pieces.put(Square.of(Rank.EIGHT, File.C), blackBishop);
        pieces.put(Square.of(Rank.EIGHT, File.F), blackBishop);
        pieces.put(Square.of(Rank.ONE, File.C), whiteBishop);
        pieces.put(Square.of(Rank.ONE, File.F), whiteBishop);
    }

    private static void addQueens(final HashMap<Square, Piece> pieces) {
        pieces.put(Square.of(Rank.EIGHT, File.D), Queen.of(Side.BLACK));
        pieces.put(Square.of(Rank.ONE, File.D), Queen.of(Side.WHITE));
    }

    private static void addKings(final HashMap<Square, Piece> pieces) {
        pieces.put(Square.of(Rank.EIGHT, File.E), King.of(Side.BLACK));
        pieces.put(Square.of(Rank.ONE, File.E), King.of(Side.WHITE));
    }

    private static void addPawns(final HashMap<Square, Piece> pieces) {
        final List<Pawn> blackPawns = Pawn.of(Side.BLACK);
        final List<Pawn> whitePawns = Pawn.of(Side.WHITE);
        for (File file : File.values()) {
            pieces.put(Square.of(Rank.SEVEN, file), blackPawns.get(file.ordinal()));
            pieces.put(Square.of(Rank.TWO, file), whitePawns.get(file.ordinal()));
        }
    }

    private static void addEmptyPieces(final HashMap<Square, Piece> pieces) {
        for (File file : File.values()) {
            pieces.put(Square.of(Rank.THREE, file), EmptyPiece.getInstance());
            pieces.put(Square.of(Rank.FOUR, file), EmptyPiece.getInstance());
            pieces.put(Square.of(Rank.FIVE, file), EmptyPiece.getInstance());
            pieces.put(Square.of(Rank.SIX, file), EmptyPiece.getInstance());
        }
    }
}
