package dao;

import db.DBConnection;
import vo.PieceVo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PieceDao {
    public void addPiece(PieceVo pieceVO) throws SQLException {
        String query = "INSERT INTO piece(game_id, name, row, col) VALUES (?, ?, ?, ?)";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, pieceVO.getGameId());
            pstmt.setString(2, pieceVO.getName());
            pstmt.setInt(3, pieceVO.getRow());
            pstmt.setString(4, pieceVO.getCol());

            pstmt.executeUpdate();
        }
    }

    public List<PieceVo> findAll(int gameId) throws SQLException {
        String query = "SELECT * FROM piece WHERE game_id = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, gameId);
            ResultSet rs = pstmt.executeQuery();
            return getPieceVOS(rs);
        }
    }

    private List<PieceVo> getPieceVOS(ResultSet resultSet) throws SQLException {
        ArrayList<PieceVo> pieceVos = new ArrayList<>();
        while (resultSet.next()) {
            PieceVo pieceVO = new PieceVo(
                    resultSet.getInt("game_id"),
                    resultSet.getString("name"),
                    resultSet.getInt("row"),
                    resultSet.getString("col")
            );
            pieceVos.add(pieceVO);
        }
        return pieceVos;
    }
}
