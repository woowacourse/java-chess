package techcourse.fp.chess.controller;

import techcourse.fp.chess.domain.Board;

@FunctionalInterface
public interface StartCommandRunner {

    StartCommandRunner end = () -> null;

    Board execute();

}
