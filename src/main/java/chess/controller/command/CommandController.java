package chess.controller.command;

import chess.domain.Chess;

public interface CommandController {
    Chess execute(Chess chess);
}
