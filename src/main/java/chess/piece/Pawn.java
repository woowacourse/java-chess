package chess.piece;

import chess.Color;
import chess.Movement;
import chess.Position;

import java.util.List;
import java.util.Map;

public class Pawn extends Piece {

    public static final List<List<Movement>> BLACK_MOVEMENT = List.of(
            List.of(Movement.DOWN),
            List.of(Movement.LEFT_DOWN),
            List.of(Movement.RIGHT_DOWN),
            List.of(Movement.DOWN, Movement.DOWN)
    );
    public static final List<List<Movement>> WHITE_MOVEMENT = List.of(
            List.of(Movement.UP),
            List.of(Movement.LEFT_UP),
            List.of(Movement.RIGHT_UP),
            List.of(Movement.UP, Movement.UP)
    );
    private final boolean moved;

    public Pawn(Color color, Position position, boolean moved) {
        super("P", color, position, new PMoving());
        this.moved = moved;
    }

    @Override
    public Piece move(Map<Position, Piece> board, Position positionToMove) {
        super.validate(board, positionToMove);
        return new Pawn(getColor(), positionToMove, true);
    }

    @Override
    public List<List<Movement>> getMovement() {
        if(getColor() == Color.BLACK) {
            if(moved) {
                return BLACK_MOVEMENT.subList(0, 3);
            }
            return BLACK_MOVEMENT;
        }
        if(moved) {
            return WHITE_MOVEMENT.subList(0, 3);
        }
        return WHITE_MOVEMENT;
    }
}
