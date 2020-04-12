package chess.service;

import chess.controller.dao.ChessBoardDao;
import chess.controller.dao.GameDao;
import chess.controller.dto.ResponseDto;

import java.sql.SQLException;

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
}