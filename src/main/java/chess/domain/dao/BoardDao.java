package chess.domain.dao;

import chess.domain.dto.PieceDto;

import java.sql.*;
import java.util.List;

public class BoardDao {

    private final Connection connection;
    private int id = 0;

    public BoardDao() {
        connection = Connector.getConnection();
    }

    BoardDao(final Connection connection) {
        this.connection = connection;
    }

    public void save(int gameId, String position, String piece, String color) {
        final String sql = "insert into Board (id, game_id, position, piece, color) values(?, ?, ?, ?, ?)";
        try {
            final PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, ++id);
            statement.setInt(2, gameId);
            statement.setString(3, position);
            statement.setString(4, piece);
            statement.setString(5, color);
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public PieceDto findByGameId(int gameId){
        final String sql = "select * from board where game_id = ?";
        try {
            final PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, gameId);
            final ResultSet result = statement.executeQuery();
            if (!result.next()) {
                return null;
            }
            return new PieceDto(
                    result.getInt("id"),
                    result.getInt("game_id"),
                    result.getString("position"),
                    result.getString("piece")
            );
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public void delete(int gameId){
        if(gameId == 0){
            return;
        }

        final String sql = "delete from Board where game_id = ?";
        try {
            final PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, gameId);
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}
