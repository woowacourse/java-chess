package chess.service;

import chess.controller.dto.BoardDto;
import chess.controller.dto.GameDto;
import chess.controller.dto.ScoresDto;
import chess.domain.ChessGame;
import chess.domain.board.Board;
import chess.domain.board.position.Horizontal;
import chess.domain.board.position.Position;
import chess.domain.board.position.Vertical;
import chess.domain.piece.Owner;
import chess.domain.piece.Piece;
import chess.service.dao.GameDao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameService {

    private final GameDao gameDao;

    public GameService(Connection connection) {
        this.gameDao = new GameDao(connection);
    }

    public void create(final Long roomId) throws SQLException {
        final ChessGame chessGame = ChessGame.initNew();
        gameDao.save(roomId, chessGame.turn(), chessGame.board());
    }

    public void delete(final Long roomId) throws SQLException {
        gameDao.delete(roomId);
    }

    public List<String> show(final Long roomId, final Position source) throws SQLException {
        final ChessGame chessGame = loadChessGame(roomId);
        return chessGame.reachablePositions(source);
    }

    public void move(final Long roomId, final Position source, final Position target) throws SQLException {
        final ChessGame chessGame = loadChessGame(roomId);
        chessGame.move(source, target);
        gameDao.update(roomId, chessGame.turn(), chessGame.board());
    }

    public boolean isGameEnd(final Long roomId) throws SQLException {
        return loadChessGame(roomId).isGameEnd();
    }

    public ChessGame loadChessGame(final Long roomId) throws SQLException {
        final GameDto gameDto = gameDao.load(roomId);
        return ChessGame.load(gameDto.getBoard(), gameDto.getTurn());
    }

    public ScoresDto scores(final Long roomId) throws SQLException {
        final ChessGame chessGame = loadChessGame(roomId);
        final ScoresDto scoresDto = new ScoresDto();
        scoresDto.setWhiteScore(chessGame.score(Owner.WHITE));
        scoresDto.setBlackScore(chessGame.score(Owner.BLACK));
        return scoresDto;
    }

    public BoardDto board(final Long roomId) throws SQLException {
        final ChessGame chessGame = loadChessGame(roomId);
        final BoardDto boardDto = new BoardDto();
        boardDto.setBoard(chessGame.unicodeBoard());
        return boardDto;
    }

    public List<Owner> winner(final Long roomId) throws SQLException {
        return loadChessGame(roomId).winner();
    }


}
