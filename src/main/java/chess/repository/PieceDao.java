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

    public void save(final Map<Position, Piece> piecesByPosition, final long game_id) {
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

}
