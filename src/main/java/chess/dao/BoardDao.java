package chess.dao;

import chess.domain.board.service.dto.BoardModifyRequest;
import chess.domain.board.service.dto.BoardRegisterRequest;
import chess.domain.board.service.dto.BoardSearchResponse;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class BoardDao {

    public Long save(final BoardRegisterRequest boardRegisterRequest) {

        final String query = "INSERT INTO BOARD(POSITION, TURN) VALUES (?, ?)";

        try (final Connection connection = MySqlManager.establishConnection();
             final PreparedStatement preparedStatement = connection.prepareStatement(query,
                                                                                     Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, boardRegisterRequest.position());
            preparedStatement.setString(2, boardRegisterRequest.turn());
            preparedStatement.executeUpdate();

            return generateKey(preparedStatement);
        } catch (SQLException e) {
            System.err.println("DB 저장 오류: " + e.getMessage());
        }

        throw new IllegalStateException("DB 저장 오류입니다.");
    }

    private Long generateKey(final PreparedStatement preparedStatement) throws SQLException {
        try (final ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
            if (resultSet.next()) {
                return resultSet.getLong(1);
            }
        }

        throw new IllegalStateException("DB 저장 오류입니다.");
    }

    public Optional<BoardSearchResponse> findById(final Long id) {
        final String query = "SELECT * FROM BOARD WHERE BOARD_ID = ?";

        try (final Connection connection = MySqlManager.establishConnection();
             final PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setLong(1, id);
            final ResultSet resultSet = preparedStatement.executeQuery();

            return mappingToDtoFrom(resultSet);

        } catch (SQLException e) {
            System.err.println("DB 조회 오류: " + e.getMessage());
        }

        return Optional.empty();
    }

    private Optional<BoardSearchResponse> mappingToDtoFrom(final ResultSet resultSet) {
        try {
            if (resultSet.next()) {
                return Optional.of(new BoardSearchResponse(
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

    public List<BoardSearchResponse> findAll() {
        final String query = "SELECT * FROM BOARD";

        try (final Connection connection = MySqlManager.establishConnection();
             final PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            final ResultSet resultSet = preparedStatement.executeQuery();

            return mappingToBoardDtosFrom(resultSet);
        } catch (SQLException e) {
            System.err.println("DB 조회 오류: " + e.getMessage());
        }

        return Collections.emptyList();
    }

    private List<BoardSearchResponse> mappingToBoardDtosFrom(final ResultSet resultSet) {
        try {
            List<BoardSearchResponse> boardSearchResponses = new ArrayList<>();

            while (resultSet.next()) {
                final BoardSearchResponse boardSearchResponse = new BoardSearchResponse(
                        resultSet.getLong("BOARD_ID"),
                        resultSet.getString("POSITION"),
                        resultSet.getString("TURN")
                );

                boardSearchResponses.add(boardSearchResponse);
            }

            return boardSearchResponses;
        } catch (SQLException e) {
            System.err.println("DB 조회 오류: " + e.getMessage());
        }
        return null;
    }

    public void modifyById(final BoardModifyRequest boardModifyRequest) {
        final String query = "UPDATE BOARD SET POSITION = ?, TURN = ? WHERE BOARD_ID = ?";

        try (final Connection connection = MySqlManager.establishConnection();
             final PreparedStatement preparedStatement = connection.prepareStatement(query);) {

            preparedStatement.setString(1, boardModifyRequest.position());
            preparedStatement.setString(2, boardModifyRequest.turn());
            preparedStatement.setLong(3, boardModifyRequest.id());
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
