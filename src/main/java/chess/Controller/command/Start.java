package chess.Controller.command;

import chess.domain.board.Board;

public class Start extends PieceCommand {

    @Override
    protected boolean canDoAction(final Command command, final Board board) {
        return command == Command.START && board.isReady();
    }

    @Override
    protected Board doAction(final ParsedCommand parsedCommand, final Board board) {
        board.startFirstTurn();
        return board;
    }
}
