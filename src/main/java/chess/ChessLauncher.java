package chess;

import chess.command.Command;
import chess.command.CommandFactory;
import chess.domain.ChessGame;
import chess.domain.board.Board;
import chess.domain.board.strategy.CreateCompleteBoardStrategy;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessLauncher {

    public void run() {
        OutputView.printStartMessage();
        ChessGame chessGame = new ChessGame(new Board(new CreateCompleteBoardStrategy()));
        while (!chessGame.isFinished()) {
            proceed(chessGame);
        }
    }

    private void proceed(final ChessGame chessGame) {
        try {
            Command command = CommandFactory.find(InputView.input());
            command.execute(chessGame);
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            proceed(chessGame);
        }
    }
}
