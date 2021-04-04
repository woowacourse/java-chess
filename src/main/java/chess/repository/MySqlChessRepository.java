package chess.repository;

import chess.dao.ChessDAO;
import chess.dto.RoomDTO;
import chess.dto.RoomsDTO;

import java.sql.SQLException;

public class MySqlChessRepository implements ChessRepository {
    private ChessDAO chessDAO;

    public MySqlChessRepository() {
        this.chessDAO = new ChessDAO();
    }

    @Override
    public void createChessGame(final String chessGameData) {
        try {
            chessDAO.createChessGame(chessGameData);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void saveChessGame(final String gameId, final String chessGameData) {
        try {
            chessDAO.saveChessGame(gameId, chessGameData);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public String loadChessGame(final String gameId) {
        String gameData = "";
        try {
            gameData = chessDAO.loadChessGame(gameId);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return gameData;
    }

    @Override
    public RoomsDTO createRoom(final String name, final String pw) {
        try {
            return chessDAO.createRoom(name, pw);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }

    @Override
    public RoomDTO findRoomFromId(final String roomId) {
        try {
            return chessDAO.findRoomFromId(roomId);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
}
