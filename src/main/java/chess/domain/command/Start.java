package chess.domain.command;

import chess.domain.position.Position;
import chess.exception.InvalidCommandException;

public final class Start implements Command {

    @Override
    public boolean isStart() {
        return true;
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
        return false;
    }
}
