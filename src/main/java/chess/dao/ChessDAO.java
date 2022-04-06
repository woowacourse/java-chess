package chess.dao;

import chess.DBConnector;
import chess.dto.ChessDTO;
import chess.dto.GameIdDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ChessDAO {

    public void deletePiece(String position, GameIdDTO gameIdDTO) {
        Connection connection = DBConnector.getConnection();
        final String sql = "delete from board where position = (?) and game_id = (?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, position);
            statement.setInt(2, gameIdDTO.getId());
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<ChessDTO> findAllPiece(GameIdDTO gameIdDTO) {
        Connection connection = DBConnector.getConnection();
        final String sql = "select * from board where game_id = (?)";
        
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, gameIdDTO.getId());
            ResultSet resultSet = statement.executeQuery();
            return toDTOFindPieces(resultSet);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private List<ChessDTO> toDTOFindPieces(ResultSet resultSet) throws SQLException {
        List<ChessDTO> boards = new ArrayList<>();
        while (resultSet.next()) {
            boards.add(new ChessDTO(resultSet.getString("color"), resultSet.getString("piece"),
                    resultSet.getString("position")));
        }
        return boards;
    }

    public void savePieces(List<ChessDTO> chessDTOS, GameIdDTO gameIdDTO) {
        Connection connection = DBConnector.getConnection();
        try {
            savePiece(chessDTOS, gameIdDTO, connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void savePiece(List<ChessDTO> chessDTOS, GameIdDTO gameIdDTO, Connection connection) throws SQLException {
        final String sql = "insert into board(game_id, piece, position, color) values (?, ?, ?, ?)";
        
        for (ChessDTO chessDto : chessDTOS) {
            PreparedStatement statement  = connection.prepareStatement(sql);
            statement.setInt(1, gameIdDTO.getId());
            statement.setString(2, chessDto.getPiece().toLowerCase());
            statement.setString(3, chessDto.getPosition());
            statement.setString(4, chessDto.getColor());
            statement.executeUpdate();
        }
    }
}
