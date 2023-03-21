package chess.service;

import chess.controller.CommandRequest;

@FunctionalInterface
public interface CommandAction {

    void action(final CommandRequest commandRequest);
}
