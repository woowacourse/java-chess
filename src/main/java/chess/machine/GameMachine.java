package chess.machine;

import chess.domain.manager.BoardInitializer;
import chess.domain.manager.GameManager;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.Arrays;
import java.util.List;

public final class GameMachine {

    private static final String MOVE_DELIMITER = " ";
    private static final int MOVE_COMMAND_SIZE = 3;
    private static final int SOURCE_INDEX = 1;
    private static final int TARGET_INDEX = 2;

    public void run() {
        InputView.announceStart();
        GameManager gameManager = initializeGameManager();
        String command = InputView.requestCommand();
        while (!Command.isEnd(command) && !gameManager.isEnd()) {
            play(gameManager, command);
            command = InputView.requestCommand();
        }
    }

    private GameManager initializeGameManager() {
        while (!Command.isStart(InputView.requestCommand())) {
            OutputView.announceNotStarted();
        }
        return makeGameManager();
    }

    private GameManager makeGameManager() {
        GameManager gameManager = new GameManager(new BoardInitializer());
        OutputView.printBoard(gameManager);
        return gameManager;
    }

    private void play(GameManager gameManager, final String command) {
        if (Command.isStart(command)) {
            OutputView.announceCanNotRestart();
        }
        if (Command.isMove(command)) {
            movePiece(gameManager, Arrays.asList(command.split(MOVE_DELIMITER)));
        }
        if (Command.isStatus(command)) {
            OutputView.printScore(gameManager);
        }
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
