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
    private static final int SOURCE_POSITION = 1;
    private static final int TARGET_POSITION = 2;

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
        ChessGame game = ChessGame.newGame();
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
        game.movePiece(commands[SOURCE_POSITION], commands[TARGET_POSITION]);
    }

    private void showStatus(ChessGame game) {
        Map<Color, Double> scores = game.calculateScore();
        Color winner = game.decideWinner();
        outputView.printStatus(scores, winner);
    }
}
