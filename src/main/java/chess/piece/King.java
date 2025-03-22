package chess.piece;

import java.util.Map;
import chess.Color;
import chess.Column;
import chess.Position;
import chess.Row;

public class King extends Piece {

    public King(Color color) {
        super(color);
    }

    @Override
    public boolean isAbleToMove(Position startPosition, Position endPosition, Map<Position, Piece> board) {
        if (Row.getDifference(startPosition.row(), endPosition.row()) > 1
                || Column.getDifference(startPosition.column(), endPosition.column()) > 1) {
            return false;
        }
        if (board.get(endPosition) == null) {
            return true;
        }
        if (board.get(endPosition).getColor() == super.getColor()) {
            return false;
        }

        return true;
    }
}
