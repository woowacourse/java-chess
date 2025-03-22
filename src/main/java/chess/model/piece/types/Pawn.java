package chess.model.piece.types;

import chess.model.ChessBoard;
import chess.model.element.Color;
import chess.model.piece.Piece;
import chess.model.position.Position;

public class Pawn extends Piece {

    public Pawn(Color color) {
        super(color);
    }

    @Override
    protected boolean isNotWithInDirection(Position src, Position dest) {
        int[] srcNumber = findNumberByPosition(src);
        int[] destNumber = findNumberByPosition(dest);

        if (color.isBlack()) {
            if (destNumber[0] != srcNumber[0] - 1) { // 아래 방향 3개
                return false;
            }
        }
        if (destNumber[0] != srcNumber[0] + 1) { // 윗 방향 3개
            return false;
        }
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
