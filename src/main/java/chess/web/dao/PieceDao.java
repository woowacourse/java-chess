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

    public int save(PieceDto pieceDto) throws SQLException {
        final String sql = "insert into piece (position, name, imagePath) values (?, ?, ?)";
        final PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        statement.setString(1, pieceDto.getPosition());
        statement.setString(2, pieceDto.getName());
        statement.setString(3, pieceDto.getImageName());
        statement.executeUpdate();
        ResultSet result = statement.getGeneratedKeys();

        if (result.next()) {
            return result.getInt(1);
        }

        return 0;
    }

    public List<Integer> saveAll(List<PieceDto> pieces) throws SQLException {
        List<Integer> ids = new ArrayList<>();
        for (PieceDto pieceDto : pieces) {
            ids.add(save(pieceDto));
        }
        return Collections.unmodifiableList(ids);
    }

    public void remove(int id) throws SQLException {
        final String sql = "delete from piece where id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);
        statement.executeUpdate();
    }

    public void update(List<PieceDto> pieces) throws SQLException {
        removeAll();
        saveAll(pieces);
    }

    public void removeAll() throws SQLException {
        final String sql = "delete from piece";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.executeUpdate();
    }
}
