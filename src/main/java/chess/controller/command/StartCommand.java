package chess.controller.command;

import chess.domain.game.ChessGame;
import chess.view.OutputView;

public final class StartCommand implements Command {

    private final OutputView outputView = new OutputView();

    @Override
    public void execute(ChessGame chessGame) {
        chessGame.startGame();
        outputView.printBoard(chessGame.getBoard());
    }
}
