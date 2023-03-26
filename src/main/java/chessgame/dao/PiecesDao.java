package chessgame.dao;

import chessgame.domain.chessgame.Camp;
import chessgame.domain.coordinate.Coordinate;
import chessgame.domain.piece.Piece;
import chessgame.domain.piece.PieceType;

import java.sql.SQLException;

public class PiecesDao {

    private static final String INSERT_PIECE_QUERY =
            "INSERT INTO pieces(`rank`, `file`, `type`, `camp`, `game_room_id`) VALUES(?, ?, ?, ?, ?)";
    private static final String FIND_PIECES_BY_ROOM_ID_QUERY =
            "SELECT * FROM pieces WHERE game_room_id = ?";

    private final DatabaseConnection databaseConnection = new DatabaseConnection();

    public void addPiece(long gameRoomId, Coordinate coordinate, Piece piece) {
        try (final var connection = databaseConnection.getConnection();
             final var preparedStatement = connection.prepareStatement(INSERT_PIECE_QUERY)) {
            PieceType pieceType = piece.pieceType();
            Camp camp = piece.camp();

            preparedStatement.setInt(1, coordinate.row());
            preparedStatement.setInt(2, coordinate.column());
            preparedStatement.setString(3, pieceType.name());
            preparedStatement.setString(4, camp.name());
            preparedStatement.setLong(5, gameRoomId);
            preparedStatement.executeUpdate();
        } catch (final SQLException exception) {
            throw new RuntimeException(exception);
        }
    }
}
