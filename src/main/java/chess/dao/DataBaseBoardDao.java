package chess.dao;

import chess.domain.board.Board;
import chess.domain.board.Turn;
import chess.domain.piece.Bishop;
import chess.domain.piece.Color;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.position.Position;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Function;

public class DataBaseBoardDao implements BoardDao {

    private static final Map<PieceType, Function<Color, Piece>> PIECE_MAPPER = new EnumMap<>(PieceType.class);

    static {
        PIECE_MAPPER.put(PieceType.PAWN, Pawn::new);
        PIECE_MAPPER.put(PieceType.KNIGHT, Knight::new);
        PIECE_MAPPER.put(PieceType.BISHOP, Bishop::new);
        PIECE_MAPPER.put(PieceType.ROOK, Rook::new);
        PIECE_MAPPER.put(PieceType.QUEEN, Queen::new);
        PIECE_MAPPER.put(PieceType.KING, King::new);
    }

    @Override
    public void saveBoard(final Board board, final long gameId) {
        final String saveBoardQuery = "INSERT INTO board VALUES (?,?,?,?,?)";
        try (final Connection connection = ConnectionGenerator.getConnection();
             final PreparedStatement preparedStatement =
                     connection.prepareStatement(saveBoardQuery)) {
            preparedStatement.setLong(1, gameId);
            for (final Entry<Position, Piece> positionPieceEntry : board.getBoard().entrySet()) {
                final Position position = positionPieceEntry.getKey();
                final Piece piece = positionPieceEntry.getValue();
                preparedStatement.setInt(2, position.file().value());
                preparedStatement.setInt(3, position.rank().value());
                preparedStatement.setString(4, piece.getColor().name());
                preparedStatement.setString(5, piece.getPieceType().name());
                preparedStatement.executeUpdate();
            }
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updatePiecePosition(final Position from, final Position to, final long gameId) {
        removeDestinationPosition(to, gameId);
        final String updatePiecePositionQuery =
                "UPDATE board SET position_file = ?, position_rank = ? "
                        + "where chess_game_id = ? AND position_file = ? AND Position_rank = ?";
        try (final Connection connection = ConnectionGenerator.getConnection();
             final PreparedStatement preparedStatement =
                     connection.prepareStatement(updatePiecePositionQuery)) {
            preparedStatement.setInt(1, to.file().value());
            preparedStatement.setInt(2, to.rank().value());
            preparedStatement.setLong(3, gameId);
            preparedStatement.setInt(4, from.file().value());
            preparedStatement.setInt(5, from.rank().value());
            preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private void removeDestinationPosition(final Position to, final long gameId) {
        final String removeDestinationPositionQuery = "DELETE FROM board WHERE chess_game_id = ? "
                + "AND position_file = ? AND position_rank = ?";
        try (final Connection connection = ConnectionGenerator.getConnection();
             final PreparedStatement preparedStatement =
                     connection.prepareStatement(removeDestinationPositionQuery)) {
            preparedStatement.setLong(1, gameId);
            preparedStatement.setInt(2, to.file().value());
            preparedStatement.setInt(3, to.rank().value());
            preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public Board loadBoard(final Long gameId, final Turn turn) {
        final String findBoardInfoByChessGameId =
                "SELECT position_file, position_rank, color, pieceType from board WHERE chess_game_id = ?";
        try (final Connection connection = ConnectionGenerator.getConnection();
             final PreparedStatement preparedStatement =
                     connection.prepareStatement(findBoardInfoByChessGameId)) {
            preparedStatement.setLong(1, gameId);
            final ResultSet resultSet = preparedStatement.executeQuery();
            final Map<Position, Piece> board = new HashMap<>();
            while (resultSet.next()) {
                final int file = resultSet.getInt(1);
                final int rank = resultSet.getInt(2);
                final Color color = Color.valueOf(resultSet.getString(3));
                final PieceType pieceType = PieceType.valueOf(resultSet.getString(4));
                final Position position = new Position(file, rank);
                board.put(position, PIECE_MAPPER.get(pieceType).apply(color));
            }
            return new Board(board, turn);
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteGame() {

    }
}
