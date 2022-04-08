package chess.Controller.command;

import chess.dao.BoardDao;
import chess.domain.GameState;
import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.piece.Color;
import chess.domain.piece.PieceType;
import java.util.Locale;

public class Move extends PieceCommand {

    public Move() {
        super();
    }

    @Override
    protected boolean canDoAction(final Command command, final Board board) {
        return command == Command.MOVE && board.isRunning();
    }

    @Override
    protected Board doAction(final ParsedCommand parsedCommand, final Board board, final int userId) {
        final Position start = parsedCommand.getStart();
        final Position target = parsedCommand.getDestination();
        final Color currentColor = board.getCurrentColor();
        final int boardId = userDao.getBoard(userId);
        if (board.move(start, target, currentColor).isSamePiece(PieceType.KING)) {
            boardDao.changeGameStatus(GameState.END.toString(), boardId);
            return board;
        }
        movePiece(start, target, boardId);
        changeGameStatus(boardDao, board, boardId);
        return board;
    }

    private void movePiece(final Position start, final Position target, final int boardId) {
        final String startPosition = start.getColumn().toString().toLowerCase(Locale.ROOT)
                + start.getRow().getValue();
        final String targetPosition = target.getColumn().toString().toLowerCase(Locale.ROOT)
                + target.getRow().getValue();
        piecesDao.deletePiece(targetPosition);
        piecesDao.changePosition(startPosition, targetPosition, boardId);
    }

    private void changeGameStatus(final BoardDao boardDao, final Board board, final int boardId) {
        final GameState nextTurnGameState = board.changeTurn();
        boardDao.changeGameStatus(nextTurnGameState.toString(), boardId);
    }

}
