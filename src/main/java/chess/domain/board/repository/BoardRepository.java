package chess.domain.board.repository;

import chess.dao.BoardModifyDao;
import chess.dao.BoardRegisterDao;
import chess.dao.BoardSearchDao;
import chess.dao.MySqlManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class BoardRepository {

    public Long save(final BoardRegisterDao boardRegisterDao) {

        final String query = "INSERT INTO BOARD(POSITION, TURN) VALUES (?, ?)";

        try (final Connection connection = MySqlManager.establishConnection();
             final PreparedStatement preparedStatement = connection.prepareStatement(query,
                                                                                     Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, boardRegisterDao.position());
            preparedStatement.setString(2, boardRegisterDao.turn());
            preparedStatement.executeUpdate();

            return generateKey(preparedStatement);
        } catch (SQLException e) {
            System.err.println("DB 저장 오류: " + e.getMessage());
        }

        throw new IllegalStateException("DB 저장 오류입니다.");
    }

    private long generateKey(final PreparedStatement preparedStatement) throws SQLException {
        try (final ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
            if (resultSet.next()) {
                return resultSet.getLong(1);
            }
        }

        throw new IllegalStateException("DB 저장 오류입니다.");
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

    public List<BoardSearchDao> findAll() {
        final String query = "SELECT * FROM BOARD";

        try (final Connection connection = MySqlManager.establishConnection();
             final PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            final ResultSet resultSet = preparedStatement.executeQuery();

            List<BoardSearchDao> boardSearchDaos = mappingToBoardDaosFrom(resultSet);
            if (boardSearchDaos != null) return boardSearchDaos;

        } catch (SQLException e) {
            System.err.println("DB 조회 오류: " + e.getMessage());
        }

        return Collections.emptyList();
    }

    private List<BoardSearchDao> mappingToBoardDaosFrom(final ResultSet resultSet) {
        try {
            List<BoardSearchDao> boardSearchDaos = new ArrayList<>();

            while (resultSet.next()) {
                final BoardSearchDao boardSearchDao = new BoardSearchDao(
                        resultSet.getLong("BOARD_ID"),
                        resultSet.getString("POSITION"),
                        resultSet.getString("TURN")
                );

                boardSearchDaos.add(boardSearchDao);
            }

            return boardSearchDaos;
        } catch (SQLException e) {
            System.err.println("DB 조회 오류: " + e.getMessage());
        }
        return null;
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
