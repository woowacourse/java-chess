package chess.controller;

import chess.controller.command.Command;

@FunctionalInterface
public interface Action {

    void act(final Command command);

}
