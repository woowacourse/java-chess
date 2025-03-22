package chess.model.piece.types;

import chess.model.ChessBoard;
import chess.model.element.Color;
import chess.model.piece.Piece;
import chess.model.position.Position;

public class King extends Piece {

    public King(Color color) {
        super(color);
    }

    @Override
    protected boolean isNotWithInDirection(Position src, Position dest) {
        return true;
    }

    @Override
    protected boolean isWithInRangeByMovement(double distance) {
        if (distance == 1) {
            return true;
        }
        if (distance == Math.sqrt(2)) {
            return true;
        }
        return false;
    }

    @Override
    protected boolean passFilter(Position src, Position dest, ChessBoard board) {
        return true;
    }
}
