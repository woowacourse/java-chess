package dao;

import dto.GameDTO;
import chess.domain.DBConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GameDAOImpl implements GameDAO {
    private static final DBConnector CONNECTOR = DBConnector.getInstance();

    private static class GameDAOImplHolder {
        private static final GameDAO instance = new GameDAOImpl();
    }

    public static GameDAO getInstance() {
        return GameDAOImplHolder.instance;
    }

    @Override
    public GameDTO findById(int id) {
        String query = "SELECT * FROM gmae WHERE id = ?";
        GameDTO game = null;

        try (Connection con = CONNECTOR.getConnection();
             PreparedStatement pstmt = con.prepareStatement(query)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (!rs.next()) return game;

            game = new GameDTO(rs.getInt("id"),
                    rs.getBoolean("isEnd"),
                    rs.getInt("team_id"));

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException("게임 정보를 받아올 수 없습니다.");
        }

        return game;
    }

    @Override
    public int addGame() {
        String query = "INSERT INTO game (team_id) VALUES 2";
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
    public int updateGame(GameDTO gameDTO) {
        String query = "UPDATE game SET team_id=?, isEnd=?  WHERE id=?";
        int result;

        try (Connection con = CONNECTOR.getConnection();
             PreparedStatement pstmt = con.prepareStatement(query)){
            pstmt.setInt(1,gameDTO.getTurn().getTeamId());
            pstmt.setBoolean(2,gameDTO.isEnd());
            pstmt.setInt(3,gameDTO.getId());
            result = pstmt.executeUpdate();
        } catch (SQLException e){
            System.out.println(e.getMessage());
            throw new RuntimeException("게임 정보를 업데이트 할 수 없습니다.");
        }
        return result;
    }

    @Override
    public int deleteGame(int id) {
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
