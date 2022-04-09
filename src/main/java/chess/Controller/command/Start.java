package chess.Controller.command;

import chess.domain.GameState;
import chess.domain.board.Board;

public class Start extends PieceCommand {

    @Override
    protected boolean canDoAction(final Command command, final Board board) {
        return command == Command.START && board.isReady();
    }

    @Override
    protected Board doAction(final ParsedCommand parsedCommand, final Board board, final int userId) {
        boardDao.changeGameStatus(GameState.WHITE_RUNNING.toString(), userDao.getBoard(userId));
        return board;
    }
}
