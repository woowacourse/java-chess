package chessgame.dao;

import chessgame.domain.chessgame.Camp;
import chessgame.domain.coordinate.Coordinate;
import chessgame.domain.piece.Piece;
import chessgame.domain.piece.PieceType;
import chessgame.dto.PieceDto;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PiecesDao {

    private static final String INSERT_PIECE_QUERY =
            "INSERT INTO pieces(`rank`, `file`, `type`, `camp`, `game_room_id`) VALUES(?, ?, ?, ?, ?)";
    private static final String FIND_PIECES_BY_ROOM_ID_QUERY =
            "SELECT * FROM pieces WHERE game_room_id = ?";
    private static final String UPDATE_PIECE_BY_COORDINATE_QUERY =
            "UPDATE pieces SET type = ?, camp = ?" +
                    "WHERE `game_room_id` = ? and `rank` = ? and `file` = ?";

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

    public List<PieceDto> findPiecesByRoomId(long roomId) {
        try (final var connection = databaseConnection.getConnection();
             final var preparedStatement = connection.prepareStatement(FIND_PIECES_BY_ROOM_ID_QUERY)) {
            preparedStatement.setLong(1, roomId);
            final var result = preparedStatement.executeQuery();

            List<PieceDto> pieces = new ArrayList<>();

            while (result.next()) {
                PieceDto pieceDto = new PieceDto(
                        result.getInt("rank"),
                        result.getInt("file"),
                        result.getString("type"),
                        result.getString("camp"),
                        result.getInt("game_room_id")
                );
                pieces.add(pieceDto);
            }
            return pieces;
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updatePieceByCoordinate(long roomId, Coordinate coordinate, Piece chagePiece) {
        try (final var connection = databaseConnection.getConnection();
             final var preparedStatement = connection.prepareStatement(UPDATE_PIECE_BY_COORDINATE_QUERY)) {
            PieceType pieceType = chagePiece.pieceType();
            Camp camp = chagePiece.camp();
            int rank = coordinate.row();
            int file = coordinate.column();

            preparedStatement.setString(1, pieceType.name());
            preparedStatement.setString(2, camp.name());
            preparedStatement.setLong(3, roomId);
            preparedStatement.setInt(4, rank);
            preparedStatement.setInt(5, file);
            preparedStatement.executeUpdate();
        } catch (final SQLException exception) {
            throw new RuntimeException(exception);
        }
    }
}
