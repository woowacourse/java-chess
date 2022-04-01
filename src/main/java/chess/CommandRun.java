package chess;

import chess.controller.ChessController;

@FunctionalInterface
public interface CommandRun<T, U> {

    void execute(T t, U u);

}
