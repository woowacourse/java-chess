package chess.dao;

import chess.Team;
import chess.Turn;
import chess.utils.JdbcConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class BoardDaoImpl implements BoardDao {

    @Override
    public Optional<Turn> findTurnById(Long id) {
        final String query = "SELECT (turn) from board where id = ?";
        try (
                Connection connection = JdbcConnector.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)
        ) {
            statement.setLong(1, id);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                String turn = result.getString("turn");
                return Optional.of(new Turn(Team.from(turn)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public void updateTurnById(Long id, String newTurn) {
        final String query = "UPDATE board set turn = ? where id = ?";
        try (
                Connection connection = JdbcConnector.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)
        ) {
            statement.setString(1, newTurn);
            statement.setLong(2, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

//    @Override
//    public Optional<Board> findById(Long id) {
//        final String query = "SELECT * from board where id = ?";
//        try (
//                Connection connection = JdbcConnector.getConnection();
//                PreparedStatement statement = connection.prepareStatement(query)
//        ) {
//            statement.setLong(1, id);
//            ResultSet result = statement.executeQuery();
//            if (!result.next()) {
//                Optional.empty();
//            }
//            Long boardId = result.getLong("id");
//            String turn = result.getString("turn");
//
//            return Optional.of()
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
}
