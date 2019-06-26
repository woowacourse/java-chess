package dao;

import dto.GameDto;
import dto.UserDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoImpl implements UserDao {
    private static final DBConnector CONNECTOR = DBConnector.getInstance();

    private static class UserDaoImplHolder {
        private static final UserDao instance = new UserDaoImpl();
    }

    public static UserDao getInstance() {
        return UserDaoImplHolder.instance;
    }

    @Override
    public int addUser(String userName,int teamId, int gameId) {
        String query = "INSERT INTO user (name,team_id,game_id) VALUES (?, ?, ?)";
        int result;

        try (Connection con = CONNECTOR.getConnection();
             PreparedStatement pstmt = con.prepareStatement(query)) {
            pstmt.setString(1,userName);
            pstmt.setInt(2,teamId);
            pstmt.setInt(3,gameId);
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException("유저 정보를 생성할 수 없습니다.");
        }
        return result;
    }

    @Override
    public UserDto findByGameId(int gameId) {
        String query = "SELECT * FROM user WHERE game_id=?";
        UserDto user = null;

        try (Connection con = CONNECTOR.getConnection();
             PreparedStatement pstmt = con.prepareStatement(query)) {
            pstmt.setInt(1, gameId);
            ResultSet rs = pstmt.executeQuery();
            List<>
            if (!rs.next()) return user;

            user = new UserDto(rs.getString("name"),rs.getInt("team_id"));

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException("유저 정보를 받아올 수 업습니다.");
        }

        return user;
    }

    @Override
    public int deleteUserByGameId(int gameId) {
        String query = "DELETE FROM user WHERE game_id=?";
        int result;

        try (Connection con = CONNECTOR.getConnection();
             PreparedStatement pstmt = con.prepareStatement(query)){
            pstmt.setInt(1,gameId);
            result = pstmt.executeUpdate();
        } catch (SQLException e){
            System.out.println(e.getMessage());
            throw new RuntimeException("유저를 삭제할 수 없습니다.");
        }
        return result;
    }
}
