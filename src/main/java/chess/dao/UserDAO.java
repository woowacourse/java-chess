package chess.dao;

import chess.dto.UserDTO;
import chess.dto.UsersDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    public UsersDTO findByRoomId(final String roomId) throws SQLException {
        String query = "SELECT black.nickname AS black_user, white.nickname AS white_user " +
                "FROM room JOIN user as black on black.id = room.black_user " +
                "JOIN user as white on white.id = room.white_user " +
                "WHERE room.id = ?";
        Connection connection = ConnectDB.getConnection();
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, roomId);
        ResultSet resultSet = statement.executeQuery();

        UsersDTO usersDTO = null;
        try (connection; statement; resultSet) {
            if (!resultSet.next()) {
                return null;
            }

            usersDTO = new UsersDTO(
                    resultSet.getString("black_user"),
                    resultSet.getString("white_user")
            );
        } catch (Exception e) {

        }

        return usersDTO;
    }

    public int findUserIdByNickname(final String nickname) throws SQLException {
        String query = "SELECT id FROM user WHERE nickname = ?";
        Connection connection = ConnectDB.getConnection();
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, nickname);
        ResultSet resultSet = statement.executeQuery();

        int id = 0;
        try (connection; statement; resultSet) {
            if (!resultSet.next()) {
                return 0;
            }

            id = resultSet.getInt("id");
        } catch (Exception e) {

        }

        return id;
    }

    public List<UserDTO> findAll() throws SQLException {
        String query = "SELECT * from user";
        Connection connection = ConnectDB.getConnection();
        PreparedStatement statement = connection.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery();

        List<UserDTO> users = new ArrayList<>();

        try (connection; statement; resultSet) {
            while (resultSet.next()) {
                users.add(new UserDTO(
                        resultSet.getInt("id"),
                        resultSet.getString("nickname")
                ));
            }
        } catch (Exception e) {

        }

        return users;
    }

    public String findNicknameById(final int id) throws SQLException {
        String query = "SELECT nickname FROM user WHERE id = ?";
        Connection connection = ConnectDB.getConnection();
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();

        String nickname = "";
        try (connection; statement; resultSet) {
            if (!resultSet.next()) {
                return null;
            }

            nickname = resultSet.getString("nickname");
        } catch (Exception e) {

        }

        return nickname;
    }
}
