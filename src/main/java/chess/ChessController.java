package chess;

import chess.domain.ChessGame;
import chess.domain.Color;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;

public class ChessController {
    private static final String START_GAME_COMMAND = "start";
    private static final String MOVE_COMMAND = "move";
    private static final String STATUS_COMMAND = "status";
    private static final String MOVE_COMMAND_DELIMITER = " ";

    private final InputView inputView;
    private final OutputView outputView;

    public ChessController() {
        inputView = new InputView();
        outputView = new OutputView();
    }

    public void run() {
        outputView.printGameStartMessage();
        String startOrEndInput = inputView.getStartOrEndInput();
        if (startOrEndInput.equals(START_GAME_COMMAND)) {
            startGame();
        }
        inputView.terminate();
    }

    private void startGame() {
        ChessGame game = new ChessGame();
        outputView.printBoard(game.getBoard());
        while (game.isRunning()) {
            play(game);
            outputView.printBoard(game.getBoard());
        }
    }

    private void play(ChessGame game) {
        try {
            String command = inputView.getCommand();
            decodeCommand(game, command);
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e.getMessage());
            play(game);
        }
    }

    private void decodeCommand(ChessGame game, String command) {
        if (command.startsWith(MOVE_COMMAND)) {
            playTurn(command, game);
        }
        if (command.startsWith(STATUS_COMMAND)) {
            showStatus(game);
        }
    }

    private void playTurn(String moveCommand, ChessGame game) {
        String[] commands = moveCommand.split(MOVE_COMMAND_DELIMITER);
        game.movePiece(commands[1], commands[2]);
    }

    private void showStatus(ChessGame game) {
        Map<Color, Double> scores = game.calculateScore();
        Comparator<Color> comparator = Comparator.comparing(scores::get);
        Color winningColor = Arrays.stream(Color.values())
                .max(comparator)
                .orElse(Color.NONE);

        outputView.printStatus(scores, winningColor);
    }
}
