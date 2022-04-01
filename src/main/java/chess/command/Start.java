package chess.command;

import chess.domain.board.Board;
import chess.view.OutputView;
import java.util.Optional;

public class Start extends CommandChain {

    public Start(final CommandChain commandChain) {
        super(Optional.ofNullable(commandChain));
    }

    @Override
    protected boolean canDoAction(final Command command, final Board board) {
        return command == Command.START && board.isReady();
    }

    @Override
    protected void doAction(final ParsedCommand parsedCommand, final Board board) {
        board.startFirstTurn();
        OutputView.printBoard(board.getPieces());
    }
}
