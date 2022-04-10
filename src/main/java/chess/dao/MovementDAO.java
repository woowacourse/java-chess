package chess.dao;

import chess.domain.position.Position;
import chess.view.OutputView;
import chess.utils.DBConnectionUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public final class MovementDAO {

    public int addMoveCommand(final Movement movement) throws SQLException {
        String query = "INSERT INTO movement (game_id, source_position, target_position, turn)"
                + "SELECT ?, ?, ?, ?"
                + "FROM DUAL "
                + "WHERE (SELECT turn FROM movement WHERE game_id = ? ORDER BY created_at DESC LIMIT 1) = ?"
                + "   OR (SELECT turn FROM movement WHERE game_id = ? ORDER BY created_at DESC LIMIT 1) IS NULL;";
        Connection connection = DBConnectionUtils.getConnection();
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, movement.getGameId());
        statement.setString(2, movement.getSource());
        statement.setString(3, movement.getTarget());
        statement.setString(4, movement.getTeam().name());
        statement.setString(5, movement.getGameId());
        statement.setString(6, movement.getTeam().changeTeam().name());
        statement.setString(7, movement.getGameId());
        try {
            return statement.executeUpdate();
        } catch (SQLException e) {
            OutputView.printErrorMessage(e.getMessage());
        }
        return 1;
    }

    public List<Movement> findMovementByGameId(final String gameId) throws SQLException {
        Connection connection = DBConnectionUtils.getConnection();
        String query = "SELECT * FROM CHESS_GAME cg JOIN MOVEMENT m on cg.id = m.game_id WHERE cg.is_end=false AND cg.id=(?) ORDER BY m.created_at";
        List<Movement> movements = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, gameId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                String source = rs.getString("m.source_position");
                String target = rs.getString("m.target_position");
                movements.add(new Movement(Position.of(source), Position.of(target)));
            }
        } catch (SQLException e) {
            OutputView.printErrorMessage(e.getMessage());
        }
        DBConnectionUtils.closeConnection(connection);
        return movements;
    }
}
