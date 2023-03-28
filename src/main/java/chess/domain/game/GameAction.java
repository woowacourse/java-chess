package chess.domain.game;

import chess.controller.command.Command;

@FunctionalInterface
public interface GameAction {
    void execute(final ChessGame game, final Command command);
}
