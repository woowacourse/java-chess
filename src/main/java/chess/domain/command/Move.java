package chess.domain.command;

import chess.domain.position.Position;
import chess.exception.InvalidCommandException;

public class Move implements Command {
    private static final String SPACE = " ";

    private final Position source;
    private final Position target;

    public Move(String command) {
        String[] sourceTarget = command.split(SPACE);
        validateLength(sourceTarget);

        source = Position.from(sourceTarget[1]);
        target = Position.from(sourceTarget[2]);
    }

    private void validateLength(String[] sourceTarget) {
        if (sourceTarget.length != 3) {
            throw new InvalidCommandException();
        }
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
    public Position source() {
        return source;
    }

    @Override
    public Position target() {
        return target;
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
