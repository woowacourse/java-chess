package chess.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChessBoardFactory {

    public ChessBoard generate() {
        return new ChessBoard(makePieces());
    }

    private Map<Square, Piece> makePieces() {
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

    private void addRooks(final HashMap<Square, Piece> pieces) {
        final List<Rook> blackRooks = Rook.of(Team.BLACK);
        final List<Rook> whiteRooks = Rook.of(Team.WHITE);
        pieces.put(Square.of(Rank.ONE, File.A), blackRooks.get(0));
        pieces.put(Square.of(Rank.ONE, File.H), blackRooks.get(1));
        pieces.put(Square.of(Rank.EIGHT, File.A), whiteRooks.get(0));
        pieces.put(Square.of(Rank.EIGHT, File.H), whiteRooks.get(1));
    }

    private void addKnights(final HashMap<Square, Piece> pieces) {
        final List<Knight> blackKnights = Knight.of(Team.BLACK);
        final List<Knight> whiteKnights = Knight.of(Team.WHITE);
        pieces.put(Square.of(Rank.ONE, File.B), blackKnights.get(0));
        pieces.put(Square.of(Rank.ONE, File.G), blackKnights.get(1));
        pieces.put(Square.of(Rank.EIGHT, File.B), whiteKnights.get(0));
        pieces.put(Square.of(Rank.EIGHT, File.G), whiteKnights.get(1));
    }

    private void addBishops(final HashMap<Square, Piece> pieces) {
        final List<Bishop> blackBishops = Bishop.of(Team.BLACK);
        final List<Bishop> whiteBishops = Bishop.of(Team.WHITE);
        pieces.put(Square.of(Rank.ONE, File.C), blackBishops.get(0));
        pieces.put(Square.of(Rank.ONE, File.F), blackBishops.get(1));
        pieces.put(Square.of(Rank.EIGHT, File.C), whiteBishops.get(0));
        pieces.put(Square.of(Rank.EIGHT, File.F), whiteBishops.get(1));
    }

    private void addQueens(final HashMap<Square, Piece> pieces) {
        pieces.put(Square.of(Rank.ONE, File.D), Queen.of(Team.BLACK));
        pieces.put(Square.of(Rank.EIGHT, File.D), Queen.of(Team.WHITE));
    }

    private void addKings(final HashMap<Square, Piece> pieces) {
        pieces.put(Square.of(Rank.ONE, File.E), King.of(Team.BLACK));
        pieces.put(Square.of(Rank.EIGHT, File.E), King.of(Team.WHITE));
    }

    private void addPawns(final HashMap<Square, Piece> pieces) {
        final List<Pawn> blackPawns = Pawn.of(Team.BLACK);
        final List<Pawn> whitePawns = Pawn.of(Team.WHITE);
        for (File file : File.values()) {
            pieces.put(Square.of(Rank.TWO, file), blackPawns.get(file.ordinal()));
            pieces.put(Square.of(Rank.SEVEN, file), whitePawns.get(file.ordinal()));
        }
    }

    private void addEmptyPieces(final HashMap<Square, Piece> pieces) {
        for (File file : File.values()) {
            pieces.put(Square.of(Rank.THREE, file), new EmptyPiece());
            pieces.put(Square.of(Rank.FOUR, file), new EmptyPiece());
            pieces.put(Square.of(Rank.FIVE, file), new EmptyPiece());
            pieces.put(Square.of(Rank.SIX, file), new EmptyPiece());
        }
    }
}
