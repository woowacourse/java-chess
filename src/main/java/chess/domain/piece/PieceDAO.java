package chess.domain.piece;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import chess.domain.ConnectionUtils;
import chess.domain.board.BoardDTO;

public class PieceDAO {
    public void save(Long chessId, BoardDTO boardDTO) throws SQLException {
        String query = "INSERT INTO piece (chess_id, position, color, name) VALUES (?, ?, ?, ?)";
        try (Connection connection = ConnectionUtils.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {
            for (PieceDTO pieceDTO : boardDTO.getPieceDTOS()) {
                String position = pieceDTO.getPosition();
                String color = pieceDTO.getColor();
                String name = pieceDTO.getName();

                pstmt.setLong(1, chessId);
                pstmt.setString(2, position);
                pstmt.setString(3, color);
                pstmt.setString(4, name);
                pstmt.executeUpdate();
            }
        }
    }

    public List<PieceDTO> findPiecesByChessId(Long chessId) throws SQLException {
        List<PieceDTO> pieceDTOS = new ArrayList<>();
        String query = "SELECT position, color, name FROM piece WHERE chess_id = (?)";
        try (Connection connection = ConnectionUtils.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setLong(1, chessId);
            final ResultSet resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                final String name = resultSet.getString("name");
                final String color = resultSet.getString("color");
                final String position = resultSet.getString("position");
                pieceDTOS.add(new PieceDTO(name, color, position));
            }
        }
        return pieceDTOS;
    }
}
