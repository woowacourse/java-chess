package chess.controller;

import chess.controller.converter.BoardConverter;
import chess.controller.status.AppStatus;
import chess.controller.util.InputExceptionHandler;
import chess.dao.boardpieces.JdbcBoardPiecesDao;
import chess.dao.boardstatuses.JdbcBoardStatusesDao;
import chess.dto.CommandRequest;
import chess.dto.GameResultResponse;
import chess.service.ChessGameService;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessGameController {

    private static final InputExceptionHandler inputExceptionHandler = new InputExceptionHandler(
            OutputView::printInputErrorMessage);
    private final ChessGameService chessGameService;

    public ChessGameController() {
        this.chessGameService = new ChessGameService(new JdbcBoardPiecesDao(), new JdbcBoardStatusesDao());
    }

    public AppStatus start(CommandRequest commandRequest) {
        OutputView.printAvailableBoardIds(chessGameService.availableBoards());
        int boardId = inputExceptionHandler.retryRequestIfInputIllegal(InputView::requestBoardId);
        chessGameService.start(boardId);
        OutputView.printBoard(BoardConverter.convertToBoard(chessGameService.readBoard()));
        return AppStatus.RUNNING;
    }

    public AppStatus move(CommandRequest commandRequest) {
        chessGameService.move(commandRequest);
        OutputView.printBoard(BoardConverter.convertToBoard(chessGameService.readBoard()));
        if (chessGameService.isOver()) {
            OutputView.printGameOverMessage();
        }
        return AppStatus.RUNNING;
    }

    public AppStatus end(CommandRequest commandRequest) {
        chessGameService.end(commandRequest);
        OutputView.printGuideMessage();
        return AppStatus.RUNNING;
    }

    public AppStatus status(CommandRequest commandRequest) {
        GameResultResponse gameResult = chessGameService.computeResult(commandRequest);
        OutputView.printGameResult(gameResult);
        return AppStatus.RUNNING;
    }

    public AppStatus forceQuit(CommandRequest commandRequest) {
        return AppStatus.TO_EXIT;
    }

}
