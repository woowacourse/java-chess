package chess.domain.board;

import chess.domain.Color;
import chess.domain.piece.*;
import chess.domain.piece.role.Bishop;
import chess.domain.piece.role.King;
import chess.domain.piece.role.Knight;
import chess.domain.piece.role.Pawn;
import chess.domain.piece.role.Queen;
import chess.domain.piece.role.Role;
import chess.domain.piece.role.Rook;
import chess.domain.position.Column;
import chess.domain.position.Position;

import chess.domain.position.Row;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class RegularRuleSetup implements PiecesSetup {

    public Map<Position, Piece> initialize() {
        final Map<Position, Piece> pieces = new HashMap<>();
        initPieces(pieces);
        return pieces;
    }

    private void initPieces(final Map<Position, Piece> pieces) {
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

    private void initWhitePiece(Map<Position, Piece> pieces, Column column, Role role) {
        pieces.put(Position.valueOf(column, Row.ONE), new Piece(Color.WHITE, role));
    }

    private void initBlackPiece(Map<Position, Piece> pieces, Column column, Role role) {
        pieces.put(Position.valueOf(column, Row.EIGHT), new Piece(Color.BLACK, role));
    }
}
