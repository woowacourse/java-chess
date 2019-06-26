package dao;

import dto.GameDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GameDaoImpl implements GameDao {
    private static final DBConnector CONNECTOR = DBConnector.getInstance();
    private static final String WHITE_TURN = "2";

    private static class GameDAOImplHolder {
        private static final GameDao instance = new GameDaoImpl();
    }

    public static GameDao getInstance() {
        return GameDAOImplHolder.instance;
    }

    @Override
    public int addGame() {
        String query = "INSERT INTO game (team_id) VALUES "+WHITE_TURN;
        int result;

        try (Connection con = CONNECTOR.getConnection();
             PreparedStatement pstmt = con.prepareStatement(query)) {
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException("게임을 생성할 수 없습니다.");
        }
        return result;
    }

    @Override
    public GameDto findById(int id) {
        String query = "SELECT * FROM gmae WHERE id = ?";
        GameDto game = null;

        try (Connection con = CONNECTOR.getConnection();
             PreparedStatement pstmt = con.prepareStatement(query)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (!rs.next()) return game;

            game = new GameDto(rs.getInt("id"),
                    rs.getBoolean("isEnd"),
                    rs.getInt("team_id"));

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException("게임 정보를 받아올 수 없습니다.");
        }

        return game;
    }

    @Override
    public int updateGame(GameDto gameDto) {
        String query = "UPDATE game SET team_id=?, isEnd=?  WHERE id=?";
        int result;

        try (Connection con = CONNECTOR.getConnection();
             PreparedStatement pstmt = con.prepareStatement(query)){
            pstmt.setInt(1,gameDto.getTurn().getTeamId());
            pstmt.setBoolean(2,gameDto.isEnd());
            pstmt.setInt(3,gameDto.getId());
            result = pstmt.executeUpdate();
        } catch (SQLException e){
            System.out.println(e.getMessage());
            throw new RuntimeException("게임 정보를 업데이트 할 수 없습니다.");
        }
        return result;
    }

    @Override
    public int deleteGameByid(int id) {
        String query = "DELETE FROM game WHERE id=?";
        int result;

        try (Connection con = CONNECTOR.getConnection();
             PreparedStatement pstmt = con.prepareStatement(query)){
            pstmt.setInt(1,id);
            result = pstmt.executeUpdate();
        } catch (SQLException e){
            System.out.println(e.getMessage());
            throw new RuntimeException("게임을 삭제할 수 없습니다.");
        }
        return result;
    }
}
