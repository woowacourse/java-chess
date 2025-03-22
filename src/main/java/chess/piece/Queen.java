package chess.piece;

import java.util.Map;
import chess.Color;
import chess.Position;

public class Queen extends Piece {

    public Queen(Color color) {
        super(color);
    }

    @Override
    public boolean isAbleToMove(Position startPosition, Position endPosition, Map<Position, Piece> board) {
        return false;
    }
}
