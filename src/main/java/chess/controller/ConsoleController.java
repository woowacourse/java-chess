package chess.controller;

import chess.consoleview.InputView;
import chess.consoleview.OutputView;
import chess.domain.game.ChessGame;
import chess.domain.position.Position;
import chess.dto.request.ConsoleCommandDto;
import chess.dto.response.BoardDto;
import chess.dto.response.PieceColorDto;
import chess.dto.response.ScoreResultDto;

public class ConsoleController {

    public void run() {
        ChessGame chessGame = ChessGame.create();

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

        printCurrentTurn(chessGame);
        OutputView.printBoard(BoardDto.from(chessGame.getBoard()));
    }

    private void executeMove(ChessGame chessGame, ConsoleCommandDto commandDto) {
        Position fromPosition = Position.from(commandDto.getArgumentByIndex(0));
        Position toPosition = Position.from(commandDto.getArgumentByIndex(1));
        chessGame.movePiece(fromPosition, toPosition);

        printCurrentTurn(chessGame);
        OutputView.printBoard(BoardDto.from(chessGame.getBoard()));
    }

    private void printCurrentTurn(ChessGame chessGame) {
        OutputView.printCurrentTurn(PieceColorDto.from(chessGame));
    }

    private void executeStatus(ChessGame chessGame) {
        OutputView.printScore(ScoreResultDto.from(chessGame));
    }
}
