package chess.domain.piece;

import chess.domain.board.BoardSituation;
import chess.domain.position.Position;

import java.util.List;

public interface MoveStrategy {

    List<Position> getMovablePositionsWithoutObstacles(Position source);

    List<Position> getMovablePositions(Position source, BoardSituation boardSituation);

}
