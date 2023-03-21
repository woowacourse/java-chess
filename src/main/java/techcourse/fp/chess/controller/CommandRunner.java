package techcourse.fp.chess.controller;

import techcourse.fp.chess.dto.CommandRequest;

@FunctionalInterface
public interface CommandRunner {

    void execute(CommandRequest commandRequest);
}
