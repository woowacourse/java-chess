package chess.command;

import chess.domain.board.Position;

public class Move implements Command {
    private final Position sourcePosition;
    private final Position targetPosition;

    protected Move(Position sourcePosition, Position targetPosition) {
        this.sourcePosition = sourcePosition;
        this.targetPosition = targetPosition;
    }

    public Position getSourcePosition() {
        return sourcePosition;
    }

    public Position getTargetPosition() {
        return targetPosition;
    }

    @Override
    public boolean isStart() {
        return false;
    }

    @Override
    public boolean isMove() {
        return true;
    }

    @Override
    public boolean isEnd() {
        return false;
    }

    @Override
    public boolean isStatus() {
        return false;
    }
}
