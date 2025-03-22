package chess.piece;

import chess.Color;
import chess.Movement;
import chess.Position;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class King extends Piece {

    public King(Color color) {
        super(color);
    }

    @Override
    public boolean canMove(Position beforePosition, Position afterPosition) {
        List<Movement> movements = List.of(Movement.LEFT, Movement.UP, Movement.RIGHT, Movement.DOWN,
                Movement.LEFT_DOWN);
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
        List<Movement> kingMovements = List.of(Movement.LEFT, Movement.UP, Movement.RIGHT, Movement.DOWN,
                Movement.LEFT_DOWN);
        for (Movement movement : kingMovements) {
            if (beforePosition.canMove(movement)) {
                if (afterPosition.equals(beforePosition.moveSimul(movement))) {
                    movements.add(movement);
                }
            }
        }
        return movements;
    }

    @Override
    public boolean canMoveWithPieces(Map<Piece,Boolean> pieces) {

        if(pieces.size()==1){
            return pieces.entrySet().stream().findFirst().get().getKey().getColor()==(this.color);
        }
        return pieces.isEmpty();
    }
}
