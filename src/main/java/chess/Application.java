package chess;

import java.util.Map;

import chess.domain.ChessGame;
import chess.domain.Color;
import chess.domain.command.Command;
import chess.view.InputView;
import chess.view.OutputView;

public class Application {

    public static void main(String[] args) {
        OutputView.printGameStartMessage();
        Command command = getFirstCommand();
        if (command == Command.START) {
            startGame();
        }
    }

    private static Command getFirstCommand() {
        try {
            return Command.of(InputView.getFirstCommand());
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return getFirstCommand();
        }
    }

    private static void startGame() {
        ChessGame game = new ChessGame();
        OutputView.printBoard(game.getBoard());
        while (game.isRunning()) {
            OutputView.printPlayingCommandMessage();
            String commandValue = InputView.getCommand();
            Command command = Command.of(commandValue);
            if (command == Command.MOVE) {
                playTurn(commandValue, game);
            }
            if (command == Command.STATUS) {
                showStatus(game);
            }
        }
    }

    private static void playTurn(String moveCommand, ChessGame game) {
        try {
            String[] commands = moveCommand.split(" ");
            game.movePiece(commands[1], commands[2]);
            OutputView.printBoard(game.getBoard());
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
        }
    }

    private static void showStatus(ChessGame game) {
        Map<Color, Double> scores = game.calculateScore();
        Color winningColor = Color.NONE;
        double blackScore = scores.get(Color.BLACK);
        double whiteScore = scores.get(Color.WHITE);
        if (blackScore > whiteScore) {
            winningColor = Color.BLACK;
        }
        if (blackScore < whiteScore) {
            winningColor = Color.WHITE;
        }
        OutputView.printStatus(scores, winningColor);
    }
}
