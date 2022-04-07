package chess.domain.game;

import chess.domain.pieces.*;
import chess.domain.position.Column;
import chess.domain.position.Position;
import chess.domain.position.Row;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class BoardInitializer implements Initializer {

    private static final Column QUEEN_LINE = Column.D;
    private static final Column KING_LINE = Column.E;
    private static final Row BLACK_PAWN_ROW = Row.SEVEN;
    private static final Row WHITE_PAWN_ROW = Row.TWO;
    private static final Row WHITE_PIECE_ROW = Row.ONE;
    private static final Row BLACK_PIECE_ROW = Row.EIGHT;

    private final Map<Position, Piece> pieces = new HashMap<>();

    public Map<Position, Piece> initialize() {
        if (pieces.isEmpty()) {
            initBoard(pieces);
        }
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
            initWhitePiece(pieces, column.name().toLowerCase(), new Rook());
            initBlackPiece(pieces, column.name().toLowerCase(), new Rook());
        }
    }

    private void initKnights(Map<Position, Piece> pieces) {
        for (Column column : List.of(Column.B, Column.G)) {
            initWhitePiece(pieces, column.name().toLowerCase(), new Knight());
            initBlackPiece(pieces, column.name().toLowerCase(), new Knight());
        }
    }

    private void initBishops(Map<Position, Piece> pieces) {
        for (Column column : List.of(Column.C, Column.F)) {
            initWhitePiece(pieces, column.name().toLowerCase(), new Bishop());
            initBlackPiece(pieces, column.name().toLowerCase(), new Bishop());
        }
    }

    private void initQueens(Map<Position, Piece> pieces) {
        initWhitePiece(pieces, QUEEN_LINE.name().toLowerCase(), new Queen());
        initBlackPiece(pieces, QUEEN_LINE.name().toLowerCase(), new Queen());
    }

    private void initKings(Map<Position, Piece> pieces) {
        initWhitePiece(pieces, KING_LINE.name().toLowerCase(), new King());
        initBlackPiece(pieces, KING_LINE.name().toLowerCase(), new King());
    }

    private void initPawns(Map<Position, Piece> pieces) {
        initWhitePawns(pieces);
        initBlackPawns(pieces);
    }

    private void initBlackPawns(Map<Position, Piece> pieces) {
        for (Column column : Column.values()) {
            pieces.put(Position.of(column.name() + BLACK_PAWN_ROW.value()), new Piece(Color.BLACK, new Pawn()));
        }
    }

    private void initWhitePawns(Map<Position, Piece> pieces) {
        for (Column column : Column.values()) {
            pieces.put(Position.of(column.name() + WHITE_PAWN_ROW.value()), new Piece(Color.WHITE, new Pawn()));
        }
    }

    private void initBlackPiece(Map<Position, Piece> pieces, String column, Type type) {
        pieces.put(Position.of(column + BLACK_PIECE_ROW.value()), new Piece(Color.BLACK, type));
    }

    private void initWhitePiece(Map<Position, Piece> pieces, String column, Type type) {
        pieces.put(Position.of(column + WHITE_PIECE_ROW.value()), new Piece(Color.WHITE, type));
    }
}
