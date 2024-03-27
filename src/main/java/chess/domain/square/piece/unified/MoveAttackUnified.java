package chess.domain.square.piece.unified;

import chess.domain.position.PathFinder;
import chess.domain.position.Position;
import chess.domain.square.Square;
import chess.domain.square.piece.Color;
import chess.domain.square.piece.Piece;
import java.util.Map;

public abstract class MoveAttackUnified extends Piece {

    public MoveAttackUnified(final Color color) {
        super(color);
    }

    @Override
    public final boolean canArrive(PathFinder pathFinder, Map<Position, Square> board) {
        final Square targetSquare = board.get(pathFinder.targetPosition());
        if (targetSquare.isColor(getColor())) {
            return false;
        }
        return canMove(pathFinder) && isNotObstructed(pathFinder, board);
    }
}
