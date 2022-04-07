package web.dao;

import web.dto.PieceDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static web.dao.DBConnector.getConnection;

public class PieceDao {

    public void save(PieceDto pieceDto) {
        final Connection connection = getConnection();
        final String sql = "insert into pieces (room_name, position, piece_type, piece_color) " +
                "value (?, ?, ?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, pieceDto.getRoomName());
            statement.setString(2, pieceDto.getPosition());
            statement.setString(3, pieceDto.getPieceType());
            statement.setString(4, pieceDto.getPieceColor());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public PieceDto findByRoomNameAndPosition(String roomName, String position) {
        final Connection connection = getConnection();
        final String sql = "select room_name, position, piece_type, piece_color " +
                "from pieces " +
                "where room_name=? and position=?";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, roomName);
            statement.setString(2, position);
            final ResultSet result = statement.executeQuery();
            if (!result.next()) {
                return null;
            }
            return new PieceDto(result.getString("room_name"), result.getString("position"),
                    result.getString("piece_type"), result.getString("piece_color"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<PieceDto> findAllPiecesByRoomName(String roomName) {
        final Connection connection = getConnection();
        final String sql = "select room_name, position, piece_type, piece_color " +
                "from pieces " +
                "where room_name=?";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, roomName);
            final ResultSet result = statement.executeQuery();
            List<PieceDto> pieces = new ArrayList<>();
            while (result.next()) {
                pieces.add(new PieceDto(result.getString("room_name"), result.getString("position"),
                        result.getString("piece_type"), result.getString("piece_color")));
            }
            return pieces;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void update(String roomName, String previousPosition, String newPosition) {
        final Connection connection = getConnection();
        final String sql = "update pieces " +
                "set position=? " +
                "where room_name=? and position=?";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, newPosition);
            statement.setString(2, roomName);
            statement.setString(3, previousPosition);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteByRoomNameAndPosition(String roomName, String position) {
        final Connection connection = getConnection();
        final String sql = "delete from pieces " +
                "where room_name=? and position=?";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, roomName);
            statement.setString(2, position);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
