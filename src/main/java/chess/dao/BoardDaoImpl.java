package chess.dao;

import chess.domain.piece.Team;
import chess.utils.DatabaseUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BoardDaoImpl implements BoardDao {

    private final Connection connection;

    public BoardDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void createBoard(Team team) {
        String sql = "insert into board (turn) values (?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, team.name());

            int result = preparedStatement.executeUpdate();
            DatabaseUtil.validExecute(result);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateTurn(Team turn, int id) {
        String sql = "update board set turn = ? where id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, turn.name());
            preparedStatement.setInt(2, id);

            int result = preparedStatement.executeUpdate();
            DatabaseUtil.validExecute(result);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
