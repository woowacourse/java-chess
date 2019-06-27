package chess.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import chess.database.DatabaseConnection;
import chess.domain.Player;

public class ChessGameDAO {
    private static final int ROUND_INDEX = 1;
    private static final String INSERT_CHESS_GAME_QUERY = "insert into chessgame(turn) values(?)";
    private static final String UPDATE_CHESS_GAME_TURN_QUERY = "update chessgame set turn = ? where room_number = ?";
    private static final String SELECT_CHESS_GAME_TURN = "select turn from chessgame where room_number = ?";
    private static final String UPDATE_CHESS_GAME_OVER_QUERY = "update chessgame set gameover = ? where room_number = ?";
    private static final String SELECT_NOT_OVER_CHESS_GAME = "select room_number from chessgame where gameover = 1";

    private static ChessGameDAO chessGameDAO;

    private ChessGameDAO() {
    }

    public static ChessGameDAO getInstance() {
        if (chessGameDAO == null) {
            chessGameDAO = new ChessGameDAO();
        }
        return chessGameDAO;
    }

    public int addChessGame(Player currentPlayer) throws SQLException {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(
                INSERT_CHESS_GAME_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, currentPlayer.name());
            pstmt.executeUpdate();
            return getRoomNumber(pstmt);
        }
    }

    private int getRoomNumber(PreparedStatement pstmt) throws SQLException {
        try (ResultSet resultSet = pstmt.getGeneratedKeys()) {
            if (!resultSet.next()) {
                throw new SQLException();
            }
            return resultSet.getInt(ROUND_INDEX);
        }
    }


    public Player getChessGameTurn(int roomNumber) throws SQLException {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(SELECT_CHESS_GAME_TURN)) {
            pstmt.setInt(1, roomNumber);
            return getTurn(pstmt);
        }
    }

    private Player getTurn(PreparedStatement pstmt) throws SQLException {
        try (ResultSet resultSet = pstmt.executeQuery()) {
            if (!resultSet.next()) {
                throw new SQLException();
            }
            return Player.valueOf(resultSet.getString(1));
        }
    }

    public void changeTurn(int roomNumber, Player currentPlayer) throws SQLException {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(UPDATE_CHESS_GAME_TURN_QUERY)) {
            pstmt.setString(1, currentPlayer.name());
            pstmt.setInt(2, roomNumber);
            pstmt.executeUpdate();
        }
    }

    public void gameover(int roomNumber) throws SQLException {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(UPDATE_CHESS_GAME_OVER_QUERY)) {
            pstmt.setBoolean(1, false);
            pstmt.setInt(2, roomNumber);
            pstmt.executeUpdate();
        }
    }

    public List<Integer> getNotOverAllRoomNumbers() throws SQLException {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(SELECT_NOT_OVER_CHESS_GAME)) {
            return getAllRoomNumbers(pstmt);
        }
    }

    public List<Integer> getAllRoomNumbers(PreparedStatement pstmt) throws SQLException {
        try (ResultSet resultSet = pstmt.executeQuery()) {
            List<Integer> roomNumbers = new ArrayList<>();
            while (resultSet.next()) {
                roomNumbers.add(resultSet.getInt(1));
            }
            return roomNumbers;
        }
    }
}
