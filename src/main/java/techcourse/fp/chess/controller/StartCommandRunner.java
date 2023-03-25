package techcourse.fp.chess.controller;

import techcourse.fp.chess.domain.ChessGame;

@FunctionalInterface
public interface StartCommandRunner {

    StartCommandRunner end = () -> null;

    ChessGame execute();

}
