package chess;

import java.util.Map;

import chess.domain.ChessGame;
import chess.domain.Color;
import chess.view.InputView;
import chess.view.OutputView;

public class Application {
    public static void main(String[] args) {
        OutputView outputView = new OutputView();

        outputView.printGameStartMessage();

        String startOrEndInput = InputView.getStartOrEndInput();
        if (startOrEndInput.equals("start")) {
            startGame(outputView);
        }
    }

    private static void startGame(OutputView outputView) {
        ChessGame game = new ChessGame();
        outputView.printBoard(game.getBoard());
        while (game.isRunning()) {
            play(outputView, game);
            outputView.printBoard(game.getBoard());
        }
    }

    private static void play(OutputView outputView, ChessGame game) {
        try {
            String command = InputView.getCommand();
            if (command.startsWith("move")) {
                playTurn(command, game);
            }
            if (command.startsWith("status")) {
                showStatus(outputView, game);
            }
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e.getMessage());
            play(outputView, game);
        }
    }

    private static void playTurn(String moveCommand, ChessGame game) {
        String[] commands = moveCommand.split(" ");
        game.movePiece(commands[1], commands[2]);
    }

    private static void showStatus(OutputView outputView, ChessGame game) {
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
        outputView.printStatus(scores, winningColor);
    }
}
