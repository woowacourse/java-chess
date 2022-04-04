package chess.controller;

import chess.domain.Score;
import chess.domain.Team;
import chess.domain.state.BoardInitialize;
import chess.domain.state.Finished;
import chess.domain.state.GameState;
import chess.domain.state.WhiteTurn;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.List;

public class ChessGameController {
    private static final int COMMAND_INDEX = 0;
    private static final int SOURCE_POSITION_INDEX = 1;
    private static final int DESTINATION_POSITION_INDEX = 2;

    public void run() {
        OutputView.printStartMessage();
        List<String> input = InputView.requestCommand();
        Command command = Command.of(input.get(COMMAND_INDEX));
        if (command.isStart()) {
            GameState gameState = new WhiteTurn(BoardInitialize.create());
            startGame(gameState);
        }
    }

    private void startGame(GameState gameState) {
        OutputView.printChessBoard(gameState.getBoard());
        while (gameState.isRunning()) {
            gameState = playCommand(gameState);
        }
        OutputView.printFinishedGame(gameState);
    }

    private GameState playCommand(GameState gameState) {
        try {
            List<String> input = InputView.requestCommand();
            Command command = Command.of(input.get(COMMAND_INDEX));
            gameState = executeCommand(gameState, input, command);
            return gameState;
        } catch (RuntimeException exception) {
            OutputView.errorMessage(exception.getMessage());
            return playCommand(gameState);
        }
    }

    private GameState executeCommand(GameState gameState, List<String> input, Command command) {
        if (command.isMove()) {
            gameState = pieceMove(gameState, input);
        }
        if (command.isStatus()) {
            printStatus(gameState);
        }
        if (command.isEnd()) {
            return new Finished(Team.NONE, gameState.getBoard());
        }
        return gameState;
    }

    private GameState pieceMove(GameState gameState, List<String> input) {
        gameState = gameState.move(input.get(SOURCE_POSITION_INDEX), input.get(DESTINATION_POSITION_INDEX));
        OutputView.printChessBoard(gameState.getBoard());
        return gameState;
    }

    private void printStatus(GameState gameState) {
        OutputView.printStatus(new Score(gameState.getBoard()));
    }
}
