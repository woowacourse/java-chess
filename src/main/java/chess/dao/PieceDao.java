package chess.dao;

import chess.controller.web.dto.piece.PieceResponseDto;
import chess.domain.board.position.Position;
import chess.domain.piece.Piece;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PieceDao {

    public long[] savePieces(final Long gameId, final Map<Position, Piece> pieces) {
        final String query =
                "INSERT INTO piece(gameId, position, symbol) VALUES (?, ?, ?)";

        try (Connection connection = ConnectionProvider.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {

            for (final Map.Entry<Position, Piece> entry : pieces.entrySet()) {
                pstmt.setInt(1, gameId.intValue());
                pstmt.setString(2, entry.getKey().parseString());
                pstmt.setString(3, entry.getValue().getSymbol());
                pstmt.addBatch();
            }

            return pstmt.executeLargeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<PieceResponseDto> findPiecesByGameId(final Long gameId) {
        final String query =
                "SELECT * from piece where gameId = ?";

        try (Connection connection = ConnectionProvider.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query);) {
            pstmt.setInt(1, gameId.intValue());
            List<PieceResponseDto> pieceResponseDtos = new ArrayList<>();

            try (ResultSet resultSet = pstmt.executeQuery()) {
                while (resultSet.next()) {
                    pieceResponseDtos.add(
                            new PieceResponseDto(resultSet.getString("symbol"),
                                    resultSet.getString("position")));
                }
                return pieceResponseDtos;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Long updateTargetPiece(final String target, final Piece sourcePiece, final Long gameId) {
        final String query =
                "UPDATE piece SET symbol = ? where gameId = ? && position = ?";

        try (Connection connection = ConnectionProvider.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query);) {
            pstmt.setString(1, sourcePiece.getSymbol());
            pstmt.setInt(2, gameId.intValue());
            pstmt.setString(3, target);
            return pstmt.executeLargeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Long updateSourcePiece(final String source, final Long gameId) {
        final String query =
                "UPDATE piece SET symbol = ? WHERE gameId=? && position=?";

        try (Connection connection = ConnectionProvider.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query);) {
            pstmt.setString(1, ".");
            pstmt.setInt(2, gameId.intValue());
            pstmt.setString(3, source);
            return pstmt.executeLargeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
