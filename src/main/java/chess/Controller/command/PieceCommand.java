package chess.Controller.command;

import chess.Controller.dto.PiecesDto;
import chess.dao.BoardDao;
import chess.dao.PiecesDao;
import chess.dao.UserDao;
import chess.domain.board.Board;

public abstract class PieceCommand {

    private static final String CANNOT_IMPLEMENT_COMMAND = "현재 실행할 수 없는 명령입니다.";

    protected final BoardDao boardDao;
    protected final PiecesDao piecesDao;
    protected final UserDao userDao;

    protected PieceCommand() {
        this.boardDao = new BoardDao();
        this.piecesDao = new PiecesDao();
        this.userDao = new UserDao();
    }

    public PiecesDto doCommandAction(final ParsedCommand parsedCommand, final Board board, final int userId) {
        if (canDoAction(parsedCommand.getCommand(), board)) {
            return PiecesDto.fromEntity(doAction(parsedCommand, board, userId));
        }
        throw new IllegalArgumentException(CANNOT_IMPLEMENT_COMMAND);
    }

    protected abstract boolean canDoAction(final Command command, final Board board);

    protected abstract Board doAction(final ParsedCommand parsedCommand, final Board board, final int userId);

}
