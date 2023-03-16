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
    public boolean canMove(Position sourcePosition, Position targetPosition, Color color) {
        return isDiagonal(sourcePosition, targetPosition) && isNotMyPosition(sourcePosition, targetPosition)
                && getColor() != color;
    }

    @Override
    public List<Position> findPath(Position sourcePosition, Position targetPosition) {
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

    @Override
    public boolean isKing() {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public Piece move() {
        return new Bishop(getColor());
    }
}
