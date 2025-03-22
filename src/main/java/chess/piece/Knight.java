package chess.piece;

import chess.Color;
import chess.Movement;
import chess.Position;

import java.util.List;
import java.util.Map;

public class Knight extends Piece{
    private static final List<List<Movement>> movement = List.of(
            List.of(Movement.DOWN_DOWN_LEFT),
            List.of(Movement.DOWN_DOWN_RIGHT),
            List.of(Movement.LEFT_LEFT_DOWN),
            List.of(Movement.LEFT_LEFT_UP),
            List.of(Movement.UP_UP_LEFT),
            List.of(Movement.UP_UP_RIGHT),
            List.of(Movement.RIGHT_RIGHT_DOWN),
            List.of(Movement.RIGHT_RIGHT_UP)
    );

    public Knight(Color color, Position position) {
        super("N", color, position, new GMoving());
    }

    @Override
    public Piece move(Map<Position, Piece> board, Position positionToMove) {
        super.validate(board, positionToMove);
        return new Knight(getColor(), positionToMove);
    }

    @Override
    public List<List<Movement>> getMovement() {
        return movement;
    }
}
