package domain.dao;

import domain.dto.PieceDto;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PieceDao {

    private static final String URL = "jdbc:mysql://localhost:13306/chess";
    private static final String USER = "user";
    private static final String PASSWORD = "password";

    public Connection getConnection() {
        loadDriver();
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (final Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

    private void loadDriver() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }

    public void save(PieceDto pieceDto) {
        final Connection connection = getConnection();
        final String sql = "insert into piece (game_id, position, type, player) values (?,?,?,?)";
        try {
            final PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, Integer.toString(pieceDto.getGameId()));
            statement.setString(2, pieceDto.getPosition());
            statement.setString(3, pieceDto.getType());
            statement.setString(4, pieceDto.getPlayer());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public List<PieceDto> findByGameId(int gameId) {
        List<PieceDto> pieceDtos = new ArrayList<>();
        final Connection connection = getConnection();
        final String sql = "select * from piece where game_id = ?";
        try {
            final PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, Integer.toString(gameId));
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                pieceDtos.add(new PieceDto(Integer.parseInt(resultSet.getString("game_id")),
                    resultSet.getString("position"),
                    resultSet.getString("type"),
                    resultSet.getString("player")));
            }
            return pieceDtos;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void delete(int gameId) {
        final Connection connection = getConnection();
        final String sql = "delete from piece where game_id = ?";
        try {
            final PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, Integer.toString(gameId));
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
