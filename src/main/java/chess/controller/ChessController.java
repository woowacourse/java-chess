package chess.controller;

import chess.domain.game.ChessGame;
import chess.domain.game.score.ScoreResult;
import chess.domain.position.Position;
import chess.view.InputView;
import chess.view.OutputView;
import chess.view.dto.BoardDto;
import chess.view.dto.ConsoleCommandDto;

public class ChessController {

    public void run() {
        ChessGame chessGame = new ChessGame();

        OutputView.printCommandGuide();

        requestCommand(chessGame);
    }

    private void requestCommand(ChessGame chessGame) {
        try {
            ConsoleCommandDto commandDto = InputView.inputCommand();

            if (commandDto.isEnd()) {
                return;
            }

            executeCommandIfNotEnd(chessGame, commandDto);
        } catch (IllegalStateException | IllegalArgumentException e) {
            OutputView.printException(e);
            requestCommand(chessGame);
        }
    }

    private void executeCommandIfNotEnd(ChessGame chessGame, ConsoleCommandDto commandDto) {
        if (commandDto.isStart()) {
            executeStart(chessGame);
        }
        if (commandDto.isMove()) {
            executeMove(chessGame, commandDto);
        }
        if (commandDto.isStatus()) {
            executeStatus(chessGame);
        }
        requestCommand(chessGame);
    }

    private void executeStart(ChessGame chessGame) {
        chessGame.startGame();

        OutputView.printBoard(BoardDto.from(chessGame.getBoard()));
    }

    private void executeMove(ChessGame chessGame, ConsoleCommandDto commandDto) {
        Position fromPosition = Position.from(commandDto.getArgumentByIndex(0));
        Position toPosition = Position.from(commandDto.getArgumentByIndex(1));
        chessGame.movePiece(fromPosition, toPosition);

        OutputView.printBoard(BoardDto.from(chessGame.getBoard()));
    }

    private void executeStatus(ChessGame chessGame) {
        ScoreResult scoreResult = chessGame.showStatus();

        OutputView.printScore(scoreResult);
    }
}
