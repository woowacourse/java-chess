package chess.domain.board.repository;

import chess.dao.BoardDao;
import chess.dao.MySqlManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class BoardRepository {

    public void save(final String position) {

        final String query = "INSERT INTO BOARD(position) VALUES (?)";

        try (final Connection connection = MySqlManager.establishConnection();
             final PreparedStatement preparedStatement = connection.prepareStatement(query);) {

            preparedStatement.setString(1, position);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.err.println("DB 저장 오류: " + e.getMessage());
        }
    }

    public Optional<BoardDao> findById(final Long id) {
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

    private Optional<BoardDao> mappingToBoardDaoFrom(final ResultSet resultSet) {
        try {
            if (resultSet.next()) {
                return Optional.of(new BoardDao(
                        resultSet.getLong("BOARD_ID"),
                        resultSet.getString("POSITION")
                ));
            }
        } catch (SQLException e) {
            System.err.println("DB 조회 오류: " + e.getMessage());
        }

        return Optional.empty();
    }

    public void modifyById(final Long boardId, final String modifyingPosition) {
        final String query = "UPDATE BOARD SET POSITION = ? WHERE BOARD_ID = ?";

        try (final Connection connection = MySqlManager.establishConnection();
             final PreparedStatement preparedStatement = connection.prepareStatement(query);) {

            preparedStatement.setString(1, modifyingPosition);
            preparedStatement.setLong(2, boardId);
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
