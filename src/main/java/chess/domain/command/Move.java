package chess.domain.command;

import chess.domain.position.Position;

public class Move implements Command {
    private static final String BLANK = " ";

    private final Position from;
    private final Position to;

    public Move(String command) {
        String[] sourceTarget = command.split(BLANK);

        from = Position.from(sourceTarget[1]);
        to = Position.from(sourceTarget[2]);
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
        return from;
    }

    @Override
    public Position target() {
        return to;
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
