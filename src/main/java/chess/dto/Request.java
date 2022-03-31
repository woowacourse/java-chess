package chess.dto;

import java.util.Objects;

import chess.model.Position;
import chess.vo.Command;

public class Request {

    private static final String ERROR_NOT_MOVE_ILLEGAL = "[ERROR] MOVE 커맨드가 아니면 사용할 수 없습니다.";

    private final Command command;
    private final Position source;
    private final Position target;

    public Request(Command command, Position source, Position target) {
        this.command = command;
        this.source = source;
        this.target = target;
    }

    public Request(Command command) {
        this.command = command;
        this.source = null;
        this.target = null;
    }

    public Command getCommand() {
        return command;
    }

    public Position getSource() {
        if (Objects.isNull(source)) {
            throw new IllegalStateException(ERROR_NOT_MOVE_ILLEGAL);
        }
        return source;
    }

    public Position getTarget() {
        if (Objects.isNull(target)) {
            throw new IllegalStateException(ERROR_NOT_MOVE_ILLEGAL);
        }
        return target;
    }
}
