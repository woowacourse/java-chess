package chess.Controller.command;

import chess.dao.BoardDao;
import chess.dao.UserDao;
import chess.domain.GameState;
import chess.domain.Score;
import chess.domain.board.Board;

public class End extends ScoreCommand {

    private final BoardDao boardDao;
    private final UserDao userDao;

    public End() {
        this.boardDao = new BoardDao();
        this.userDao = new UserDao();
    }

    @Override
    protected boolean canDoAction(final Command command, final Board board) {
        return command == Command.END;
    }

    @Override
    protected Score doAction(final ParsedCommand parsedCommand, final Board board, final int userId) {
        final int boardId = userDao.getBoard(userId);
        boardDao.changeGameStatus(GameState.END.toString(), boardId);
        return new Score(board);
    }
}
