package chess.dao;

import chess.model.piece.Piece;
import chess.model.position.Position;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SquareDaoImpl implements SquareDao {

    private final DataSource dataSource;

    public SquareDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void save(Position position, Piece piece) {
        if (hasPiece(position)) {
            update(position, piece);
            return;
        }
        insert(position, piece);
    }

    private void update(Position position, Piece piece) {
        final String sql = "update square set team = ?, symbol = ? where position = ?";
        executeStatement(sql, List.of(piece.getTeam(), piece.getSymbol(), position.getKey()));
    }

    private void insert(Position position, Piece piece) {
        final String sql = "insert into square (position, team, symbol) values (?, ?, ?)";
        executeStatement(sql, List.of(position.getKey(), piece.getTeam(), piece.getSymbol()));
    }

    private boolean hasPiece(Position position) {
        final String sql = "select count(position) as cnt from square where position = ?";
        int count = 0;
        try (final PreparedStatement statement = dataSource.connection().prepareStatement(sql)) {
            statement.setString(1, position.getKey());
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            count = resultSet.getInt("cnt");
        } catch (SQLException e) {
            throw new RuntimeException("[ERROR] 데이터 조회 실패");
        }
        return count == 1;
    }

    public Map<String, String> find() {
        final String sql = "select position, team, symbol from square";
        try (final PreparedStatement statement = dataSource.connection().prepareStatement(sql);
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
        executeStatement(sql, new ArrayList<>());
    }

    private void executeStatement(String sql, List<String> conditions) {
        try (final PreparedStatement statement = dataSource.connection().prepareStatement(sql)) {
            setQuery(statement, conditions);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("[ERROR] 데이터 삭제 실패");
        }
    }

    private void setQuery(PreparedStatement statement, List<String> conditions) throws SQLException {
        for (int index = 0; index < conditions.size(); index++) {
            statement.setString(index + 1, conditions.get(index));
        }
    }
}
