package techcourse.fp.chess.controller;

import techcourse.fp.chess.domain.ChessGame;
import techcourse.fp.chess.dto.request.CommandRequest;

@FunctionalInterface
public interface PlayingCommandRunner {

    PlayingCommandRunner end = (commandRequest, board) -> {};

    void execute(CommandRequest commandRequest, ChessGame chessGame);
}
