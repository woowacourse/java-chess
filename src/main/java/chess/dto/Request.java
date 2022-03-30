package chess.dto;

import chess.constant.Command;
import chess.domain.board.position.Position;

public interface Request {
    Command getCommand();

    Position getSource();

    Position getTarget();
}
