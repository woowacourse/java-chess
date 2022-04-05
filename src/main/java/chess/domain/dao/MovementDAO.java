package chess.domain.dao;

import chess.domain.position.Position;
import chess.view.OutputView;
import chess.web.connection.DBConnectionUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public final class MovementDAO {

    public void addMoveCommand(Movement movement) throws SQLException {
        Connection connection = DBConnectionUtils.getConnection();
        String query = "INSERT INTO MOVEMENT (game_id, source_position, target_position) VALUES (?, ?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, movement.getGameId());
            statement.setString(2, movement.getSource());
            statement.setString(3, movement.getTarget());
            statement.executeUpdate();
        } catch (SQLException e) {
            OutputView.printErrorMessage(e.getMessage());
        }
    }

    public List<Movement> findMovementByGameId(String gameId) throws SQLException {
        Connection connection = DBConnectionUtils.getConnection();
        String query = "SELECT * FROM CHESS_GAME cg JOIN MOVEMENT m on cg.id = m.game_id WHERE cg.is_end=false AND cg.id=(?)";
        List<Movement> movements = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, gameId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
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
