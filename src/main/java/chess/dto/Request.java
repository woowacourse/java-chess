package chess.dto;

import chess.Command;
import chess.Position;

public interface Request {
    Command getCommand();

    Position getSource();

    Position getTarget();
}
