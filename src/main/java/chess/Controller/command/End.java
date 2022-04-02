package chess.Controller.command;

import chess.domain.board.Board;

public class End extends ScoreCommand {

    @Override
    protected boolean canDoAction(final Command command, final Board board) {
        return command == Command.END;
    }

    @Override
    protected chess.domain.Status doAction(final ParsedCommand parsedCommand, final Board board) {
        board.terminateGame();
        return new chess.domain.Status(board);
    }
}
