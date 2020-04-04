package chess.domain.piece;

import chess.repository.RepositoryUtil;

import java.sql.*;

public class GamePieceDAO {

    private final Connection connection;

    public GamePieceDAO() {
        this.connection = RepositoryUtil.getConnection();
    }

    public int addGamePiece(int positionId, GamePiece gamePiece) throws SQLException {
        String query = "INSERT INTO piece (position_id, piece_name) VALUES (?, ?)";
        PreparedStatement pstmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        pstmt.setString(1, Integer.toString(positionId));
        pstmt.setString(2, gamePiece.getName());
        pstmt.executeUpdate();

        ResultSet generatedKeys = pstmt.getGeneratedKeys();

        if (generatedKeys.next()) {
            return generatedKeys.getInt(1);
        }
        return 0;
    }

    public GamePiece findPieceByPositionId(int positionId) throws SQLException {
        String query = "SELECT * FROM piece WHERE position_id = ?";
        PreparedStatement pstmt = connection.prepareStatement(query);
        pstmt.setString(1, Integer.toString(positionId));
        ResultSet rs = pstmt.executeQuery();

        if (!rs.next()) {
            return null;
        }

        return ChessPiece.findGamePieceBy(rs.getString("piece_name"));
    }

    public void updatePieceNameByPositionId(int positionId, String changedName) throws SQLException {
        String query = "UPDATE piece SET piece_name = ? WHERE position_id = ?";
        PreparedStatement pstmt = connection.prepareStatement(query);
        pstmt.setString(1, changedName);
        pstmt.setString(2, Integer.toString(positionId));
        pstmt.executeUpdate();
    }
}
