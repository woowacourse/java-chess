package chess.dao;

import chess.db.DBConnector;
import chess.domain.position.Position;
import chess.dto.PieceDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PieceDaoImpl implements PieceDao {

    private final Connection connection;

    public PieceDaoImpl() {
        connection = DBConnector.getConnection();
    }

    @Override
    public void remove(Position position) {
        final String sql = "delete from piece where position = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, position.getName());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeAll() {
        final String sql = "delete from piece";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveAll(List<PieceDto> pieceDtos) {
        pieceDtos.forEach(this::save);
    }

    @Override
    public void save(PieceDto pieceDto) {
        final String sql = "insert into piece (position, type, color) values (?, ?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, pieceDto.getPosition());
            statement.setString(2, pieceDto.getType());
            statement.setString(3, pieceDto.getColor());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<PieceDto> findAll() {
        final String sql = "select * from piece";
        List<PieceDto> pieces = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                pieces.add(
                        PieceDto.of(
                                resultSet.getString("position"),
                                resultSet.getString("color"),
                                resultSet.getString("type")
                        )
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pieces;
    }

    @Override
    public void update(Position source, Position target) {
        final String sql = "update piece set position = ? where position = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, target.getName());
            statement.setString(2, source.getName());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
