package chess.domain.command;

import chess.domain.ChessGame;
import chess.exception.GameIsNotStartException;

public class MoveOnCommand implements Command {
    private static final String COMMAND_NAME = "move";
    private static final String COMMAND_MESSAGE = "%s 에서 %s로 이동을 하였습니다";

    public String run(ChessGame chessGame, CommandInput commandInput) {
        checkGameStart(chessGame);
        chessGame.move(commandInput.getTarget(), commandInput.getDestination());
        return String.format(COMMAND_MESSAGE, commandInput.getTarget(), commandInput.getDestination());
    }

    public void checkGameStart(ChessGame chessGame) {
        if (chessGame.isBeforeStart()) {
            throw new GameIsNotStartException();
        }
    }

    @Override
    public boolean isSameCommand(CommandInput commandInput) {
        return commandInput.isSameCommand(COMMAND_NAME);
    }
}
