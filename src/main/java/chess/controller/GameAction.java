package chess.controller;

import chess.domain.game.Command;

import java.util.List;

@FunctionalInterface
public interface GameAction {

    void execute(final Command command, final List<String> commands);
}
