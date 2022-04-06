package chess.dao;

import chess.domain.piece.Piece;
import chess.domain.piece.PieceFactory;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PieceDao {

    private static final String URL = "jdbc:mysql://localhost:3306/chess";
    private static final String USER = "user";
    private static final String PASSWORD = "password";

    public Connection getConnection() {
        loadDriver();
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
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

    public void save(Piece piece) {
        final Connection connection = getConnection();
        final String sql = "insert into piece (name, color, position) values (?, ?, ?)";
        try {
            final PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, piece.getName());
            statement.setString(2, piece.getColor().getName());
            statement.setString(3, piece.getPosition().getPosition());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void deleteByPosition(String position) {
        final Connection connection = getConnection();
        final String sql = "delete from piece where position = ?";
        try {
            final PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, position);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateByPosition(String source, String target) {
        final Connection connection = getConnection();
        final String sql = "update piece set position = ? where position = ?";
        try {
            final PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, target);
            statement.setString(2, source);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Piece> findAll() {
        final Connection connection = getConnection();
        final String sql = "select name, color, position from piece";
        final List<Piece> members = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                members.add(PieceFactory.of(resultSet.getString("name"), resultSet.getString("color"),
                    resultSet.getString("position")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return members;
    }

    public Piece findByPosition(String position) {
        final Connection connection = getConnection();
        final String sql = "select name, color, position from piece where position = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, position);
            resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                return null;
            }
            return PieceFactory.of(resultSet.getString("name"), resultSet.getString("color"),
                resultSet.getString("position"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
