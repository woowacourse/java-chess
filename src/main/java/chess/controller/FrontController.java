package chess.controller;

import chess.controller.status.AppStatus;
import chess.controller.util.InputExceptionHandler;
import chess.view.InputView;
import chess.view.OutputView;

public class FrontController {

    private static final ChessGameController chessGameController = new ChessGameController();
    private static final InputExceptionHandler inputExceptionHandler = new InputExceptionHandler(
            OutputView::printInputErrorMessage);
    private static AppStatus appStatus = AppStatus.RUNNING;

    private FrontController() {
    }

    public static void run() {
        OutputView.printGuideMessage();
        while (appStatus == AppStatus.RUNNING) {
            appStatus = inputExceptionHandler.retryExecuteIfInputIllegal(
                    InputView::requestGameCommand, chessGameController, CommandActionMapper::execute
            );
        }
    }

}
