package chess.piece;

import chess.board.Position;
import java.util.Collections;
import java.util.List;

public class Knight extends Piece {

    public Knight(final Position position, final Side side) {
        super(position, side);
    }

    @Override
    public boolean isMovable(Position targetPosition) {
        final double slope = position.getSlope(targetPosition);

        return slope == 0.5 || slope == 2.0;
    }

    @Override
    public Knight move(final Position positionToMove) {
        return new Knight(positionToMove, this.side);
    }

    @Override
    public List<Position> getPaths(final Position targetPosition) {
        /*
        Knight는 이동 경로에 존재하는 말을 뛰어 넘을 수 있기 때문에 도착 지점에 아군 말이 존재하는지만 검사한다.
        그러므로, 빈 리스트를 반환함으로써 Knight가 이동할 땐 경로에 말이 존재하는지 체크하지 않는다.
         */
        return Collections.emptyList();
    }
}
