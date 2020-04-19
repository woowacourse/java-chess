package chess.service;

import chess.controller.dao.ChessBoardDao;
import chess.controller.dao.GameDao;
import chess.controller.dto.ResponseDto;
import chess.domain.chesspiece.Piece;
import chess.domain.game.GameStatus;
import chess.domain.game.Player;
import chess.domain.position.Position;

import java.sql.SQLException;
import java.util.Map;

public class ChessService {
    ChessBoardDao chessBoardDao = new ChessBoardDao();
    GameDao gameDao = new GameDao();

    public int findRoomNumber() throws SQLException {
        return gameDao.findMaxRoomNumber();
    }

    public void loadInitGame(ResponseDto responseDto) throws SQLException {
        gameDao.saveInitGame(responseDto);

        int roomNumber = gameDao.findMaxRoomNumber();

        chessBoardDao.saveChessBoard(responseDto.getChessBoardDto(), roomNumber);
    }

    public void endService() throws SQLException {
        int roomNumber = gameDao.findMaxRoomNumber();
        chessBoardDao.deleteChessBoard(roomNumber);
        gameDao.updateEndState(roomNumber);
    }

    public void move(ResponseDto responseDto) throws SQLException {
        int roomNumber = gameDao.findMaxRoomNumber();

        gameDao.updateGame(responseDto);
        chessBoardDao.deleteChessBoard(roomNumber);
        chessBoardDao.saveChessBoard(responseDto.getChessBoardDto(), roomNumber);
    }

    public GameStatus findState(int roomNumber) throws SQLException {
        return gameDao.findState(roomNumber);
    }

    public Map<Position, Piece> findPlayingChessBoard(int roomNumber) throws SQLException {
        return chessBoardDao.findPlayingChessBoard(roomNumber);
    }

    public Player findTurn(int roomNumber) throws SQLException {
        return gameDao.findTurn(roomNumber);
    }
}