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
        Game game = initializeGame();
        String command = InputView.requestCommand();
        while (!Command.isEnd(command) && !game.isEnd()) {
            play(game, command);
            command = InputView.requestCommand();
        }
    }

    private Game initializeGame() {
        while (!Command.isStart(InputView.requestCommand())) {
            OutputView.announceNotStarted();
        }
        return makeGame();
    }

    private Game makeGame() {
        Game game = new Game(new BoardInitializer());
        OutputView.printBoard(game);
        return game;
    }

    private void play(Game game, final String command) {
        if (Command.isStart(command)) {
            OutputView.announceCanNotRestart();
        }
        if (Command.isMove(command)) {
            movePiece(game, Arrays.asList(command.split(MOVE_DELIMITER)));
        }
        if (Command.isStatus(command)) {
            OutputView.printScore(game);
        }
    }

    private void movePiece(Game game, final List<String> commands) {
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

    private void movePieceOnBoard(Game game, final List<String> commands) {
        try {
            game.move(commands.get(SOURCE_INDEX), commands.get(TARGET_INDEX));
        } catch (IllegalArgumentException e) {
            OutputView.announceBadMovement(e.getMessage());
        }
    }
}
