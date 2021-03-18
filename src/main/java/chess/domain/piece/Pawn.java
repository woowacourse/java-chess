package chess.domain.piece;

import chess.domain.Position;

import java.util.List;
import java.util.Map;

public class Pawn extends Piece {
    private final int direction;

    public Pawn(final int direction) {
        this.direction = direction;
    }

    @Override
    public boolean isMovable(Position current, Position destination, Map<Position, Piece> chessBoard){
        if (!checkPositionRule(current, destination)) {
            return false;
        }
        if (current.checkDiagonalRule(destination)) {
            return chessBoard.containsKey(destination);
        }
        if (current.checkStraightRule(destination)) {
            final List<Position> straightPath = current.generateStraightPath(destination);
            return checkEmptyPath(straightPath, chessBoard) && !chessBoard.containsKey(destination);
        }
        return false;
    }

    @Override
    public boolean checkPositionRule(final Position current, final Position destination){
        if (isMoved) {
            return checkPositionRuleAfterMove(current, destination);
        }
        return checkPositionRuleFirstMove(current, destination);
    }

    private boolean checkPositionRuleAfterMove(final Position current, final Position destination) {
        if (current.moveY(direction).equals(destination)) {
            return true;
        }
        return current.checkDiagonalToDirection(destination, direction);
    }

    private boolean checkPositionRuleFirstMove(final Position current, final Position destination) {
        if (current.moveY(direction).equals(destination) || current.moveY(direction * 2).equals(destination)) {
            return true;
        }
        return current.checkDiagonalToDirection(destination, direction);
    }
}
