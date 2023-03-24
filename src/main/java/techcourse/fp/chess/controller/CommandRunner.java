package techcourse.fp.chess.controller;

import techcourse.fp.chess.domain.Board;
import techcourse.fp.chess.dto.CommandRequest;

@FunctionalInterface
public interface CommandRunner {

    CommandRunner end = (commandRequest, board) -> {};

    void execute(CommandRequest commandRequest, Board board);
}
