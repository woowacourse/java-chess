package chess.repository;

import chess.domain.TeamColor;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;

public class PieceDao {

    private final Connection connection;

    public PieceDao() {
        connection = ConnectionProvider.getConnection();
    }

    public void save(final Map<Position, Piece> piecesByPosition, final long game_id) { // PieceId 반환?
        piecesByPosition.forEach((position, piece) -> saveEachPiece(piece, position, game_id));
    }

    private void saveEachPiece(final Piece piece, final Position position, final long gameId) {
        String queryStatement = "INSERT INTO piece (type, position, color, game_id) VALUES(?, ?, ?, ?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(queryStatement);
            preparedStatement.setString(1, piece.getType().name());
            preparedStatement.setString(2, position.toString());
            preparedStatement.setString(3, convertTeamColorToString(piece.getColor()));
            preparedStatement.setLong(4, gameId);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.err.println("INSERT 오류 " + e.getMessage());
            e.printStackTrace();
        }
    }

    private String convertTeamColorToString(final TeamColor color) {
        return color.name();
    }

    public boolean updateByPositionAndGameId(final Position prev, final long gameId, final Position current) { // 이동시킨다
        String queryStatement = "UPDATE piece SET position = ? WHERE game_id = ? AND position = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(queryStatement);
            preparedStatement.setString(1, current.toString());
            preparedStatement.setLong(2, gameId);
            preparedStatement.setString(3, prev.toString());
            int result = preparedStatement.executeUpdate();

            if (result != 1) {
                return false;
            }

        } catch (SQLException e) {
            System.err.println("UPDATE 오류: " + e.getMessage());
            e.printStackTrace();
        }
        return true;
    }

    public boolean deleteByPositionAndGameId(final Position target, final long gameId) {
        String queryStatement = "DELETE FROM piece WHERE game_id = ? AND position = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(queryStatement);
            preparedStatement.setLong(1, gameId);
            preparedStatement.setString(2, target.toString());
            int result = preparedStatement.executeUpdate();

            if (result == 1) {
                return false;
            }

        } catch (SQLException e) {
            System.err.println("DELETE 오류: " + e.getMessage());
            e.printStackTrace();
        }
        return true;
    }

}
