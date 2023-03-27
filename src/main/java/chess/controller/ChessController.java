package chess.controller;

import chess.controller.command.Command;
import chess.controller.command.CommandFactory;
import chess.dao.HistoryDao;
import chess.domain.ChessGame;
import chess.domain.square.Team;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessController {
    private final InputView inputView;
    private final OutputView outputView;
    private final ChessGame chessGame;

    public ChessController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.chessGame = new ChessGame(new HistoryDao());
    }

    public void run() {
        showStartMessage();

        while (!chessGame.isEnd()) {
            repeatIfExceptionOccur(this::play);
        }
        Team winnerTeam = chessGame.getWinner();
        outputView.showWinner(winnerTeam);
    }

    private void repeatIfExceptionOccur(Runnable runnable) {
        try {
            runnable.run();
        } catch (Exception e) {
            outputView.printExceptionMessage(e.getMessage());
            repeatIfExceptionOccur(runnable);
        }
    }

    private void play() {
        String inputCommand = inputView.readCommand();
        Command command = CommandFactory.from(inputCommand);
        command.execute(chessGame, outputView);
    }

    private void showStartMessage() {
        outputView.printStartMessage();
    }
}
