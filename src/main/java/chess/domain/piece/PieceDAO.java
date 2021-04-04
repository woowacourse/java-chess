package chess.domain.piece;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import chess.domain.ConnectionUtils;
import chess.domain.board.BoardDTO;
import chess.domain.position.MovePositionDTO;

public class PieceDAO {
    public void saveAll(Long chessId, BoardDTO boardDTO) throws SQLException {
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
                final String position = resultSet.getString("position");
                final String color = resultSet.getString("color");
                final String name = resultSet.getString("name");
                pieceDTOS.add(new PieceDTO(position, color, name));
            }
        }
        return pieceDTOS;
    }

    public void update(MovePositionDTO movePositionDTO) throws SQLException {
        String sourceQuery = "UPDATE piece, (SELECT color, name FROM piece WHERE position = (?)) AS source SET piece.color = source.color, piece.name = source.name WHERE position = (?);";
        try (Connection connection = ConnectionUtils.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sourceQuery)) {
            pstmt.setString(1, movePositionDTO.getSource());
            pstmt.setString(2, movePositionDTO.getTarget());
            pstmt.executeUpdate();
        }

        String query = "UPDATE piece SET color = 'BLANK', name = 'BLANK' WHERE position = (?)";
        try (Connection connection = ConnectionUtils.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, movePositionDTO.getSource());
            pstmt.executeUpdate();
        }
    }
}
