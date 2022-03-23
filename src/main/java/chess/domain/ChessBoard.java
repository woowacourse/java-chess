package chess.domain;

import chess.domain.piece.EmptyPiece;
import chess.domain.piece.Piece;
import chess.domain.position.Column;
import chess.domain.position.Position;
import chess.domain.position.Row;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ChessBoard {

    private final Map<Position, Piece> pieces;

    public ChessBoard(Map<Position, Piece> pieces) {
        this.pieces = new HashMap<>(pieces);
        fillEmptyPieceIfAbsent();
    }

    private void fillEmptyPieceIfAbsent() {
        for (Column column : Column.values()) {
            fillEmptyPieceInColumn(column);
        }
    }

    private void fillEmptyPieceInColumn(Column column) {
        for (Row row : Row.values()) {
            this.pieces.computeIfAbsent(new Position(column, row), value -> EmptyPiece.getInstance());
        }
    }

    public Map<Position, Piece> getPieces() {
        return Collections.unmodifiableMap(pieces);
    }

    public Piece selectPiece(Position position) {
        return pieces.get(position);
    }

//    public Piece selectPiece(Position a1) {
//
//    }
}
