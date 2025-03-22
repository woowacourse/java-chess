package chess.piece;

import chess.Color;
import chess.Position;
import java.util.List;

public class Pawn extends Piece{

    public Pawn(Color color) {
        super(color);
    }

    @Override
    public List<Position> calculateCanMovePosition(Position departure, Position arrival) {
        return List.of();
    }

    @Override
    public String toString() {
        if (getColor().isBlack()) {
            return "A";
        }
        return "a";
    }
}
