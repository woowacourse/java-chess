package chess.piece;

import chess.Color;
import chess.Movement;
import chess.Position;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Pawn extends Piece {

    public Pawn(Color color) {
        super(color);
    }

    @Override
    public boolean canMove(Position beforePosition, Position afterPosition) {
        int vertical = afterPosition.row().getNumber() - beforePosition.row().getNumber();
        int horizontal = afterPosition.column().getNumber() - beforePosition.column().getNumber();

        if (color == Color.WHITE) {
            if (vertical <= 0) {
                return false;
            }
            if (vertical >= 2 || Math.abs(horizontal) >= 2) {
                return false;
            }
        }
        if (color == Color.BLACK) {
            if (vertical >= 0) {
                return false;
            }
            if (Math.abs(vertical) >= 2 || Math.abs(horizontal) >= 2) {
                return false;
            }
        }
        return true;
    }

    @Override
    public List<Movement> getMovements(Position beforePosition, Position afterPosition) {
        List<Movement> movements = new ArrayList<>();
        int horizontal = afterPosition.column().getNumber() - beforePosition.column().getNumber();
        if (color == Color.WHITE) {
            if (horizontal > 0) {
                movements.add(Movement.RIGHT_UP);
            }
            if (horizontal < 0) {
                movements.add(Movement.LEFT_UP);
            }
            movements.add(Movement.UP);
            return movements;
        }
        if (color == Color.BLACK) {
            if (horizontal > 0) {
                movements.add(Movement.RIGHT_DOWN);
            }
            if (horizontal < 0) {
                movements.add(Movement.LEFT_DOWN);
            }
            movements.add(Movement.DOWN);
            return movements;
        }
        return movements;
    }

    @Override
    public boolean canMoveWithPieces(Map<Piece, Boolean> pieces) {
        if (pieces.isEmpty()) {

            return true;
        }
        Entry<Piece, Boolean> e = pieces.entrySet().stream().findFirst().get();
        if (pieces.size() == 1) {
            if (e.getValue()) {
                return e.getKey().getColor() != this.color;
            }
        }
        return false;
    }
}
