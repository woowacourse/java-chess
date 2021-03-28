package chess.domain.command;

import chess.domain.Game;

public interface Command {
    Command run(final Game game, final String command);
    boolean isStartCommand();
    boolean isEndCommand();
    boolean isStatusCommand();
    boolean isMoveCommand();
}
