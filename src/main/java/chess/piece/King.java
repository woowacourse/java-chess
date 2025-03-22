package chess.piece;

import chess.Color;
import chess.Movement;
import chess.Position;

import java.util.List;
import java.util.Map;

public class King extends Piece {
    private static final List<List<Movement>> movement = List.of(
            List.of(Movement.UP),
            List.of(Movement.DOWN),
            List.of(Movement.LEFT),
            List.of(Movement.RIGHT)
    );


    public King(Color color, Position position) {
        super("K", color, position, new GMoving());
    }

    @Override
    public Piece move(Map<Position, Piece> board, Position positionToMove) {
        super.validate(board, positionToMove);
        return new King(getColor(), positionToMove);
    }

    @Override
    public List<List<Movement>> getMovement() {
        return movement;
    }
}
