package chess.domain;

import chess.domain.piece.Piece;

import java.util.Map;

public class Board {

    public static final String ERROR_MOVE_NOT_AVAILABLE = "해당 위치로 기물을 이동할 수 없습니다.";
    private final Map<Position, Piece> values;

    public Board(Map<Position, Piece> values) {
        this.values = values;
    }

    public void move(final Position source, final Position target) {
        Piece sourcePiece = values.get(source);
        if (!sourcePiece.canMove(source, target, this)) {
            throw new IllegalArgumentException(ERROR_MOVE_NOT_AVAILABLE);
        }

        values.remove(source);
        values.put(target, sourcePiece);
    }

    public Map<Position, Piece> get() {
        return values;
    }

    public boolean isNotExistPiece(Position position) {
        return !values.containsKey(position);
    }
}
