package chess.controller.command;


import chess.domain.GameRoom;

public interface Command {
    void execute(final GameRoom gameRoom);
}
