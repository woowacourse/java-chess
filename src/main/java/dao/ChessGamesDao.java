package dao;

import db.DBConnection;
import dto.ChessGameDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ChessGamesDao {
    public ArrayList<ChessGameDto> findAll() throws SQLException {
        String query = "SELECT * FROM game;";

        try(Connection connection = DBConnection.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(query)) {
            ResultSet rs = pstmt.executeQuery();
            ArrayList<ChessGameDto> chessGameDtos = getChessGameDtos(rs);
            return chessGameDtos;
        }
    }

    private ArrayList<ChessGameDto> getChessGameDtos(ResultSet resultSet) throws SQLException {
        ArrayList<ChessGameDto> chessGameDtos = new ArrayList<>();
        while (resultSet.next()) {
            ChessGameDto chessGameDTO = new ChessGameDto(
                    resultSet.getInt("id"),
                    resultSet.getString("white_name"),
                    resultSet.getString("black_name"),
                    resultSet.getInt("turn_is_black"));
            chessGameDtos.add(chessGameDTO);
        }
        return chessGameDtos;
    }
}
