package chess.domain.game;

import chess.machine.Command;
import chess.view.OutputView;

import java.util.Arrays;
import java.util.List;

public class ChessController {

    private static final String MOVE_DELIMITER = " ";
    private static final int MOVE_COMMAND_SIZE = 3;
    private static final int SOURCE_INDEX = 1;
    private static final int TARGET_INDEX = 2;

    public void move(final Game game, final String command) {
        if (Command.isMove(command)) {
            movePiece(game, Arrays.asList(command.split(MOVE_DELIMITER)));
        }
    }

    private void movePiece(Game game, final List<String> commands) {
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
