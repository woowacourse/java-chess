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
        final String sql = "insert into piece (game_name, position, type, player) values (?,?,?,?)";
        try {
            final PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, pieceDto.getGameName());
            statement.setString(2, pieceDto.getPosition());
            statement.setString(3, pieceDto.getType());
            statement.setString(4, pieceDto.getPlayer());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public List<PieceDto> findByGameName(String game_name) {
        List<PieceDto> pieceDtos = new ArrayList<>();
        final Connection connection = getConnection();
        final String sql = "select * from piece where game_name = ?";
        try {
            final PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, game_name);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                pieceDtos.add(new PieceDto(resultSet.getString("game_name"),
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
}
