package chess.domain.square.piece.divided;

import chess.domain.position.Path;
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
    public boolean canArrive(Path path, Map<Position, Square> board) {
        final Square targetSquare = board.get(path.getTargetPosition());
        if (targetSquare.isColor(getColor())) {
            return false;
        }
        if (targetSquare == Empty.getInstance()) {
            return canMove(path) && isNotObstructed(path, board);
        }
        return canAttack(path) && isNotObstructed(path, board);
    }

    protected abstract boolean canAttack(final Path path);
}
