package chess.Controller;

import chess.Controller.command.ParsedCommand;
import chess.Controller.command.PieceCommandFactory;
import chess.Controller.command.ScoreCommandFactory;
import chess.Controller.dto.PiecesDto;
import chess.Controller.dto.ScoreDto;
import chess.Controller.dto.StateDto;
import chess.dao.BoardDao;
import chess.dao.CommonDao;
import chess.dao.PiecesDao;
import chess.dao.UserDao;
import chess.domain.GameState;
import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.piece.Piece;
import java.util.Map;

public class ChessController {

    public ChessController() {
    }

    public int initGame(final String userName) {
        final UserDao userDao = new UserDao();
        final int exUserId = userDao.getUser(userName);
        int boardId = userDao.getBoard(exUserId);
        if (exUserId == CommonDao.FAILED) {
            boardId = (new BoardDao()).initBoard();
        }
        userDao.createUser(userName, boardId);
        return userDao.getUser(userName);
    }

    public PiecesDto getCurrentBoardState(final int userId) {
        return PiecesDto.fromEntity(createBoard(userId));
    }

    public PiecesDto doActionAboutPieces(final ParsedCommand parsedCommand, final int userId) {
        return PieceCommandFactory.from(parsedCommand.getCommand())
                .doCommandAction(parsedCommand, createBoard(userId), userId);
    }


    public ScoreDto doActionAboutScore(final ParsedCommand parsedCommand, final int userId) {
        return ScoreCommandFactory.from(parsedCommand.getCommand())
                .doCommandAction(parsedCommand, createBoard(userId), userId);
    }

    private Board createBoard(final int userId) {
        final int boardId = (new UserDao()).getBoard(userId);
        final Map<Position, Piece> pieces = (new PiecesDao()).getPieces(boardId);
        final GameState gameState = (new BoardDao()).getGameStatus(userId);
        return new Board(pieces, gameState);
    }

    public StateDto getCurrentStatus(final int userId) {
        final GameState gameState = (new BoardDao()).getGameStatus(userId);
        return StateDto.fromEntity(gameState);
    }

    public void finishGame(final int userId) {
        final UserDao userDao = new UserDao();
        final int boardId = userDao.getBoard(userId);
        userDao.deleteUser(userId);
        (new PiecesDao()).deletePieces(boardId);
        (new BoardDao()).deleteBoard(boardId);
    }
}
