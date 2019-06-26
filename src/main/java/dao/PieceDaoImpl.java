package dao;

import dto.PieceDto;
import dto.UserDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class PieceDaoImpl implements PieceDao {
    private static final DBConnector CONNECTOR = DBConnector.getInstance();

    private static class PieceDaoImplHolder {
        private static final PieceDao instance = new PieceDaoImpl();
    }

    public static PieceDao getInstance() {
        return PieceDaoImpl.PieceDaoImplHolder.instance;
    }

    @Override
    public int addPiece(String position, int kindId, int gameId) {
        String query = "INSERT INTO piece (position,kind_id,game_id) VALUES (?, ?, ?)";
        int result;
        try (Connection con = CONNECTOR.getConnection();
             PreparedStatement pstmt = con.prepareStatement(query)) {
            pstmt.setString(1,position);
            pstmt.setInt(2,kindId);
            pstmt.setInt(3,gameId);
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException("말의 정보를 생성할 수 없습니다.");
        }
        return result;
    }

    @Override
    public PieceDto findByGameId(int gameId) {
        String query = "SELECT position, kind.name FROM piece JOIN kind ON kind.id=piece.kind_id WHERE game_id=?";
        PieceDto pieceDto = null;

        try (Connection con = CONNECTOR.getConnection();
             PreparedStatement pstmt = con.prepareStatement(query)) {
            pstmt.setInt(1, gameId);
            ResultSet rs = pstmt.executeQuery();
            Map<String,String> pieceMap = new HashMap<>();
            while(rs.next()){
                pieceMap.put(rs.getString("position"),rs.getString("name"));;
            }
            if (!rs.next()) return pieceDto;

            pieceDto = new PieceDto(pieceMap);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException("말의 정보들을 받아올 수 업습니다.");
        }

        return pieceDto;
    }

    @Override
    public int deletePieceByPosition(String position) {
        String query = "DELETE FROM piece WHERE position=?";
        int result;

        try (Connection con = CONNECTOR.getConnection();
             PreparedStatement pstmt = con.prepareStatement(query)){
            pstmt.setString(1,position);
            result = pstmt.executeUpdate();
        } catch (SQLException e){
            System.out.println(e.getMessage());
            throw new RuntimeException("말을 삭제할 수 없습니다.");
        }
        return result;
    }
}
