package chess.domain;

import chess.domain.direction.Direction;

import java.util.List;

public class MoveRule {
    private final List<Direction> possibleDirections;
    private final int limitMoveCount;

    public MoveRule(List<Direction> possibleDirections, int limitMoveCount) {
        this.possibleDirections = possibleDirections;
        this.limitMoveCount = limitMoveCount;
    }

    public void judge(Navigator navigator) {
        if (!(possibleDirections.contains(navigator.getDirection()))
                || (limitMoveCount < navigator.getMoveCount())) {
            throw new IllegalArgumentException("해당 말의 행마법에 어긋나는 움직임입니다.");
        }
    }
}
