package chess.dao;

import chess.dao.exception.DataAccessException;
import chess.dao.utils.JdbcConnector;
import chess.domain.board.Board;
import chess.service.dto.HistoryDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static chess.dao.sqls.HistorySql.INSERT_HISTORY;
import static chess.dao.sqls.HistorySql.SELECT_LAST_HISTORY_BY_ROUND;

public class HistoryDao {
    private static final int FIRST = 1;

    private HistoryDao() {
    }

    private static class HistoryDaoHolder {
        private static final HistoryDao INSTANCE = new HistoryDao();
    }

    public static HistoryDao getInstance() {
        return HistoryDaoHolder.INSTANCE;
    }

    public HistoryDto selectLastHistory(int round) {
        try (Connection connection = JdbcConnector.getConnection();
             PreparedStatement preparedStatement = createPreparedStatement(connection, SELECT_LAST_HISTORY_BY_ROUND, round);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            if (!resultSet.next()) {
                throw new DataAccessException("History를 찾을 수 없습니다.");
            }

            List<String> rows = new ArrayList<>();
            for (int i = 0; i < Board.BOUNDARY; i++) {
                rows.add(resultSet.getString("row" + i));
            }
            HistoryDto historyDTO = new HistoryDto();
            historyDTO.setRound(resultSet.getInt("round_id"));
            historyDTO.setTurn(resultSet.getInt("turn"));
            historyDTO.setRows(rows);

            return historyDTO;
        } catch (Exception e) {
            throw new DataAccessException(e);
        }
    }

    private PreparedStatement createPreparedStatement(Connection connection, String query, int round) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, round);
        preparedStatement.setInt(2, round);
        return preparedStatement;
    }


    public int insertHistory(HistoryDto historyDTO) {
        try (Connection connection = JdbcConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_HISTORY)) {

            int index = FIRST;
            preparedStatement.setInt(index++, historyDTO.getRound());
            for (String row : historyDTO.getRows()) {
                preparedStatement.setString(index++, row);
            }
            preparedStatement.setInt(index, historyDTO.getTurn());

            return preparedStatement.executeUpdate();

        } catch (Exception e) {
            throw new DataAccessException(e);
        }
    }
}
