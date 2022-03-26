package chess.domain;

import chess.view.InputView;
import chess.view.OutputView;

import java.util.Arrays;
import java.util.List;

public class GameMachine {

    private static final String MOVE_DELIMITER = " ";
    private static final int MOVE_COMMAND_SIZE = 3;
    private static final int SOURCE_INDEX = 1;
    private static final int TARGET_INDEX = 2;

    public void run() {
        InputView.announceStart();
        GameManager gameManager = null;
        String command = "";
        do {
            command = InputView.requestCommand();
            gameManager = play(gameManager, command);
        } while (!Command.isEnd(command) && !gameManager.isEnd());
    }

    private GameManager play(GameManager gameManager, final String command) {
        if (Command.isStart(command)) {
            gameManager = new GameManager(new BoardInitializer());
            OutputView.printBoard(gameManager);
        }
        if (Command.isMove(command)) {
            movePiece(gameManager, Arrays.asList(command.split(MOVE_DELIMITER)));
        }
        if (Command.isStatus(command)) {
            OutputView.printScore(gameManager);
        }
        return gameManager;
    }

    private void movePiece(GameManager gameManager, final List<String> commands) {
        if (gameManager == null) {
            OutputView.announceNotStarted();
            return;
        }
        if (commands.size() != MOVE_COMMAND_SIZE) {
            OutputView.announceBadMoveCommand();
            return;
        }
        movePieceOnBoard(gameManager, commands);
        OutputView.printBoard(gameManager);
    }

    private void movePieceOnBoard(GameManager board, final List<String> commands) {
        try {
            board.move(commands.get(SOURCE_INDEX), commands.get(TARGET_INDEX));
        } catch (IllegalArgumentException e) {
            OutputView.announceBadMovement(e.getMessage());
        }
    }
}
