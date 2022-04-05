package chess.Controller.command;

import chess.dao.BoardDao;
import chess.dao.PiecesDao;
import chess.dao.UserDao;
import chess.domain.GameState;
import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.piece.Color;
import chess.domain.piece.PieceType;
import java.util.Locale;

public class Move extends PieceCommand {

    @Override
    protected boolean canDoAction(final Command command, final Board board) {
        return command == Command.MOVE && board.isRunning();
    }

    @Override
    protected Board doAction(final ParsedCommand parsedCommand, final Board board, final int userId) {
        final Position start = parsedCommand.getStart();
        final Position target = parsedCommand.getDestination();
        final Color currentColor = board.getCurrentColor();
        final int boardId = (new UserDao()).getBoard(userId);
        final BoardDao boardDao = new BoardDao();
        if (board.move(start, target, currentColor).isSamePiece(PieceType.KING)) {
            boardDao.changeGameStatus(GameState.END.toString(), boardId);
            return board;
        }
        final String startPosition = start.getColumn().toString().toLowerCase(Locale.ROOT)
                + start.getRow().getValue();
        final String targetPosition = target.getColumn().toString().toLowerCase(Locale.ROOT)
                + target.getRow().getValue();
        final PiecesDao piecesDao = new PiecesDao();
        piecesDao.deletePiece(targetPosition);
        piecesDao.changePosition(startPosition, targetPosition, boardId);
        final GameState nextTurnGameState = board.getGameState();
        boardDao.changeGameStatus(nextTurnGameState.toString(), boardId);
        return board;
    }
}
