package chess.domain.piece;

import chess.domain.board.FileCoordinate;
import chess.domain.board.Position;
import chess.domain.board.RankCoordinate;
import java.util.ArrayList;
import java.util.List;

public class Bishop extends Piece {

    public Bishop(Color color) {
        super(color);
    }

    @Override
    boolean canMove(Position sourcePosition, Position targetPosition) {
        return isDiagonal(sourcePosition, targetPosition) && isNotMyPosition(sourcePosition, targetPosition);
    }

    @Override
    List<Position> findPath(Position sourcePosition, Position targetPosition) {
        if (!canMove(sourcePosition, targetPosition)) {
            throw new IllegalArgumentException("이동할 수 없는 위치입니다.");
        }
        List<Position> paths = new ArrayList<>();
        int nowFileCoordinate = sourcePosition.getColumn();
        int nowRankCoordinate = sourcePosition.getRow();
        int targetFileCoordinate = targetPosition.getColumn();
        int targetRankCoordinate = targetPosition.getRow();
        int columnStep = getStep(nowFileCoordinate, targetFileCoordinate);
        int rowStep = getStep(nowRankCoordinate, targetRankCoordinate);

        while (nowFileCoordinate != targetFileCoordinate) {
            Position position = new Position(FileCoordinate.findBy(nowFileCoordinate),
                    RankCoordinate.findBy(nowRankCoordinate));
            nowFileCoordinate += columnStep;
            nowRankCoordinate += rowStep;
            paths.add(position);
        }
        return paths;
    }

    private int getStep(int nowFileCoordinate, int targetFileCoordinate) {
        if (nowFileCoordinate - targetFileCoordinate > 0) {
            return -1;
        }
        return 1;
    }

    @Override
    boolean isKing() {
        return false;
    }

    @Override
    Piece move() {
        return null;
    }
}
