package chess.piece;

import chess.ChessBoard;
import chess.Color;
import chess.Movement;
import chess.Position;
import java.util.Set;

public class Knight extends Piece {

    private static final Set<Movement> MOVEMENTS = Set.of(
            Movement.UP_UP_LEFT, Movement.UP_UP_RIGHT,
            Movement.LEFT_LEFT_DOWN, Movement.LEFT_LEFT_UP,
            Movement.RIGHT_RIGHT_DOWN, Movement.RIGHT_RIGHT_UP,
            Movement.DOWN_DOWN_LEFT, Movement.DOWN_DOWN_RIGHT
    );

    public Knight(Color color, Position position) {
        super(color, position);
    }

    @Override
    protected boolean isMovable(ChessBoard chessBoard, Position to) {
        for (Movement movement : MOVEMENTS) {
            Position curr = position;
            if (!curr.canMove(movement)) {
                continue;
            }
            curr = curr.move(movement);
            if (curr.equals(to)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        if (isColor(Color.BLACK)) {
            return "n";
        }
        return "N";
    }
}
