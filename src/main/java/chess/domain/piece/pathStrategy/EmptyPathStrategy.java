package chess.domain.piece.pathStrategy;

import chess.domain.board.Position;
import chess.exception.PieceNotFoundException;

import java.util.Collections;
import java.util.List;

public class EmptyPathStrategy implements PathStrategy {
    @Override
    public void validateDistance(Position sourcePosition, Position targetPosition) {
        throw new PieceNotFoundException("움직일 수 있는 체스말이 없으므로 거리 확인이 불가능합니다.");
    }

    @Override
    public List<Position> createPath(Position sourcePosition, Position targetPosition) {
        return Collections.EMPTY_LIST;
    }
}
