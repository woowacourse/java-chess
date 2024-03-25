package chess.domain.square.piece.divided;

import chess.domain.position.PathFinder;
import chess.domain.position.Position;
import chess.domain.square.Empty;
import chess.domain.square.Square;
import chess.domain.square.piece.Color;
import chess.domain.square.piece.Piece;
import java.util.Map;

public abstract class DividedArriveWay extends Piece {

    public DividedArriveWay(final Color color) {
        super(color);
    }

    @Override
    public boolean canArrive(PathFinder pathFinder, Map<Position, Square> board) {
        final Square targetSquare = board.get(pathFinder.targetPosition());
        if (targetSquare.isColor(getColor())) {
            return false;
        }
        if (targetSquare == Empty.getInstance()) {
            return canMove(pathFinder) && isNotObstructed(pathFinder, board);
        }
        return canAttack(pathFinder) && isNotObstructed(pathFinder, board);
    }

    protected abstract boolean canAttack(final PathFinder pathFinder);
}
