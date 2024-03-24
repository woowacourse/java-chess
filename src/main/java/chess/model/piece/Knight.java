package chess.model.piece;

import chess.model.material.Color;
import chess.model.material.Type;
import chess.model.position.Position;

public class Knight extends Piece {

    private static final int FIRST_MOVEMENT = 2;
    private static final int SECOND_MOVEMENT = 1;

    public Knight(Type type, Color color) {
        super(type, color);
    }

    @Override
    public boolean canMove(Position source, Position target) {
        int rowDifference = Math.abs(calculateRowDifference(source, target));
        int columnDifference = Math.abs(calculateColumnDifference(source, target));

        if (rowDifference == FIRST_MOVEMENT && columnDifference == SECOND_MOVEMENT) {
            return true;
        }
        return columnDifference == FIRST_MOVEMENT && rowDifference == SECOND_MOVEMENT;
    }
}
