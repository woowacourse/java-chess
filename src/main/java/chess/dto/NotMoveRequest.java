package chess.dto;

import chess.constant.Command;
import chess.domain.board.position.Position;

public class NotMoveRequest implements Request {

    private final Command command;

    public NotMoveRequest(Command command) {
        this.command = command;
    }

    @Override
    public Command getCommand() {
        return command;
    }

    @Override
    public Position getSourcePosition() {
        throw new UnsupportedOperationException("[ERROR] 지원하지 않는 기능입니다.");
    }

    @Override
    public Position getTargetPosition() {
        throw new UnsupportedOperationException("[ERROR] 지원하지 않는 기능입니다.");
    }
}
