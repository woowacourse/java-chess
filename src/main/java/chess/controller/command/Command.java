package chess.controller.command;

import chess.domain.ChessGame;
import chess.view.OutputView;

public interface Command {
    void execute(ChessGame chessGame, OutputView outputView);

    boolean isNotEndCommand();

    boolean isNotStartCommand();
}
