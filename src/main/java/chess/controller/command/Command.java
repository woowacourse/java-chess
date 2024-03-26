package chess.controller.command;

import chess.domain.game.ChessGame;
import chess.view.OutputView;

public interface Command {
    void execute(ChessGame game, OutputView outputView);

    boolean isStart();

    boolean isEnd();
}
