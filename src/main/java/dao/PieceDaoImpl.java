package dao;

import chess.domain.DBConnector;
import dto.NavigatorDto;
import dto.PieceDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PieceDaoImpl implements PieceDao {
    private static final DBConnector CONNECTOR = DBConnector.getInstance();

    private static class PieceDaoImplHolder {
        private static final PieceDao instance = new PieceDaoImpl();
    }

    public static PieceDao getInstance() {
        return PieceDaoImpl.PieceDaoImplHolder.instance;
    }

    @Override
    public int addPiece(PieceDto pieceDto) {
        String query = "INSERT INTO piece (position,kind_id,game_id,team_id) VALUES (?, ?, ?, ?)";
        int result;
        try (Connection con = CONNECTOR.getConnection();
             PreparedStatement pstmt = con.prepareStatement(query)) {
            pstmt.setString(1,pieceDto.getPosition());
            pstmt.setInt(2,pieceDto.getKindId());
            pstmt.setInt(3,pieceDto.getGameId());
            pstmt.setInt(4, pieceDto.getAliance().getTeamId());
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException("말의 정보를 생성할 수 없습니다.");
        }
        return result;
    }

    @Override
    public List<PieceDto> findByGameId(int gameId) {
        String query = "SELECT position, kind_id, team_id from piece WHERE game_id=?";
        List<PieceDto> pieceDtos = new ArrayList<>();

        try (Connection con = CONNECTOR.getConnection();
             PreparedStatement pstmt = con.prepareStatement(query)) {
            pstmt.setInt(1, gameId);
            ResultSet rs = pstmt.executeQuery();

            while(rs.next()){
                String position = rs.getString("position");
                int kindId = rs.getInt("kind_id");
                int teamId = rs.getInt("team_id");
                pieceDtos.add(new PieceDto(teamId,gameId,kindId,position));
            }
            if (!rs.next()) return pieceDtos;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException("말의 정보들을 받아올 수 업습니다.");
        }
        return pieceDtos;
    }

    @Override
    public int updatePiece(NavigatorDto navigatorDto) {
        String query = "UPDATE piece SET position=? WHERE game_id=? AND position=?";
        int result;

        try (Connection con = CONNECTOR.getConnection();
             PreparedStatement pstmt = con.prepareStatement(query)){
            pstmt.setString(1, navigatorDto.getEndPosition());
            pstmt.setInt(2, navigatorDto.getGameId());
            pstmt.setString(3, navigatorDto.getStartPosition());
            result = pstmt.executeUpdate();
        } catch (SQLException e){
            System.out.println(e.getMessage());
            throw new RuntimeException("말의 정보를 업데이트 할 수 없습니다.");
        }
        return result;
    }

    @Override
    public int deletePiece(PieceDto pieceDto) {
        String query = "DELETE FROM piece WHERE game_id=? AND position=?";
        int result;

        try (Connection con = CONNECTOR.getConnection();
             PreparedStatement pstmt = con.prepareStatement(query)){
            pstmt.setInt(1, pieceDto.getGameId());
            pstmt.setString(2, pieceDto.getPosition());
            result = pstmt.executeUpdate();
        } catch (SQLException e){
            System.out.println(e.getMessage());
            throw new RuntimeException("말을 삭제할 수 없습니다.");
        }
        return result;
    }
}
