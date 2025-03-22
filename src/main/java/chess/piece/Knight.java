package chess.piece;

import java.util.Map;
import chess.Color;
import chess.Column;
import chess.Position;
import chess.Row;

public class Knight extends Piece {

    public Knight(Color color) {
        super(color);
    }

    @Override
    public boolean isAbleToMove(Position startPosition, Position endPosition, Map<Position, Piece> board) {
        int rowDiff = Row.getDifference(startPosition.row(), endPosition.row());
        int colDiff = Column.getDifference(startPosition.column(), endPosition.column());

        if (board.get(endPosition) != null && board.get(endPosition).getColor() == super.getColor()) {
            return false;
        }
        if (rowDiff == 2 && colDiff == 1) {
            return true;
        }
        if (rowDiff == 1 && colDiff == 2) {
            return true;
        }
        return false;
    }
}
