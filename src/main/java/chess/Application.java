package chess;

import chess.domain.state.BoardInitialize;
import chess.domain.state.GameState;
import chess.domain.state.WhiteTurn;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.List;

public class Application {
    public static void main(String[] args) {
        OutputView.printStartMessage();
        List<String> input = InputView.requestCommand();
        GameState gameState = new WhiteTurn(BoardInitialize.create());
        Command command = Command.of(input.get(0));
        if (command.isStart()) {
            startGame(gameState);
        }
    }

    private static void startGame(GameState gameState) {
        OutputView.printChessBoard(gameState.getBoard());
        while (!gameState.isFinished()) {
            List<String> input = InputView.requestCommand();
            Command command = Command.of(input.get(0));
            if (command == Command.STATUS) {
                OutputView.printStatus(gameState.getTeamScore(), gameState.getTeamScore());
                continue;
            }
            gameState = gameState.move(input.get(1), input.get(2));
            OutputView.printChessBoard(gameState.getBoard());
        }
        OutputView.printFinishedGame(gameState);
    }
}

