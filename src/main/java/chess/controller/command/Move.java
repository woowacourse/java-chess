package chess.controller.command;

import chess.domain.position.Position;

public class Move implements Command {
    private final Position from;
    private final Position to;

    public Move(String command) {
        String[] sourceTarget = command.split(" ");

        from = Position.of(sourceTarget[1]);
        to = Position.of(sourceTarget[2]);
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
