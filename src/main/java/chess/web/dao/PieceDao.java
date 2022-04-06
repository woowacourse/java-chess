package chess.web.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import chess.web.dto.PieceDto;
import chess.web.utils.DBConnector;

public class PieceDao {
    private final Connection connection = DBConnector.getConnection();

    public List<PieceDto> findAllByRoomId(int roomId) throws SQLException {
        final String sql = "select position, name, imagePath from piece where roomId = ?";
        List<PieceDto> pieces = new ArrayList<>();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, roomId);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            PieceDto pieceDto = PieceDto.of(
                resultSet.getString("position"),
                resultSet.getString("name"),
                resultSet.getString("imagePath")
            );
            pieces.add(pieceDto);
        }
        return pieces;
    }

    public List<PieceDto> findAll() throws SQLException{
        final String sql = "select position, name, imagePath from piece";
        List<PieceDto> pieces = new ArrayList<>();
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            PieceDto pieceDto = PieceDto.of(
                resultSet.getString("position"),
                resultSet.getString("name"),
                resultSet.getString("imagePath")
            );
            pieces.add(pieceDto);
        }
        return pieces;
    }

    public int save(PieceDto pieceDto, int roomId) throws SQLException {
        final String sql = "insert into piece (position, name, imagePath, roomId) values (?, ?, ?, ?)";
        final PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        statement.setString(1, pieceDto.getPosition());
        statement.setString(2, pieceDto.getName());
        statement.setString(3, pieceDto.getImageName());
        statement.setInt(4, roomId);
        statement.executeUpdate();
        ResultSet result = statement.getGeneratedKeys();

        if (result.next()) {
            return result.getInt(1);
        }

        return 0;
    }

    public List<Integer> saveAll(List<PieceDto> pieces, int roomId) throws SQLException {
        List<Integer> ids = new ArrayList<>();
        for (PieceDto pieceDto : pieces) {
            ids.add(save(pieceDto, roomId));
        }
        return Collections.unmodifiableList(ids);
    }

    public void remove(int id) throws SQLException {
        final String sql = "delete from piece where id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);
        statement.executeUpdate();
    }

    public void update(List<PieceDto> pieces, int roomId) throws SQLException {
        removeAllByRoomId(roomId);
        saveAll(pieces, roomId);
    }

    public void removeAll() throws SQLException {
        final String sql = "delete from piece";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.executeUpdate();
    }

    public void removeAllByRoomId(int roomId) throws SQLException {
        final String sql = "delete from piece where roomId = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, roomId);
        statement.executeUpdate();
    }
}
