package chess.piece;

import chess.Color;
import chess.Movement;
import chess.Position;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Knight extends Piece {

    public Knight(Color color) {
        super(color);
    }

    @Override
    public boolean canMove(Position beforePosition, Position afterPosition) {
        List<Movement> movements = List.of(Movement.LEFT_LEFT_DOWN, Movement.LEFT_LEFT_UP, Movement.RIGHT_RIGHT_DOWN,
                Movement.RIGHT_RIGHT_UP, Movement.DOWN_DOWN_LEFT, Movement.DOWN_DOWN_RIGHT, Movement.UP_UP_LEFT,
                Movement.UP_UP_RIGHT);
        for (Movement movement : movements) {
            if (beforePosition.canMove(movement)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Movement> getMovements(Position beforePosition, Position afterPosition) {
        List<Movement> movements = new ArrayList<>();
        List<Movement> knightMovements = List.of(Movement.LEFT_LEFT_DOWN, Movement.LEFT_LEFT_UP,
                Movement.RIGHT_RIGHT_DOWN, Movement.RIGHT_RIGHT_UP, Movement.DOWN_DOWN_LEFT, Movement.DOWN_DOWN_RIGHT,
                Movement.UP_UP_LEFT, Movement.UP_UP_RIGHT);
        for (Movement movement : knightMovements) {
            if (beforePosition.canMove(movement)) {
                if (afterPosition.equals(beforePosition.moveSimul(movement))) {
                    movements.add(movement);
                }
            }
        }
        return movements;
    }

    @Override
    public boolean canMoveWithPieces(Map<Piece, Boolean> pieces) {
        if(pieces.isEmpty()){
            return true;
        }
        Entry<Piece, Boolean> e = pieces.entrySet().stream().findFirst().get();
        if (e.getValue() && e.getKey().getColor() == this.color) {
            return false;
        }
        return true;
    }
}
