package chess.piece;

import chess.Color;
import chess.Movement;
import chess.Position;

import java.util.List;
import java.util.Map;

public class Bishop extends Piece {
    private static final List<List<Movement>> movement = List.of(
            List.of(Movement.LEFT_UP),
            List.of(Movement.LEFT_DOWN),
            List.of(Movement.RIGHT_DOWN),
            List.of(Movement.RIGHT_UP)
    );


    public Bishop(Color color, Position position) {
        super("B", color, position, new JMoving());
    }


    @Override
    public List<List<Movement>> getMovement() {
        return movement;
    }

    @Override
    public Piece move(Map<Position, Piece> board, Position positionToMove) {
        super.validate(board, positionToMove);
        return new Bishop(getColor(), positionToMove);
    }
}
