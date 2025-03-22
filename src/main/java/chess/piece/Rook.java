package chess.piece;

import chess.Color;
import chess.Movement;
import chess.Position;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Rook extends Piece {

    public Rook(Color color) {
        super(color);
    }

    @Override
    public boolean canMove(Position beforePosition, Position afterPosition) {

        return afterPosition.row().equals(beforePosition.row()) || afterPosition.column()
                .equals(beforePosition.column());
    }

    @Override
    public List<Movement> getMovements(Position beforePosition, Position afterPosition) {
        List<Movement> movements = new ArrayList<>();
        int count;
        if (afterPosition.row().equals(beforePosition.row())) {
            count = Math.abs(afterPosition.column().getNumber() - beforePosition.column().getNumber());
            if (afterPosition.column().getNumber() > beforePosition.column().getNumber()) {
                count = afterPosition.column().getNumber() - beforePosition.column().getNumber();
                for (int i = 0; i < count; i++) {
                    movements.add(Movement.RIGHT);
                }
                return movements;
            }
            for (int i = 0; i < count; i++) {
                movements.add(Movement.LEFT);
            }
        }
        count = afterPosition.row().getNumber() - beforePosition.row().getNumber();
        if (afterPosition.row().getNumber() > beforePosition.row().getNumber()) {
            for (int i = 0; i < count; i++) {
                movements.add(Movement.UP);
            }
            return movements;
        }
        for (int i = 0; i < count; i++) {
            movements.add(Movement.DOWN);
        }
        return movements;
    }

    @Override
    public boolean canMoveWithPieces(Map<Piece, Boolean> pieces) {
        if (pieces.size() == 1) {
            Entry<Piece, Boolean> e = pieces.entrySet().stream().findFirst().get();
            if (e.getValue() && e.getKey().getColor() != this.getColor()) {
                return true;
            }
            return false;
        }
        return false;
    }
}
