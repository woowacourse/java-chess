package chess.console.command;

import chess.console.view.OutputView;
import chess.domain.GameManager;

public final class Move implements Command {

    public static final String MOVE_COMMAND_DELIMITER = " ";
    public static final int SOURCE_INDEX = 1;
    public static final int DESTINATION_INDEX = 2;

    private final String source;
    private final String destination;

    public Move(String moveCommand) {
        String[] arguments = moveCommand.split(MOVE_COMMAND_DELIMITER);
        source = arguments[SOURCE_INDEX];
        destination = arguments[DESTINATION_INDEX];
    }

    @Override
    public void execute(GameManager gameManager) {
        if (gameManager.isFinished()) {
            OutputView.printResult(gameManager.getStatus().getResult());
            return;
        }

        gameManager.move(source, destination);
        OutputView.printBoard(gameManager.getBoard());
    }

    @Override
    public boolean isEnd() {
        return false;
    }
}
