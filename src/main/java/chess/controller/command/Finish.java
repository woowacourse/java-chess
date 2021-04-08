package chess.controller.command;

import chess.domain.board.Board;

public final class Finish extends AbstractCommand{

    public Finish(final Board board) {
        super(board);
    }

    @Override
    public Command execute(final String command) {
        if (commandIsStatus(command)) {
            return this;
        }
        if ("exit".equals(command)) {
            return new Exit(board);
        }
        if ("start".equals(command)) {
            return new Play(new Board());
        }
        printErrorMessage("start, status, exit");
        return this;
    }
}
