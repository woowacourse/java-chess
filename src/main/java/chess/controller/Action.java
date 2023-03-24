package chess.controller;

import chess.controller.dto.CommandDto;

@FunctionalInterface
public interface Action {

    CommandDto act(final CommandDto commandDto);
}
