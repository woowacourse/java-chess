package chess.controller;

import java.util.List;
import java.util.Map;

import chess.command.CommandType;
import chess.domain.ChessGame;
import chess.domain.Result;
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

    private boolean executeCommand(Map.Entry<CommandType, List<Square>> commands) {
        CommandType commandType = commands.getKey();
        if (commandType == CommandType.START) {
            start();
            return true;
        }

        if (commandType == CommandType.MOVE) {
            move(commands);
            return true;
        }

        if (commandType == CommandType.END) {
            return false;
        }

        if (commandType == CommandType.STATUS) {
            status();
            return false;
        }

        if (commandType == CommandType.CONTINUE) {
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

    private void move(Map.Entry<CommandType, List<Square>> commands) {
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
        Result result = game.saveStatus();
        OutputView.showScore(result, Color.WHITE);
        OutputView.showScore(result, Color.BLACK);
    }

    private void checkGameStarted() {
        if (game == null) {
            throw new IllegalArgumentException(ERROR_MESSAGE_IMPOSSIBLE_COMMAND);
        }
    }

}
