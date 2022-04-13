package chess.dao;

import chess.model.piece.Piece;
import chess.model.position.Position;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class SquareDaoImpl implements SquareDao {

    private final DataSource dataSource;

    public SquareDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void save(Position position, Piece piece) {
        final String sql = "insert into square (position, team, symbol) values (?, ?, ?)";
        try (final Connection connection = dataSource.connection();
             final PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, position.getKey());
            statement.setString(2, piece.getTeam());
            statement.setString(3, piece.getSymbol());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("[ERROR] 데이터 저장 실패");
        }
    }

    public Map<String, String> find() {
        final String sql = "select position, team, symbol from square";
        try (final Connection connection = dataSource.connection();
             final PreparedStatement statement = connection.prepareStatement(sql);
             final ResultSet resultSet = statement.executeQuery()) {
            return createFrom(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException("[ERROR] 데이터 조회 실패");
        }
    }

    private Map<String, String> createFrom(ResultSet resultSet) throws SQLException {
        Map<String, String> squares = new HashMap<>();
        while (resultSet.next()) {
            squares.put(resultSet.getString("position"),
                    resultSet.getString("team") + "_" + resultSet.getString("symbol"));
        }
        return squares;
    }

    public void delete() {
        final String sql = "delete from square";
        try (final Connection connection = dataSource.connection();
             final PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("[ERROR] 데이터 삭제 실패");
        }
    }

    public void update(String position, Piece piece) {
        final String sql = "update square set team = ?, symbol = ? where position = ?";
        try (final Connection connection = dataSource.connection();
             final PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, piece.getTeam());
            statement.setString(2, piece.getSymbol());
            statement.setString(3, position);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("[ERROR] 데이터 최신화 실패");
        }
    }
}
