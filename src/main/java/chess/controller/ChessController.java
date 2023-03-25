package chess.controller;

import chess.controller.converter.BoardConverter;
import chess.domain.ChessGame;
import chess.dto.CommandRequest;
import chess.dto.GameResultResponse;
import chess.view.OutputView;

public class ChessController {

    private ChessGame chessGame;

    public ChessController() {
        this.chessGame = new ChessGame();
    }

    public AppStatus start(CommandRequest commandRequest) {
        chessGame.start(commandRequest);
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
        chessGame = new ChessGame();
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
