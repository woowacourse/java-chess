package chess.dto;

import chess.constant.Command;
import chess.domain.board.position.Position;

public class MoveRequest implements Request {

    private final Command command;
    private final Position source;
    private final Position target;

    public MoveRequest(Command command, Position source, Position target) {
        this.command = command;
        this.source = source;
        this.target = target;
    }

    @Override
    public Command getCommand() {
        return command;
    }

    @Override
    public Position getSource() {
        return source;
    }

    @Override
    public Position getTarget() {
        return target;
    }
}
