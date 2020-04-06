package dao;

import db.DBConnection;
import vo.PieceVO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PieceDAO {
    public void addPiece(PieceVO pieceVO) throws SQLException {
        String query = "INSERT INTO piece(game_id, name, row, col) VALUES (?, ?, ?, ?)";
        PreparedStatement pstmt = getPreparedStatement(pieceVO, query);
        pstmt.executeUpdate();
    }

    private PreparedStatement getPreparedStatement(PieceVO pieceVO, String query) throws SQLException {
        PreparedStatement pstmt = DBConnection.getInstance().prepareStatement(query);
        pstmt.setInt(1, pieceVO.getGameId());
        pstmt.setString(2, pieceVO.getName());
        pstmt.setInt(3, pieceVO.getRow());
        pstmt.setString(4, pieceVO.getCol());
        return pstmt;
    }

    public ArrayList<PieceVO> findBy(int gameId) throws SQLException {
        String query = "SELECT * FROM piece WHERE game_id = ?";
        PreparedStatement pstmt = DBConnection.getInstance().prepareStatement(query);
        pstmt.setInt(1, gameId);
        ResultSet rs = pstmt.executeQuery();

        if (!rs.next()) {
            return null;
        }

        ArrayList<PieceVO> pieceVOs = getPieceVOS(rs);

        return pieceVOs;
    }

    private ArrayList<PieceVO> getPieceVOS(ResultSet resultSet) throws SQLException {
        ArrayList<PieceVO> pieceVOs = new ArrayList<>();
        while (resultSet.next()) {
            PieceVO pieceVO = new PieceVO(
                    resultSet.getInt("game_id"),
                    resultSet.getString("name"),
                    resultSet.getInt("row"),
                    resultSet.getString("col")
            );
            pieceVOs.add(pieceVO);
        }
        return pieceVOs;
    }
}
