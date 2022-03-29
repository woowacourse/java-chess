package chess.command;

import chess.domain.board.Board;
import chess.view.OutputView;
import java.util.Optional;

public class Status extends CommandChain {

    Status() {
        super(Optional.of(new End()));
    }

    @Override
    protected boolean canDoAction(Command command, Board board) {
        return command == Command.STATUS && board.isRunning();
    }

    @Override
    protected void doAction(String[] rawCommand, Board board) {
        OutputView.printStatus(new chess.domain.Status(board));
    }
}
