package chess.Controller.command;

import chess.domain.board.Board;

public class Status extends ScoreCommand {

    @Override
    protected boolean canDoAction(final Command command, final Board board) {
        return command == Command.STATUS && board.isRunning();
    }

    @Override
    protected chess.domain.Status doAction(final ParsedCommand parsedCommand, final Board board) {
        return new chess.domain.Status(board);
    }
}
