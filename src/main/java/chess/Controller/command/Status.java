package chess.Controller.command;

import chess.domain.Score;
import chess.domain.board.Board;

public class Status extends ScoreCommand {

    @Override
    protected boolean canDoAction(final Command command, final Board board) {
        return command == Command.STATUS && board.isRunning();
    }

    @Override
    protected Score doAction(final ParsedCommand parsedCommand, final Board board, final int userId) {
        return new Score(board);
    }
}
