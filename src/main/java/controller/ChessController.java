package controller;

import domain.CommandRequest;
import service.ChessService;
import view.InputView;
import view.OutputView;

public class ChessController {
    private final ChessService chessService;

    public ChessController() {
        chessService = new ChessService();
    }

    public void run() {
        OutputView.printChessInfo();

        play();
    }

    private void play() {
        try {
            playEachTurn();
        } catch (IllegalArgumentException | IllegalStateException e) {
            OutputView.printErrorMessage(e);
            play();
        }
    }

    private void playEachTurn() {
        while (true) {
            CommandRequest commandRequest = new CommandRequest(InputView.requestCommand());
            chessService.execute(commandRequest);
            if (!chessService.isOngoing()) {
                break;
            }
            OutputView.printChessBoard(chessService.getChessBoard());
        }
    }
}

