package chess;

import java.util.Map;

import chess.domain.ChessGame;
import chess.domain.Color;
import chess.view.InputView;
import chess.view.OutputView;

public class Application {

    public static void main(String[] args) {
        OutputView.printGameStartMessage();
        String startOrEndInput = InputView.getStartOrEndInput();
        if (startOrEndInput.equals("start")) {
            startGame();
        }
    }

    private static void startGame() {
        ChessGame game = new ChessGame();
        OutputView.printBoard(game.getBoard());
        while (game.isRunning()) {
            play(game);
            OutputView.printBoard(game.getBoard());
        }
    }

    private static void play(ChessGame game) {
        try {
            String command = InputView.getCommand();
            if (command.startsWith("move")) {
                playTurn(command, game);
            }
            if (command.startsWith("status")) {
                showStatus(game);
            }
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            play(game);
        }
    }

    private static void playTurn(String moveCommand, ChessGame game) {
        String[] commands = moveCommand.split(" ");
        game.movePiece(commands[1], commands[2]);
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
