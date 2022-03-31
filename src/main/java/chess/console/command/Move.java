package chess.console.command;

import chess.console.GameManager;
import chess.domain.board.Position;
import chess.view.OutputView;

public final class Move implements Command {

    public static final String MOVE_COMMAND_DELIMITER = " ";
    public static final int SOURCE_INDEX = 1;
    public static final int DESTINATION_INDEX = 2;

    private final Position source;
    private final Position destination;

    public Move(String moveCommand) {
        String[] arguments = moveCommand.split(MOVE_COMMAND_DELIMITER);
        source = Position.of(arguments[SOURCE_INDEX]);
        destination = Position.of(arguments[DESTINATION_INDEX]);
    }

    @Override
    public void execute(GameManager gameManager) {
        gameManager.move(source, destination);
        OutputView.printBoard(gameManager);
    }

    @Override
    public boolean isEnd() {
        return false;
    }
}
