package chess.controller;

import java.util.List;
import java.util.Map;

import chess.Command;
import chess.domain.ChessGame;
import chess.domain.Status;
import chess.domain.piece.Color;
import chess.domain.position.Square;
import chess.view.InputView;
import chess.view.OutputView;

public class Controller {
    private static final String ERROR_MESSAGE_IMPOSSIBLE_COMMAND = "[ERROR] 지금은 앙댕! 혼난다??\n";
    private static final int SOURCE_INDEX = 0;
    private static final int TARGET_INDEX = 1;

    private ChessGame game;

    public void run() {
        OutputView.announceStart();

        while (true) {
            inGame();
        }
    }

    private void inGame() {
        try {
            executeCommand(InputView.requireCommand());
        } catch (IllegalArgumentException e) {
            OutputView.printMessage(e.getMessage());
        }
    }

    private void executeCommand(Map.Entry<Command, List<Square>> commands) {
        Command command = commands.getKey();
        if (command == Command.START) {
            start();
        }

        if (command == Command.MOVE) {
            move(commands);
        }

        if (command == Command.END) {
            System.exit(0);
        }

        if (command == Command.STATUS) {
            status();
            System.exit(0);
        }
    }

    private void start() {
        if (game != null) {
            throw new IllegalArgumentException(ERROR_MESSAGE_IMPOSSIBLE_COMMAND);
        }
        game = new ChessGame();
        OutputView.showBoard(game.getBoard());
    }

    private void move(Map.Entry<Command, List<Square>> commands) {
        checkGameStarted(game);
        if (game.isKingDie()) {
            throw new IllegalArgumentException(ERROR_MESSAGE_IMPOSSIBLE_COMMAND);
        }

        List<Square> squares = commands.getValue();
        game.move(squares.get(SOURCE_INDEX), squares.get(TARGET_INDEX));
        OutputView.showBoard(game.getBoard());

        checkKingDieAfterMove();
    }

    private void checkKingDieAfterMove() {
        if (game.isKingDie()) {
            OutputView.printKingDieMessage();
        }
    }

    private void status() {
        checkGameStarted(game);
        if (!game.isKingDie()) {
            throw new IllegalArgumentException(ERROR_MESSAGE_IMPOSSIBLE_COMMAND);
        }
        Status status = game.saveStatus();
        OutputView.showScore(status, Color.WHITE);
        OutputView.showScore(status, Color.BLACK);
    }

    private void checkGameStarted(ChessGame game) {
        if (game == null) {
            throw new IllegalArgumentException(ERROR_MESSAGE_IMPOSSIBLE_COMMAND);
        }
    }

}
