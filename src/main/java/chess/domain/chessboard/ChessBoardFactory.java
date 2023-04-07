package chess.domain.chessboard;

import chess.domain.piece.*;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChessBoardFactory {

    public ChessBoard generate() {
        return new ChessBoard(makePieces());
    }

    private Map<Position, Piece> makePieces() {
        final HashMap<Position, Piece> pieces = new HashMap<>();
        addRooks(pieces);
        addKnights(pieces);
        addBishops(pieces);
        addQueens(pieces);
        addKings(pieces);
        addPawns(pieces);
        addEmptyPieces(pieces);
        return pieces;
    }

    private void addRooks(final HashMap<Position, Piece> pieces) {
        final List<Rook> blackRooks = Rook.getRooksOf(Color.BLACK);
        final List<Rook> whiteRooks = Rook.getRooksOf(Color.WHITE);
        pieces.put(Position.of(Rank.EIGHT, File.A), blackRooks.get(0));
        pieces.put(Position.of(Rank.EIGHT, File.H), blackRooks.get(1));
        pieces.put(Position.of(Rank.ONE, File.A), whiteRooks.get(0));
        pieces.put(Position.of(Rank.ONE, File.H), whiteRooks.get(1));
    }

    private void addKnights(final HashMap<Position, Piece> pieces) {
        final List<Knight> blackKnights = Knight.getKnightsOf(Color.BLACK);
        final List<Knight> whiteKnights = Knight.getKnightsOf(Color.WHITE);
        pieces.put(Position.of(Rank.EIGHT, File.B), blackKnights.get(0));
        pieces.put(Position.of(Rank.EIGHT, File.G), blackKnights.get(1));
        pieces.put(Position.of(Rank.ONE, File.B), whiteKnights.get(0));
        pieces.put(Position.of(Rank.ONE, File.G), whiteKnights.get(1));
    }

    private void addBishops(final HashMap<Position, Piece> pieces) {
        final List<Bishop> blackBishops = Bishop.getBishopsOf(Color.BLACK);
        final List<Bishop> whiteBishops = Bishop.getBishopsOf(Color.WHITE);
        pieces.put(Position.of(Rank.EIGHT, File.C), blackBishops.get(0));
        pieces.put(Position.of(Rank.EIGHT, File.F), blackBishops.get(1));
        pieces.put(Position.of(Rank.ONE, File.C), whiteBishops.get(0));
        pieces.put(Position.of(Rank.ONE, File.F), whiteBishops.get(1));
    }

    private void addQueens(final HashMap<Position, Piece> pieces) {
        pieces.put(Position.of(Rank.EIGHT, File.D), Queen.getQueenOf(Color.BLACK));
        pieces.put(Position.of(Rank.ONE, File.D), Queen.getQueenOf(Color.WHITE));
    }

    private void addKings(final HashMap<Position, Piece> pieces) {
        pieces.put(Position.of(Rank.EIGHT, File.E), King.getKingOf(Color.BLACK));
        pieces.put(Position.of(Rank.ONE, File.E), King.getKingOf(Color.WHITE));
    }

    private void addPawns(final HashMap<Position, Piece> pieces) {
        final List<Pawn> blackPawns = Pawn.getPawnsOf(Color.BLACK);
        final List<Pawn> whitePawns = Pawn.getPawnsOf(Color.WHITE);
        for (File file : File.values()) {
            pieces.put(Position.of(Rank.SEVEN, file), blackPawns.get(file.ordinal()));
            pieces.put(Position.of(Rank.TWO, file), whitePawns.get(file.ordinal()));
        }
    }

    private void addEmptyPieces(final HashMap<Position, Piece> pieces) {
        for (File file : File.values()) {
            pieces.put(Position.of(Rank.THREE, file), EmptyPiece.getInstance());
            pieces.put(Position.of(Rank.FOUR, file), EmptyPiece.getInstance());
            pieces.put(Position.of(Rank.FIVE, file), EmptyPiece.getInstance());
            pieces.put(Position.of(Rank.SIX, file), EmptyPiece.getInstance());
        }
    }
}
