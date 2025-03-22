package chess.piece;

import chess.Position;
import java.util.List;

public class Pawn extends Piece{

    @Override
    public List<Position> calculateCanMovePosition(Position departure, Position arrival) {
        return List.of();
    }
}
