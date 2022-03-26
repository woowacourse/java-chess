package chess.dto;

import chess.vo.Command;
import chess.vo.Position;

public interface Request {
    Command getCommand();

    Position getSource();

    Position getTarget();
}
