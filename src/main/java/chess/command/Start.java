package chess.command;

import chess.domain.board.Board;
import chess.view.OutputView;
import java.util.Optional;

public class Start extends CommandChain {

    public Start() {
        super(Optional.of(new Move()));
    }

    @Override
    protected boolean canDoAction(Command command, Board board) {
        return command == Command.START && board.isReady();
    }

    @Override
    protected void doAction(String[] rawCommand, Board board) {
        board.startFirstTurn();
        OutputView.printBoard(board.getPieces());
    }
}
