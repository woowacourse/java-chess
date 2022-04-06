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

public class ConsoleController {
    private static final String ERROR_MESSAGE_IMPOSSIBLE_COMMAND = "[ERROR] 지금은 앙댕! 혼난다??\n";
    private static final String ERROR_CONSOLE_CANT_CONTINUE = "[ERROR] 콘솔에선 안되!";
    private static final int SOURCE_INDEX = 0;
    private static final int TARGET_INDEX = 1;

    protected ChessGame game;

    public void run() {
        OutputView.announceStart();

        boolean keepGaming = true;
        while (keepGaming) {
            keepGaming = inGame();
        }
    }

    private boolean inGame() {
        try {
            return executeCommand(InputView.requireCommand());
        } catch (IllegalArgumentException e) {
            OutputView.printMessage(e.getMessage());
            return true;
        }
    }

    private boolean executeCommand(Map.Entry<Command, List<Square>> commands) {
        Command command = commands.getKey();
        if (command == Command.START) {
            start();
            return true;
        }

        if (command == Command.MOVE) {
            move(commands);
            return true;
        }

        if (command == Command.END) {
            return false;
        }

        if (command == Command.STATUS) {
            status();
            return false;
        }

        if (command == Command.CONTINUE) {
            throw new IllegalArgumentException(ERROR_CONSOLE_CANT_CONTINUE);
        }
        return true;
    }

    private void start() {
        if (game != null) {
            throw new IllegalArgumentException(ERROR_MESSAGE_IMPOSSIBLE_COMMAND);
        }
        game = new ChessGame();
        OutputView.showBoard(game.getBoard());
    }

    private void move(Map.Entry<Command, List<Square>> commands) {
        checkGameStarted();
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
        checkGameStarted();
        if (!game.isKingDie()) {
            throw new IllegalArgumentException(ERROR_MESSAGE_IMPOSSIBLE_COMMAND);
        }
        Status status = game.saveStatus();
        OutputView.showScore(status, Color.WHITE);
        OutputView.showScore(status, Color.BLACK);
    }

    private void checkGameStarted() {
        if (game == null) {
            throw new IllegalArgumentException(ERROR_MESSAGE_IMPOSSIBLE_COMMAND);
        }
    }

}
