package chess.Controller;

import chess.Controller.command.ParsedCommand;
import chess.Controller.command.PieceCommandFactory;
import chess.Controller.command.ScoreCommandFactory;
import chess.Controller.dto.PiecesDto;
import chess.Controller.dto.ScoreDto;
import chess.Controller.dto.StateDto;
import chess.dao.BoardDao;
import chess.dao.PiecesDao;
import chess.dao.UserDao;
import chess.domain.GameState;
import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.piece.Piece;
import java.util.Map;
import java.util.NoSuchElementException;

public class ChessController {

    private final BoardDao boardDao;
    private final PiecesDao piecesDao;
    private final UserDao userDao;


    public ChessController() {
        this.boardDao = new BoardDao();
        this.piecesDao = new PiecesDao();
        this.userDao = new UserDao();
    }

    public int initGame(final String userName) {
        int boardId;
        try {
            final int exUserId = userDao.getUser(userName);
            boardId = userDao.getBoard(exUserId);
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            boardId = boardDao.initBoard();
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
        final int boardId = userDao.getBoard(userId);
        final Map<Position, Piece> pieces = piecesDao.getPieces(boardId);
        final GameState gameState = boardDao.getGameStatus(userId);
        return new Board(pieces, gameState);
    }

    public StateDto getCurrentStatus(final int userId) {
        final GameState gameState = boardDao.getGameStatus(userId);
        return StateDto.fromEntity(gameState);
    }

    public void finishGame(final int userId) {
        final int boardId = userDao.getBoard(userId);
        userDao.deleteUser(userId);
        piecesDao.deletePieces(boardId);
        boardDao.deleteBoard(boardId);
    }
}
