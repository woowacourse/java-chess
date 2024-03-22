package domain.strategy;

import domain.board.Board;
import domain.board.Position;
import domain.piece.info.Direction;
import java.util.List;

public interface MoveStrategy {

    /*
    도착 지점에 아군 말이 있으면 안된다.
    각 말이 가진 움직일 수 있는 패턴이 있고, 그 패턴에 위배되는 요청이 올 경우에는 움직일 수 없다.
    특수한 경우를 제외하고 움직이는 중에 말이 존재하면 안된다.

     */
    List<Position> movablePositions(final Position source, final List<Direction> directions, final Board board);
}
