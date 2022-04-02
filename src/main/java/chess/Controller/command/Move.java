package chess.Controller.command;

import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.piece.Color;
import chess.domain.piece.PieceType;

public class Move extends PieceCommand {

    @Override
    protected boolean canDoAction(final Command command, final Board board) {
        return command == Command.MOVE && board.isRunning();
    }

    @Override
    protected Board doAction(final ParsedCommand parsedCommand, final Board board) {
        final Position start = parsedCommand.getStart();
        final Position target = parsedCommand.getDestination();
        final Color currentColor = board.getCurrentColor();
        if (board.move(start, target, currentColor).isSamePiece(PieceType.KING)) {
            board.terminateGame();
        }
        board.changeTurn();
        return board;
    }
}
