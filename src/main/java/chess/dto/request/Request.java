package chess.dto.request;

import chess.constant.Command;
import chess.domain.board.position.Position;

public interface Request {
    Command getCommand();

    Position getSourcePosition();

    Position getTargetPosition();
}
