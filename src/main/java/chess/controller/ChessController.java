package chess.controller;

import static chess.view.InputView.inputCommand;
import static chess.view.OutputView.printBoard;
import static chess.view.OutputView.printException;
import static chess.view.OutputView.printGameEndMessage;
import static chess.view.OutputView.printGameStartMessage;

import chess.domain.game.ChessGame;
import chess.domain.result.BoardResult;
import chess.domain.result.ScoreResult;
import chess.domain.state.ResultType;
import chess.dto.PiecesDto;
import chess.view.OutputView;

public class ChessController {

    private final ChessGame chessGame;

    public ChessController() {
        this.chessGame = new ChessGame();
    }

    public void run() {
        printGameStartMessage();
        while (chessGame.isRunning()) {
            actWithCommand();
            boolean isSuccessful = actWithResponse();
            progress(isSuccessful);
        }
        printGameEndMessage();
    }

    private void actWithCommand() {
        try {
            runCommandIfNeeded();
        } catch (RuntimeException e) {
            printException(e.getMessage());
            actWithCommand();
        }
    }

    private void runCommandIfNeeded() {
        if (!chessGame.needsCommand()) {
            return;
        }
        chessGame.receiveCommand(inputCommand());
    }

    private boolean actWithResponse() {
        try {
            response();
            return true;
        } catch (RuntimeException e) {
            printException(e.getMessage());
            return false;
        }
    }

    private void response() {
        if (chessGame.supports(ResultType.BOARD)) {
            responseBoard();
        }
        if (chessGame.supports(ResultType.SCORE)) {
            responseScore();
        }
    }

    private void responseBoard() {
        BoardResult boardResult = (BoardResult) chessGame.bringResult();
        printBoard(PiecesDto.from(boardResult));
    }

    private void responseScore() {
        ScoreResult scoreResult = (ScoreResult) chessGame.bringResult();
        OutputView.printScore(scoreResult.getBlackScore(), scoreResult.getWhiteScore());
    }

    private void progress(boolean isSuccessful) {
        chessGame.progress(isSuccessful);
    }
}
