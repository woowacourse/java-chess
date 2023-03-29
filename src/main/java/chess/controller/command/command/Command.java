package chess.controller.command.command;

import chess.controller.ChessState;
import chess.dto.ChessGameDto;

@FunctionalInterface
public interface Command {

    ChessState execute(final ChessState state, final ChessGameDto chessGameDto);
}
