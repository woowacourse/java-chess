package chess.Controller.command;

import chess.dao.BoardDao;
import chess.dao.UserDao;
import chess.domain.GameState;
import chess.domain.Score;
import chess.domain.board.Board;

public class End extends ScoreCommand {

    @Override
    protected boolean canDoAction(final Command command, final Board board) {
        return command == Command.END;
    }

    @Override
    protected Score doAction(final ParsedCommand parsedCommand, final Board board, final int userId) {
        final int boardId = (new UserDao()).getBoard(userId);
        (new BoardDao()).changeGameStatus(GameState.END.toString(), boardId);
        return new Score(board);
    }
}
