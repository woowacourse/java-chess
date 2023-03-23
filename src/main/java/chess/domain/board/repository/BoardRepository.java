package chess.domain.board.repository;

import chess.dao.BoardModifyDao;
import chess.dao.BoardRegisterDao;
import chess.dao.BoardSearchDao;
import chess.dao.MySqlManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class BoardRepository {

    public void save(final BoardRegisterDao boardRegisterDao) {

        final String query = "INSERT INTO BOARD(POSITION, TURN) VALUES (?, ?)";

        try (final Connection connection = MySqlManager.establishConnection();
             final PreparedStatement preparedStatement = connection.prepareStatement(query);) {

            preparedStatement.setString(1, boardRegisterDao.position());
            preparedStatement.setString(2, boardRegisterDao.turn());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.err.println("DB 저장 오류: " + e.getMessage());
        }
    }

    public Optional<BoardSearchDao> findById(final Long id) {
        final String query = "SELECT * FROM BOARD WHERE BOARD_ID = ?";

        try (final Connection connection = MySqlManager.establishConnection();
             final PreparedStatement preparedStatement = connection.prepareStatement(query);) {

            preparedStatement.setLong(1, id);
            final ResultSet resultSet = preparedStatement.executeQuery();

            return mappingToBoardDaoFrom(resultSet);

        } catch (SQLException e) {
            System.err.println("DB 조회 오류: " + e.getMessage());
        }

        return Optional.empty();
    }

    private Optional<BoardSearchDao> mappingToBoardDaoFrom(final ResultSet resultSet) {
        try {
            if (resultSet.next()) {
                return Optional.of(new BoardSearchDao(
                        resultSet.getLong("BOARD_ID"),
                        resultSet.getString("POSITION"),
                        resultSet.getString("TURN")
                ));
            }
        } catch (SQLException e) {
            System.err.println("DB 조회 오류: " + e.getMessage());
        }

        return Optional.empty();
    }

    public void modifyById(final BoardModifyDao boardModifyDao) {
        final String query = "UPDATE BOARD SET POSITION = ?, TURN = ? WHERE BOARD_ID = ?";

        try (final Connection connection = MySqlManager.establishConnection();
             final PreparedStatement preparedStatement = connection.prepareStatement(query);) {

            preparedStatement.setString(1, boardModifyDao.position());
            preparedStatement.setString(2, boardModifyDao.turn());
            preparedStatement.setLong(3, boardModifyDao.id());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.err.println("DB 수정 오류: " + e.getMessage());
        }
    }

    public void deleteById(final Long boardId) {
        final String query = "DELETE FROM BOARD WHERE BOARD_ID = ?";

        try (final Connection connection = MySqlManager.establishConnection();
             final PreparedStatement preparedStatement = connection.prepareStatement(query);) {

            preparedStatement.setLong(1, boardId);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.err.println("DB 삭제 오류: " + e.getMessage());
        }
    }
}
