package dao;

import db.DBConnection;
import dto.ChessGameDTO;
import vo.PieceVO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ChessGamesDAO {
    public ArrayList<ChessGameDTO> findAll() throws SQLException {
        String query = "SELECT * FROM game;";
        PreparedStatement pstmt = DBConnection.getInstance().prepareStatement(query);
        ResultSet rs = pstmt.executeQuery();

        ArrayList<ChessGameDTO> chessGameDTOs = getChessGameDTOs(rs);

        return chessGameDTOs;
    }

    private ArrayList<ChessGameDTO> getChessGameDTOs(ResultSet resultSet) throws SQLException {
        ArrayList<ChessGameDTO> chessGameDTOs = new ArrayList<>();
        while (resultSet.next()) {
            ChessGameDTO chessGameDTO = new ChessGameDTO(
                    resultSet.getInt("id"),
                    resultSet.getString("white_name"),
                    resultSet.getString("black_name"),
                    resultSet.getInt("turn_is_black"));
            chessGameDTOs.add(chessGameDTO);
        }
        return chessGameDTOs;
    }
}
