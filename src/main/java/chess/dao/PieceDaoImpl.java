package chess.dao;

import chess.web.dto.PieceDto;
import chess.web.dto.PieceType;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PieceDaoImpl implements PieceDao {

    private static final String URL = "jdbc:mysql://localhost:3306/chess";
    private static final String USER = "user";
    private static final String PASSWORD = "password";

    @Override
    public void save(PieceDto pieceDto) {
        String sql = "insert into piece (id, piece_type) values (?, ?)";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, pieceDto.getId());
            PieceType pieceType = pieceDto.getPieceType();
            statement.setString(2, pieceType.getType());

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalArgumentException("기물의 위치는 중복될 수 없습니다.");
        }
    }

    @Override
    public Optional<PieceDto> findById(String id) {
        String sql = "select * from piece where piece.id = ?";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (!resultSet.next()) {
                return Optional.empty();
            }

            return Optional.of(generatePieceDto(resultSet));
        } catch (SQLException e) {
            throw new IllegalArgumentException("존재하지 않는 기물입니다.");
        }
    }

    @Override
    public void remove(String id) {
        String sql = "delete from piece where id = ?";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalArgumentException("기물 삭제가 불가능합니다.");
        }
    }

    @Override
    public List<PieceDto> findAll() {
        String sql = "select * from piece";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();

            return generatePieces(resultSet);
        } catch (SQLException e) {
            throw new IllegalStateException("기물 조회가 불가능합니다.");
        }
    }

    private List<PieceDto> generatePieces(ResultSet resultSet) throws SQLException {
        List<PieceDto> pieces = new ArrayList<>();
        while (resultSet.next()) {
            pieces.add(generatePieceDto(resultSet));
        }

        return pieces;
    }

    private PieceDto generatePieceDto(ResultSet resultSet) throws SQLException {
        String id = resultSet.getString("id");
        String pieceType = resultSet.getString("piece_type");

        return new PieceDto(id, PieceType.of(pieceType));
    }

    @Override
    public void removeAll() {
        String sql = "delete from piece";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalArgumentException("기물의 위치는 중복될 수 없습니다.");
        }
    }

    private Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
