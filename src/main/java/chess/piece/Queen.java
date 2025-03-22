package chess.piece;

import chess.Color;
import chess.Movement;
import chess.Position;

import java.util.List;
import java.util.Map;

public class Queen extends Piece {
    private static final List<List<Movement>> movement = List.of(
            List.of(Movement.UP),
            List.of(Movement.DOWN),
            List.of(Movement.LEFT),
            List.of(Movement.RIGHT),
            List.of(Movement.LEFT_UP),
            List.of(Movement.LEFT_DOWN),
            List.of(Movement.RIGHT_DOWN),
            List.of(Movement.RIGHT_UP)
    );

    public Queen(Color color, Position position) {
        super("Q", color, position, new JMoving());
    }

    @Override
    public Piece move(Map<Position, Piece> board, Position positionToMove) {
        super.validate(board, positionToMove);
        return new Queen(getColor(), positionToMove);
    }

    @Override
    public List<List<Movement>> getMovement() {
        return movement;
    }
}
