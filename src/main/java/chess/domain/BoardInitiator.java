package chess.domain;

import chess.domain.pieces.*;
import chess.domain.position.Column;
import chess.domain.position.Position;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class BoardInitiator implements Initiator {

    public Map<Position, Piece> initiate() {
        final Map<Position, Piece> pieces = new HashMap<>();
        initGame(pieces);
        return pieces;
    }

    private void initGame(final Map<Position, Piece> pieces) {
        initRooks(pieces);
        initKnights(pieces);
        initBishops(pieces);
        initQueens(pieces);
        initKings(pieces);
        initPawns(pieces);
    }

    private void initRooks(Map<Position, Piece> pieces) {
        for (String column : List.of("a", "h")) {
            initWhitePiece(pieces, column, new Rook());
            initBlackPiece(pieces, column, new Rook());
        }
    }

    private void initKnights(Map<Position, Piece> pieces) {
        for (String column : List.of("b", "g")) {
            initWhitePiece(pieces, column, new Knight());
            initBlackPiece(pieces, column, new Knight());
        }
    }

    private void initBishops(Map<Position, Piece> pieces) {
        for (String column : List.of("c", "f")) {
            initWhitePiece(pieces, column, new Bishop());
            initBlackPiece(pieces, column, new Bishop());
        }
    }

    private void initQueens(Map<Position, Piece> pieces) {
        initWhitePiece(pieces, "d", new Queen());
        initBlackPiece(pieces, "d", new Queen());
    }

    private void initKings(Map<Position, Piece> pieces) {
        initWhitePiece(pieces, "e", new King());
        initBlackPiece(pieces, "e", new King());
    }

    private void initPawns(Map<Position, Piece> pieces) {
        initWhitePawns(pieces);
        initBlackPawns(pieces);
    }

    private void initBlackPawns(Map<Position, Piece> pieces) {
        for (Column column : Column.values()) {
            pieces.put(Position.of(column.name() + "7"), new Piece(Color.BLACK, new Pawn()));
        }
    }

    private void initWhitePawns(Map<Position, Piece> pieces) {
        for (Column column : Column.values()) {
            pieces.put(Position.of(column.name() + "2"), new Piece(Color.WHITE, new Pawn()));
        }
    }

    private void initWhitePiece(Map<Position, Piece> pieces, String column, Type type) {
        pieces.put(Position.of(column + "1"), new Piece(Color.WHITE, type));
    }

    private void initBlackPiece(Map<Position, Piece> pieces, String column, Type type) {
        pieces.put(Position.of(column + "8"), new Piece(Color.BLACK, type));
    }
}
