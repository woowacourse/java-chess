package chess.command;

import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.piece.Color;
import chess.domain.piece.PieceType;
import chess.view.OutputView;
import java.util.Optional;

public class Move extends CommandChain {

    public Move(final CommandChain commandChain) {
        super(Optional.ofNullable(commandChain));
    }

    @Override
    protected boolean canDoAction(final Command command, final Board board) {
        return command == Command.MOVE && board.isRunning();
    }

    @Override
    protected void doAction(final ParsedCommand parsedCommand, final Board board) {
        final Position start = parsedCommand.getStart();
        final Position target = parsedCommand.getDestination();
        final Color currentColor = board.getCurrentColor();
        if (board.move(start, target, currentColor).isSamePiece(PieceType.KING)) {
            board.terminateGame();
        }
        board.changeTurn();
        OutputView.printBoard(board.getPieces());
    }
}
