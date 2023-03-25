package techcourse.fp.chess.controller;

import techcourse.fp.chess.domain.ChessGame;

@FunctionalInterface
public interface InitCommandRunner {

    InitCommandRunner end = () -> null;

    ChessGame execute();

}
