package chess.domain.piece.strategy;

import chess.domain.position.Direction;
import chess.domain.position.Position;
import java.util.List;

public class MovingInfo {

    private final Position source;
    private final Position target;

    public MovingInfo(Position source, Position target) {
        this.source = source;
        this.target = target;
    }

    public boolean isSameRankLength(int length) {
        return getRankLength() == length;
    }

    private int getRankLength() {
        return Math.abs(source.getRankIndex() - target.getRankIndex());
    }

    public boolean isSameFileLength(int length) {
        return getFileLength() == length;
    }

    private int getFileLength() {
        return Math.abs(source.getFileIndex() - target.getFileIndex());
    }

    public boolean isContainedDirection(List<Direction> directions) {
        return directions.contains(getDirection());
    }

    public Direction getDirection() {
        return Direction.of(source, target);
    }
}
