package chess.piece;

import chess.Position;
import java.util.List;

public abstract class Piece {

    public abstract List<Position> calculateCanMovePosition(Position departure, Position arrival);
}
