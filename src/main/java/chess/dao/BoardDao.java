package chess.dao;

import chess.domain.board.Position;
import chess.domain.piece.Piece;
import chess.dto.BoardFindAllResponse;
import chess.dto.BoardFindResponse;
import chess.dto.BoardSaveRequest;
import chess.dto.BoardUpdateRequest;
import chess.utils.JdbcConnector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class BoardDao {

    public void save(BoardSaveRequest boardSaveRequest) {
        String sql = "insert into board (game_number, position, color, piece) values (?,?,?,?)";
        try (Connection connection = JdbcConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, boardSaveRequest.getGameNumber());
            statement.setString(2, boardSaveRequest.getPosition().getStringValue());
            statement.setString(3, boardSaveRequest.getPiece().getColor());
            statement.setString(4, boardSaveRequest.getPiece().getType());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateByPosition(BoardUpdateRequest boardUpdateRequest) {
        Position nextPosition = boardUpdateRequest.getNextPosition();
        Position previousPosition = boardUpdateRequest.getPreviousPosition();
        int gameNumber = boardUpdateRequest.getGameNumber();

        if (findByPositionAndGameNumber(nextPosition, gameNumber) == null) {
            save(new BoardSaveRequest(
                    nextPosition, findByPositionAndGameNumber(previousPosition, gameNumber).getPiece(), gameNumber));
            return;
        }

        String sql = "update board set color = ?, piece = ? where position = ? and game_number = ?";
        try (Connection connection = JdbcConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            Piece piece = findByPositionAndGameNumber(previousPosition, gameNumber).getPiece();
            statement.setString(1, piece.getColor());
            statement.setString(2, piece.getType());
            statement.setString(3, nextPosition.getStringValue());
            statement.setInt(4, gameNumber);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public BoardFindResponse findByPositionAndGameNumber(Position position, int gameNumber) {
        final String sql = "select * from board where position = ? and game_number = ?";
        try (Connection connection = JdbcConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, position.getStringValue());
            statement.setInt(2, gameNumber);
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                return null;
            }
            return new BoardFindResponse(
                    resultSet.getString("color"),
                    resultSet.getString("piece")
            );
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public BoardFindAllResponse findAllByGameNumber(int gameNumber) {
        final String sql = "select * from board where game_number = ?";
        try (Connection connection = JdbcConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, gameNumber);
            ResultSet resultSet = statement.executeQuery();

            Map<String, BoardFindResponse> value = new HashMap<>();
            while (resultSet.next()) {
                BoardFindResponse boardFindResponse = new BoardFindResponse(
                        resultSet.getString("color"),
                        resultSet.getString("piece"));
                value.put(resultSet.getString("position"), boardFindResponse);
            }
            return new BoardFindAllResponse(value);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void delete(Position position, int gameNumber) {
        String sql = "delete from board where position = ? and game_number = ?";
        try (Connection connection = JdbcConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, position.getStringValue());
            statement.setInt(2, gameNumber);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteAll() {
        String sql = "delete from board where 1";
        try (Connection connection = JdbcConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteAllByGameNumber(int gameNumber) {
        String sql = "delete from board where game_number = ?";
        try (Connection connection = JdbcConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, gameNumber);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
