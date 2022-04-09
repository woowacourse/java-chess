package chess.dao;

import chess.domain.board.Location;
import chess.domain.piece.Piece;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

public class BoardDao {

    public Piece getPiece(String location) {
        final Connection connection = Connector.getConnection();
        final String sql = "select team, name from board where location = ?";
        try {
            final PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, location);
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                return null;
            }
            return PieceConverter.of(resultSet.getString("team"), resultSet.getString("name"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void addPiece(String location, Piece piece) {
        final Connection connection = Connector.getConnection();
        final String sql = "insert into board (location, team, name) values (?, ?, ?)";
        try {
            final PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, location);
            statement.setString(2, piece.getTeam());
            statement.setString(3, piece.getName());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updatePiece(String location, Piece piece) {
        final Connection connection = Connector.getConnection();
        final String sql = "update board set team = ?, name = ? where location = ?";
        try {
            final PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, piece.getTeam());
            statement.setString(2, piece.getName());
            statement.setString(3, location);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //TODO
    public void deleteAll() {
        final String sql = "truncate table board";
        try (PreparedStatement statement = Connector.getConnection().prepareStatement(sql)) {
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Map<Location, Piece> getBoardMap() {
        Connection connection = Connector.getConnection();
        String sql = "select location, team, name from board";
        Map<Location, Piece> map = new LinkedHashMap<>();
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                map.put(Location.of(resultSet.getString("location")),
                        PieceConverter.of(resultSet.getString("team"), resultSet.getString("name")));
            }
            return map;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
