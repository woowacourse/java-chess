package chess.Dao;

import chess.domain.position.Position;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

public class MoveDao {

    private final Connection connection;

    public MoveDao() {
        this.connection = MySQLConnectionSetting.getInstance().getConnection();
    }

    public void addMove(final Position from, final Position to) {
        String query = "insert into move (start, end) values (?,?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, from.toString());
            statement.setString(2, to.toString());
            statement.executeUpdate();
        } catch (SQLException exception) {
            System.err.println("SQL 이동 추가 오류: " + exception.getMessage());
            exception.printStackTrace();
        }
    }

    public Map<Position, Position> getMoves() {
        String query = "select * from move";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            Map<Position, Position> moves = new LinkedHashMap<>();
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String from = resultSet.getString("start");
                String to = resultSet.getString("end");
                moves.put(Position.of(from), Position.of(to));
            }
            return moves;
        } catch (SQLException exception) {
            System.err.println("SQL 이동 불러오기 오류: " + exception.getMessage());
            exception.printStackTrace();
            return null;
        }
    }

    public void deleteAll() {
        String query = "delete from move";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.executeUpdate();
        } catch (SQLException exception) {
            System.err.println("SQL 이동 삭제 오류: " + exception.getMessage());
            exception.printStackTrace();
        }
    }
}
