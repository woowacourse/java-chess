package chess.domain.command;

import chess.domain.position.Position;

public class MoveCommand implements Command {

    private final Position from;
    private final Position to;

    public MoveCommand(Position from, Position to) {
        this.from = from;
        this.to = to;
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
    public boolean isStatus() {
        return false;
    }

    @Override
    public Position getFromPosition() {
        return from;
    }

    @Override
    public Position getToPosition() {
        return to;
    }
}
