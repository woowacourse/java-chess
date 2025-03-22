package chess.piece;

import chess.Color;
import chess.Movement;
import chess.Position;

import java.util.List;
import java.util.Map;

public class Rook extends Piece {
    List<List<Movement>> movements = List.of(
            List.of(Movement.UP),
            List.of(Movement.DOWN),
            List.of(Movement.LEFT),
            List.of(Movement.RIGHT)
    );

    public Rook(Color color, Position position) {
        super("R", color, position, new JMoving());
    }

    @Override
    public Piece move(Map<Position, Piece> board, Position positionToMove) {
        super.validate(board, positionToMove);
        return new Rook(getColor(), positionToMove);
    }

    @Override
    public List<List<Movement>> getMovement() {
        return movements;
    }
}
