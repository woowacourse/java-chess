package chess.dto;

import chess.model.Position;
import chess.vo.Command;

public interface Request {
    Command getCommand();

    Position getSource();

    Position getTarget();
}
