package chess.domain;

import chess.domain.piece.EmptyPiece;
import chess.domain.piece.Piece;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ChessBoard {

    private final Map<Position, Piece> pieces;

    public ChessBoard(Map<Position, Piece> pieces) {
        this.pieces = new HashMap<>(pieces);
        for (Column column : Column.values()) {
            for (Row row : Row.values()) {
                this.pieces.computeIfAbsent(new Position(column, row), value -> EmptyPiece.getInstance());
            }
        }
    }

    public Map<Position, Piece> getPieces() {
        return Collections.unmodifiableMap(pieces);
    }
}
