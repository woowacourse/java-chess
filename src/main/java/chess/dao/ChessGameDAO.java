package chess.dao;

import chess.view.OutputView;
import chess.utils.DBConnectionUtils;
import chess.dto.ChessGameRoomInfoDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public final class ChessGameDAO {

    public String addGame(final ChessGame chessGame) throws SQLException {
        String query = "INSERT INTO CHESS_GAME (name) VALUES (?)";
        Connection connection = DBConnectionUtils.getConnection();
        String gameId = null;
        try {
            PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, chessGame.getName());
            statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();
            rs.next();
            gameId = rs.getString(1);
            DBConnectionUtils.closeConnection(connection);
        } catch (SQLException e) {
            OutputView.printErrorMessage(e.getMessage());
        }
        return gameId;
    }

    public List<ChessGameRoomInfoDTO> findActiveGames() throws SQLException {
        String query = "SELECT * FROM CHESS_GAME WHERE IS_END = false";
        Connection connection = DBConnectionUtils.getConnection();
        List<ChessGameRoomInfoDTO> chessGameDTOs = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                ChessGameRoomInfoDTO chessGameDTO = new ChessGameRoomInfoDTO(rs.getString("id"), rs.getString("name"));
                chessGameDTOs.add(chessGameDTO);
            }
            DBConnectionUtils.closeConnection(connection);
        } catch (SQLException e) {
            OutputView.printErrorMessage(e.getMessage());
        }
        return chessGameDTOs;
    }

    public ChessGameRoomInfoDTO findGameById(final String gameId) {
        String query = "SELECT * FROM CHESS_GAME WHERE ID = ? AND IS_END = FALSE ORDER BY created_at";
        Connection connection = DBConnectionUtils.getConnection();

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, gameId);
            ResultSet rs = statement.executeQuery();
            rs.next();
            return new ChessGameRoomInfoDTO(rs.getString("id"), rs.getString("name"));
        } catch (SQLException e) {
            OutputView.printErrorMessage(e.getMessage());
        }
        return null;
    }

    public void updateGameEnd(final String gameId) throws SQLException {
        String query = "UPDATE chess_game SET is_end = true WHERE id = ?";
        final Connection connection = DBConnectionUtils.getConnection();
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, gameId);

        try (connection; statement) {
            statement.executeUpdate();
        }
    }
}
