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
        String query = "SELECT * from piece WHERE game_id=?";
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
    public PieceDto findByPosition(PieceDto pieceDto) {
        String query = "SELECT * from piece WHERE game_id=? AND position=?";
        PieceDto result = null;

        try (Connection con = CONNECTOR.getConnection();
             PreparedStatement pstmt = con.prepareStatement(query)){
            pstmt.setInt(1, pieceDto.getGameId());
            pstmt.setString(2, pieceDto.getPosition());
            ResultSet rs = pstmt.executeQuery();

            if (!rs.next()) return result;

            int teamId = rs.getInt("team_id");
            int gameId = rs.getInt("game_id");
            int kindId = rs.getInt("kind_id");
            String position = rs.getString("position");

            result = new PieceDto(teamId, gameId, kindId, position);
            rs.close();
        } catch (SQLException e){
            System.out.println(e.getMessage());
            throw new RuntimeException("말의 정보를 받아올 수 없습니다.");
        }
        return result;
    }

    @Override
    public int updatePiece(NavigatorDto navigatorDto) {
        String query = "UPDATE piece SET position=? WHERE game_id=? AND position=?";
        int result;

        int gameId = navigatorDto.getGameId();
        String start = navigatorDto.getStartPosition();
        String end = navigatorDto.getEndPosition();

        PieceDto pieceDto = new PieceDto(0, gameId, 0, end);

        if (findByPosition(pieceDto) != null) {
            deletePiece(pieceDto);
        }

        try (Connection con = CONNECTOR.getConnection();
             PreparedStatement pstmt = con.prepareStatement(query)){
            pstmt.setString(1, end);
            pstmt.setInt(2, gameId);
            pstmt.setString(3, start);
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
