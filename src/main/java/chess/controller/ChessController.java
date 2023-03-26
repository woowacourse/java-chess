package chess.controller;

import chess.controller.converter.BoardConverter;
import chess.controller.util.InputExceptionHandler;
import chess.domain.ChessGame;
import chess.dto.CommandRequest;
import chess.dto.GameResultResponse;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessController {

    private static final InputExceptionHandler inputExceptionHandler = new InputExceptionHandler(
            OutputView::printInputErrorMessage);
    private final ChessGame chessGame;

    public ChessController() {
        this.chessGame = new ChessGame();
    }

    public void validateCommandRequest(CommandRequest commandRequest) {
        chessGame.validateCommand(commandRequest);
    }

    public AppStatus start(CommandRequest commandRequest) {
        OutputView.printAvailableBoardIds(chessGame.availableBoards());
        int boardId = inputExceptionHandler.retryRequestIfInputIllegal(InputView::requestBoardId);
        chessGame.start(boardId);
        OutputView.printBoard(BoardConverter.convertToBoard(chessGame.readBoard()));
        return AppStatus.RUNNING;
    }

    public AppStatus move(CommandRequest commandRequest) {
        chessGame.move(commandRequest);
        OutputView.printBoard(BoardConverter.convertToBoard(chessGame.readBoard()));
        if (chessGame.isOver()) {
            OutputView.printGameOverMessage();
        }
        return AppStatus.RUNNING;
    }

    public AppStatus end(CommandRequest commandRequest) {
        chessGame.end(commandRequest);
        OutputView.printGuideMessage();
        return AppStatus.RUNNING;
    }

    public AppStatus status(CommandRequest commandRequest) {
        GameResultResponse gameResult = chessGame.computeResult(commandRequest);
        OutputView.printGameResult(gameResult);
        return AppStatus.RUNNING;
    }

    public AppStatus forceQuit(CommandRequest commandRequest) {
        return AppStatus.TO_EXIT;
    }

}
