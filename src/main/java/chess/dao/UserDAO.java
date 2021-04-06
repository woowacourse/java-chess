package chess.dao;

import chess.dto.UserDTO;
import chess.dto.UsersDTO;
import chess.exception.DataAccessException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public final class UserDAO {

    public UsersDTO findByRoomId(final String roomId) {
        try (Connection connection = ConnectDB.getConnection();
             PreparedStatement statement = findUsersStatement(connection, roomId);
             ResultSet resultSet = statement.executeQuery()) {
            if (!resultSet.next()) {
                return null;
            }

            return new UsersDTO(
                    resultSet.getString("black_user"),
                    resultSet.getString("white_user")
            );
        } catch (SQLException e) {
            throw new DataAccessException("참가자 정보 load를 실패했습니다.");
        }
    }

    private PreparedStatement findUsersStatement(final Connection connection, final String roomId) throws SQLException {
        String query = "SELECT black.nickname AS black_user, white.nickname AS white_user " +
                "FROM room JOIN user as black on black.id = room.black_user " +
                "JOIN user as white on white.id = room.white_user " +
                "WHERE room.id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, roomId);
        return statement;
    }

    public int findUserIdByNickname(final String nickname) {
        try (Connection connection = ConnectDB.getConnection();
             PreparedStatement statement = findUserStatement(connection, nickname);
             ResultSet resultSet = statement.executeQuery()) {
            if (!resultSet.next()) {
                return 0;
            }

            return resultSet.getInt("id");
        } catch (SQLException e) {
            throw new DataAccessException("참가자 정보 load를 실패했습니다.");
        }
    }

    private PreparedStatement findUserStatement(final Connection connection, final String nickname) throws SQLException {
        String query = "SELECT id FROM user WHERE nickname = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, nickname);
        return statement;
    }

    public List<UserDTO> findAll() {
        String query = "SELECT * from user";

        List<UserDTO> users = new ArrayList<>();
        try (Connection connection = ConnectDB.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery();) {
            while (resultSet.next()) {
                users.add(new UserDTO(
                        resultSet.getInt("id"),
                        resultSet.getString("nickname")
                ));
            }
        } catch (SQLException e) {
            throw new DataAccessException("참가자 정보 load를 실패했습니다.");
        }

        return users;
    }

    public String findNicknameById(final int id) {
        try (Connection connection = ConnectDB.getConnection();
             PreparedStatement statement = findNicknameStatement(connection, id);
             ResultSet resultSet = statement.executeQuery()) {
            if (!resultSet.next()) {
                return null;
            }

            return resultSet.getString("nickname");
        } catch (SQLException e) {
            throw new DataAccessException("참가자 정보 load를 실패했습니다.");
        }
    }

    private PreparedStatement findNicknameStatement(final Connection connection, final int id) throws SQLException {
        String query = "SELECT nickname FROM user WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, id);
        return statement;
    }
}
