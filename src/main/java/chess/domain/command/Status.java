package chess.domain.command;

import chess.domain.position.Position;
import chess.exception.InvalidCommandException;

public class Status implements Command {
    @Override
    public boolean isStart() {
        return false;
    }

    @Override
    public boolean isMove() {
        return false;
    }

    @Override
    public Position source() {
        throw new InvalidCommandException();
    }

    @Override
    public Position target() {
        throw new InvalidCommandException();
    }

    @Override
    public boolean isEnd() {
        return false;
    }

    @Override
    public boolean isStatus() {
        return true;
    }
}
