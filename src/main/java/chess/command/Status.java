package chess.command;

import chess.domain.board.Board;
import chess.view.OutputView;
import java.util.Optional;

public class Status extends CommandChain {

    public Status(final CommandChain commandChain) {
        super(Optional.ofNullable(commandChain));
    }

    @Override
    protected boolean canDoAction(final Command command, final Board board) {
        return command == Command.STATUS && board.isRunning();
    }

    @Override
    protected void doAction(final ParsedCommand parsedCommand, final Board board) {
        OutputView.printStatus(new chess.domain.Status(board));
    }
}
