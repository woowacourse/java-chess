package chess.machine;

import chess.domain.game.BoardInitializer;
import chess.domain.game.Game;
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
        Game game = null;
        String command = "";
        do {
            command = InputView.requestCommand();
            game = play(game, command);
        } while (!Command.isEnd(command) && !game.isEnd());
        printFinalResult(game);
    }

    private Game play(final Game game, final String command) {
        if (Command.isStart(command)) {
            return makeGame();
        }
        if (Command.isMove(command)) {
            movePiece(game, Arrays.asList(command.split(MOVE_DELIMITER)));
        }
        if (Command.isStatus(command)) {
            OutputView.printScoreResult(game);
        }
        return game;
    }

    private Game makeGame() {
        Game game = new Game(new BoardInitializer());
        OutputView.printBoard(game);
        return game;
    }

    private void movePiece(final Game game, final List<String> commands) {
        if (game == null) {
            OutputView.announceNotStarted();
            return;
        }
        if (commands.size() != MOVE_COMMAND_SIZE) {
            OutputView.announceBadMoveCommand();
            return;
        }
        movePieceOnBoard(game, commands);
        OutputView.printBoard(game);
    }

    private void movePieceOnBoard(final Game game, final List<String> commands) {
        try {
            game.move(commands.get(SOURCE_INDEX), commands.get(TARGET_INDEX));
        } catch (IllegalArgumentException e) {
            OutputView.announceBadMovement(e.getMessage());
        }
    }

    private void printFinalResult(final Game game) {
        if (game != null) {
            OutputView.printFinalResult(game);
        }
    }
}
