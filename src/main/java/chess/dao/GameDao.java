package chess.dao;

import chess.controller.web.dto.game.GameRequestDto;
import chess.controller.web.dto.game.GameResponseDto;

import java.sql.*;

public class GameDao {

    public Long saveGame(final GameRequestDto gameRequestDto) {
        final String query =
                "INSERT INTO game(room_name, white_username, black_username) VALUES (?, ?, ?)";

        try (Connection connection = ConnectionProvider.getConnection();
             PreparedStatement pstmt =
                     connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, gameRequestDto.getRoomName());
            pstmt.setString(2, gameRequestDto.getWhiteUsername());
            pstmt.setString(3, gameRequestDto.getBlackUsername());
            pstmt.executeLargeUpdate();

            ResultSet resultSet = pstmt.getGeneratedKeys();
            resultSet.next();
            return resultSet.getLong(1);
        } catch (SQLException e) {
            throw new IllegalStateException(e.getMessage(), e);
        }
    }

    public GameResponseDto findGameById(final Long gameId) {
        final String query =
                "SELECT * from game where id = ?";
        try (Connection connection = ConnectionProvider.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query);) {
            pstmt.setInt(1, gameId.intValue());
            try (ResultSet resultSet = pstmt.executeQuery()) {
                if (!resultSet.next()) {
                    return null;
                }
                return new GameResponseDto(
                        resultSet.getString("white_username"),
                        resultSet.getString("black_username"),
                        resultSet.getString("room_name"));
            }
        } catch (SQLException e) {
            throw new IllegalStateException(e.getMessage(), e);
        }
    }
}
