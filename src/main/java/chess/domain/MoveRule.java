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
}
