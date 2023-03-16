package chess.domain.piece;

import chess.domain.board.FileCoordinate;
import chess.domain.board.Position;
import chess.domain.board.RankCoordinate;
import java.util.ArrayList;
import java.util.List;

public class Rook extends Piece {

    private final boolean isMove;

    public Rook(Color color, boolean isMove) {
        super(color);
        this.isMove = isMove;
    }

    public Rook(Color color) {
        super(color);
        this.isMove = false;
    }

    @Override
    public boolean canMove(Position sourcePosition, Position targetPosition, Color color) {
        return isStraight(sourcePosition, targetPosition) && isNotMyPosition(sourcePosition, targetPosition)
                && getColor() != color;
    }

    @Override
    public List<Position> findPath(Position sourcePosition, Position targetPosition) {
        List<Position> paths = new ArrayList<>();
        int nowFileCoordinate = sourcePosition.getColumn();
        int nowRankCoordinate = sourcePosition.getRow();
        int targetFileCoordinate = targetPosition.getColumn();
        int targetRankCoordinate = targetPosition.getRow();
        if (nowFileCoordinate - targetFileCoordinate == 0) {
            int rowStep = getStep(nowRankCoordinate, targetRankCoordinate);

            while (nowRankCoordinate + rowStep != targetRankCoordinate) {
                nowRankCoordinate += rowStep;
                Position position = new Position(sourcePosition.getFileCoordinate(),
                        RankCoordinate.findBy(nowRankCoordinate));
                paths.add(position);
            }
            return paths;
        }
        int columnStep = getStep(nowFileCoordinate, targetFileCoordinate);

        while (nowFileCoordinate + columnStep != targetFileCoordinate) {
            nowFileCoordinate += columnStep;

            Position position = new Position(FileCoordinate.findBy(nowFileCoordinate),
                    sourcePosition.getRankCoordinate());
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
        return new Rook(getColor(), true);
    }
}
