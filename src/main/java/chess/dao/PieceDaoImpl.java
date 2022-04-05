package chess.dao;

import chess.dto.PieceDto;
import chess.utils.DataAccessException;
import chess.utils.DatabaseUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PieceDaoImpl implements PieceDao {

    private final Connection connection;

    public PieceDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(List<PieceDto> pieces, int boardId) {
        String sql = "insert into piece (board_id, team, coordinate, type) values (?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            makeBatch(preparedStatement, pieces, boardId);
            int[] result = preparedStatement.executeBatch();
            DatabaseUtil.validExecute(result);

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException();
        }
    }

    private void makeBatch(PreparedStatement preparedStatement, List<PieceDto> pieces, int boardId)
            throws SQLException {
        for (PieceDto piece : pieces) {
            preparedStatement.setInt(1, boardId);
            preparedStatement.setString(2, piece.getTeam());
            preparedStatement.setString(3, piece.getPosition());
            preparedStatement.setString(4, piece.getType());

            preparedStatement.addBatch();
        }
    }

    @Override
    public void updatePosition(String from, String to) {
        String sql = "update piece set coordinate = ? where coordinate = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, to);
            preparedStatement.setString(2, from);

            int result = preparedStatement.executeUpdate();
            DatabaseUtil.validExecute(result);

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException();
        }
    }

    @Override
    public List<PieceDto> findByBoardId(int id) {
        String sql = "select team, coordinate, type from piece where board_id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            return asList(resultSet);

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException();
        }
    }

    private List<PieceDto> asList(ResultSet resultSet) throws SQLException {
        List<PieceDto> pieces = new ArrayList<>();
        while (resultSet.next()) {
            pieces.add(new PieceDto(
                    resultSet.getString("team"),
                    resultSet.getString("type"),
                    resultSet.getString("coordinate")
            ));
        }
        return pieces;
    }

    @Override
    public void delete(String position) {
        String sql = "delete from piece where coordinate = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, position);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException();
        }
    }
}
