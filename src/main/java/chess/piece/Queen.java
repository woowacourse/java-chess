package chess.piece;

import chess.Color;
import chess.Movement;
import chess.Position;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Queen extends Piece {

    public Queen(Color color) {
        super(color);
    }

    @Override
    public boolean canMove(Position beforePosition, Position afterPosition) {
        if (Math.abs(afterPosition.row().getNumber() - beforePosition.row().getNumber()) == Math.abs(
                afterPosition.column().getNumber() - beforePosition.column().getNumber())) {
            return true;
        }

        if (afterPosition.row().equals(beforePosition.row()) || afterPosition.column()
                .equals(beforePosition.column())) {
            return true;
        }
        return false;
    }

    @Override
    public List<Movement> getMovements(Position beforePosition, Position afterPosition) {
        int vertical = afterPosition.row().getNumber() - beforePosition.row().getNumber();
        int horizontal = afterPosition.column().getNumber() - beforePosition.column().getNumber();
        int count = Math.abs(vertical);

        List<Movement> movements = new ArrayList<>();
        if (vertical > 0 && horizontal > 0) {
            for (int i = 0; i < count; i++) {
                movements.add(Movement.RIGHT_UP);
            }
        }
        if (vertical > 0 && horizontal < 0) {
            for (int i = 0; i < count; i++) {
                movements.add(Movement.LEFT_UP);
            }

        }
        if (vertical < 0 && horizontal > 0) {
            for (int i = 0; i < count; i++) {
                movements.add(Movement.RIGHT_DOWN);
            }

        }
        if (vertical < 0 && horizontal < 0) {
            for (int i = 0; i < count; i++) {
                movements.add(Movement.LEFT_DOWN);
            }
        }
        if (vertical == 0 && horizontal < 0) {
            count = Math.abs(horizontal);
            for (int i = 0; i < count; i++) {
                movements.add(Movement.LEFT);
            }
        }
        if (vertical == 0 && horizontal > 0) {
            count = Math.abs(horizontal);
            for (int i = 0; i < count; i++) {
                movements.add(Movement.RIGHT);
            }
        }
        if (vertical > 0 && horizontal == 0) {
            count = Math.abs(vertical);
            for (int i = 0; i < count; i++) {
                movements.add(Movement.UP);
            }
        }
        if (vertical < 0 && horizontal == 0) {
            count = Math.abs(vertical);
            for (int i = 0; i < count; i++) {
                movements.add(Movement.DOWN);
            }
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
