package chess.domain;

import chess.domain.pieces.*;
import chess.domain.position.Column;
import chess.domain.position.Position;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class Board {

    private final Map<Position, Piece> pieces = new HashMap<>();

    public Board() {
        initGame();
    }

    private void initGame() {
        initRooks();
        initKnights();
        initBishops();
        initQueens();
        initKings();
        initPawns();
    }

    private void initRooks() {
        for (String column : List.of("a", "h")) {
            initWhitePiece(column, new Rook());
            initBlackPiece(column, new Rook());
        }
    }

    private void initKnights() {
        for (String column : List.of("b", "g")) {
            initWhitePiece(column, new Knight());
            initBlackPiece(column, new Knight());
        }
    }

    private void initBishops() {
        for (String column : List.of("c", "f")) {
            initWhitePiece(column, new Bishop());
            initBlackPiece(column, new Bishop());
        }
    }

    private void initQueens() {
        initWhitePiece("d", new Queen());
        initBlackPiece("d", new Queen());
    }

    private void initKings() {
        initWhitePiece("e", new King());
        initBlackPiece("e", new King());
    }

    private void initPawns() {
        initWhitePawns();
        initBlackPawns();
    }

    private void initBlackPawns() {
        for (Column column : Column.values()) {
            pieces.put(Position.of(column.name() + "7"), new Piece(Color.BLACK, new Pawn()));
        }
    }

    private void initWhitePawns() {
        for (Column column : Column.values()) {
            pieces.put(Position.of(column.name() + "2"), new Piece(Color.WHITE, new Pawn()));
        }
    }

    private void initWhitePiece(String column, Type type) {
        pieces.put(Position.of(column + "1"), new Piece(Color.WHITE, type));
    }

    private void initBlackPiece(String column, Type type) {
        pieces.put(Position.of(column + "8"), new Piece(Color.BLACK, type));
    }

    public Piece piece(Position position) {
        return pieces.get(position);
    }
}
