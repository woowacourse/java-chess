package chess.web.dao;

import chess.web.dto.PieceDto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PieceDaoImpl implements PieceDao {

    private final Connection connection;

    public PieceDaoImpl() {
        connection = DbConnector.getConnection();
    }

    @Override
    public void save(PieceDto pieceDto) {
        final String sql = "insert into piece (piece_type, position, color) values (?, ?, ?)";
        try {
            final PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, pieceDto.getPieceType());
            statement.setString(2, pieceDto.getPosition());
            statement.setString(3, pieceDto.getColor());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalArgumentException("해당 기물을 저장할 수 없습니다.");
        }
    }

    @Override
    public void update(PieceDto pieceDto) {
        final String sql = "update piece set piece_type=?, position=?, color=? where position=?";
        try {
            final PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, pieceDto.getPieceType());
            statement.setString(2, pieceDto.getPosition());
            statement.setString(3, pieceDto.getColor());
            statement.setString(4, pieceDto.getPosition());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalArgumentException("해당 기물을 수정할 수 없습니다.");
        }
    }

    @Override
    public List<PieceDto> selectAll() {
        final String sql = "select * from piece";
        try {
            final PreparedStatement statement = connection.prepareStatement(sql);
            final ResultSet resultSet = statement.executeQuery();
            return toPieceDtos(resultSet);
        } catch (SQLException e) {
            throw new IllegalArgumentException("기물을 가져올 수 없습니다.");
        }
    }

    private List<PieceDto> toPieceDtos(ResultSet resultSet) throws SQLException {
        final List<PieceDto> pieces = new ArrayList<>();
        while (resultSet.next()) {
            pieces.add(new PieceDto(
                    resultSet.getString("piece_type"),
                    resultSet.getString("position"),
                    resultSet.getString("color")
            ));
        }
        return pieces;
    }

    @Override
    public void deleteAll() {
        final String sql = "delete from piece";
        try {
            final PreparedStatement statement = connection.prepareStatement(sql);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalArgumentException("해당 기물을 삭제할 수 없습니다.");
        }
    }
}
