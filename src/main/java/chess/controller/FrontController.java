package chess.controller;

import chess.controller.util.InputExceptionHandler;
import chess.view.InputView;
import chess.view.OutputView;

public class FrontController {

    private static final ChessController chessController = new ChessController();
    private static final InputExceptionHandler inputExceptionHandler = new InputExceptionHandler(
            OutputView::printInputErrorMessage);
    private static AppStatus appStatus = AppStatus.RUNNING;

    private FrontController() {
    }

    public static void run() {
        // TODO 게임방 목록 조회해서 게임방 아이디 입력받기
        OutputView.printGuideMessage();
        while (appStatus == AppStatus.RUNNING) {
            appStatus = inputExceptionHandler.retryExecuteIfInputIllegal(
                    InputView::requestGameCommand, chessController, CommandActionMapper::execute
            );
        }
    }

}
