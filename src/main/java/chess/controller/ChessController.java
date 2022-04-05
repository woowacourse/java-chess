package chess.controller;

import static chess.view.InputView.requestCommandInput;
import static chess.view.OutputView.printChessGameStart;
import static chess.view.OutputView.printCurrentChessBoard;
import static chess.view.OutputView.printExceptionMessage;
import static chess.view.OutputView.printStatus;

import chess.domain.ChessBoard;
import chess.domain.Command;
import chess.domain.Team;
import chess.domain.state.GameState;
import chess.domain.state.Ready;
import chess.dto.ChessBoardDto;
import chess.dto.ChessStatusDto;
import java.util.List;

public class ChessController {

    public void run() {
        printChessGameStart();
        GameState gameState = new Ready(ChessBoard.create());
        while (!gameState.isEnd()) {
            gameState = executeCommand(gameState);
            gameState = refresh(gameState);
        }
    }

    private GameState executeCommand(GameState gameState) {
        try {
            List<String> input = requestCommandInput();
            Command command = Command.of(input);
            gameState = gameState.execute(command, input);
            displayCommandResult(command, gameState);
            return gameState;
        } catch (IllegalArgumentException exception) {
            printExceptionMessage(exception.getMessage());
            return executeCommand(gameState);
        }
    }

    private void displayCommandResult(Command command, GameState gameState) {
        if (command.isEnd()) {
            return;
        }
        printCurrentChessBoard(ChessBoardDto.of(gameState.getChessBoard()));
        if (command.isStatus() || gameState.isFinished()) {
            displayStatus(gameState);
        }
    }

    private void displayStatus(GameState gameState) {
        ChessBoard chessBoard = gameState.getChessBoard();
        Team winner = gameState.findWinner();
        printStatus(ChessStatusDto.of(chessBoard, winner));
    }

    private GameState refresh(GameState gameState) {
        if (gameState.isFinished()) {
            return new Ready(ChessBoard.create());
        }
        return gameState;
    }
}
