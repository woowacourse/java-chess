package chess.controller;

import chess.controller.converter.BoardConverter;
import chess.controller.status.AppStatus;
import chess.controller.util.InputExceptionHandler;
import chess.dao.boardpieces.BoardPiecesDao;
import chess.dao.boardstatuses.BoardStatusesDao;
import chess.dto.CommandRequest;
import chess.dto.GameResultResponse;
import chess.service.ChessBoardService;
import chess.service.ChessGameService;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessGameController {

    private static final InputExceptionHandler inputExceptionHandler = new InputExceptionHandler(
            OutputView::printInputErrorMessage);
    private final ChessGameService chessGameService;

    public ChessGameController(BoardPiecesDao boardPiecesDao, BoardStatusesDao boardStatusesDao) {
        this.chessGameService = new ChessGameService(new ChessBoardService(boardPiecesDao, boardStatusesDao));
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
