package chess.model.piece;

import chess.model.Position;
import chess.model.material.Color;
import chess.model.material.Type;

public class King extends Piece {

    private static final int MAX_MOVE_RANGE = 1;

    public King(Type type, Color color) {
        super(type, color);
    }

    @Override
    public boolean canMove(Position source, Position target) {
        int rowDifference = Math.abs(calculateRowDifference(source, target));
        int columnDifference = Math.abs(calculateColumnDifference(source, target));
        return rowDifference <= MAX_MOVE_RANGE && columnDifference <= MAX_MOVE_RANGE;
    }
}
