package chess.domain;

import chess.domain.pieces.*;
import chess.domain.position.Column;
import chess.domain.position.Position;

import chess.domain.position.Row;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class BoardInitiator implements Initiator {

    public Map<Position, Piece> initiate() {
        final Map<Position, Piece> pieces = new HashMap<>();
        initBoard(pieces);
        return pieces;
    }

    private void initBoard(final Map<Position, Piece> pieces) {
        initRooks(pieces);
        initKnights(pieces);
        initBishops(pieces);
        initQueens(pieces);
        initKings(pieces);
        initPawns(pieces);
    }

    private void initRooks(Map<Position, Piece> pieces) {
        for (Column column : List.of(Column.A, Column.H)) {
            initWhitePiece(pieces, column, new Rook());
            initBlackPiece(pieces, column, new Rook());
        }
    }

    private void initKnights(Map<Position, Piece> pieces) {
        for (Column column : List.of(Column.B, Column.G)) {
            initWhitePiece(pieces, column, new Knight());
            initBlackPiece(pieces, column, new Knight());
        }
    }

    private void initBishops(Map<Position, Piece> pieces) {
        for (Column column : List.of(Column.C, Column.F)) {
            initWhitePiece(pieces, column, new Bishop());
            initBlackPiece(pieces, column, new Bishop());
        }
    }

    private void initQueens(Map<Position, Piece> pieces) {
        initWhitePiece(pieces, Column.D, new Queen());
        initBlackPiece(pieces, Column.D, new Queen());
    }

    private void initKings(Map<Position, Piece> pieces) {
        initWhitePiece(pieces, Column.E, new King());
        initBlackPiece(pieces, Column.E, new King());
    }

    private void initPawns(Map<Position, Piece> pieces) {
        initWhitePawns(pieces);
        initBlackPawns(pieces);
    }

    private void initBlackPawns(Map<Position, Piece> pieces) {
        for (Column column : Column.values()) {
            pieces.put(Position.valueOf(column, Row.SEVEN), new Piece(Color.BLACK, new Pawn()));
        }
    }

    private void initWhitePawns(Map<Position, Piece> pieces) {
        for (Column column : Column.values()) {
            pieces.put(Position.valueOf(column, Row.TWO), new Piece(Color.WHITE, new Pawn()));
        }
    }

    private void initWhitePiece(Map<Position, Piece> pieces, Column column, Type type) {
        pieces.put(Position.valueOf(column, Row.ONE), new Piece(Color.WHITE, type));
    }

    private void initBlackPiece(Map<Position, Piece> pieces, Column column, Type type) {
        pieces.put(Position.valueOf(column, Row.EIGHT), new Piece(Color.BLACK, type));
    }
}
