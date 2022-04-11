package chess.dao;

import chess.domain.piece.Team;
import chess.utils.DataAccessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BoardDaoImpl implements BoardDao {

    private final Connection connection;

    public BoardDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public int createBoard(Team team) {
        String sql = "insert into board (turn) values (?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, team.name());

            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            validResultSet(resultSet);
            return resultSet.getInt(1);

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException();
        }
    }

    @Override
    public void updateTurn(Team turn, int id) {
        String sql = "update board set turn = ? where id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, turn.name());
            preparedStatement.setInt(2, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException();
        }
    }

    @Override
    public Team findTurn(int id) {
        String sql = "select turn from board where id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            validResultSet(resultSet);
            return Team.of(resultSet.getString("turn"));

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException();
        }
    }

    private void validResultSet(ResultSet resultSet) throws SQLException {
        if (!resultSet.next()) {
            throw new SQLException("쿼리문 실행 결과가 존재하지 않습니다.");
        }
    }
}
