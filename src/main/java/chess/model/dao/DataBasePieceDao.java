package chess.model.dao;

import chess.model.domain.board.Board;
import chess.model.domain.board.Turn;
import chess.model.domain.piece.Bishop;
import chess.model.domain.piece.Color;
import chess.model.domain.piece.King;
import chess.model.domain.piece.Knight;
import chess.model.domain.piece.Pawn;
import chess.model.domain.piece.Piece;
import chess.model.domain.piece.PieceType;
import chess.model.domain.piece.Queen;
import chess.model.domain.piece.Rook;
import chess.model.domain.position.Position;
import chess.model.exception.QueryFailException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Function;

public class DataBasePieceDao implements PieceDao {

    private static final Map<PieceType, Function<Color, Piece>> PIECE_MAPPER = new EnumMap<>(PieceType.class);

    private static final String SAVE_BOARD_QUERY = "INSERT INTO Piece VALUES (?,?,?,?,?)";
    private static final String REMOVE_PIECE_QUERY = "DELETE FROM Piece WHERE chess_game_id = ? "
            + "AND position_file = ? AND position_rank = ?";
    private static final String LOAD_BOARD_QUERY =
            "SELECT position_file, position_rank, color, pieceType from piece WHERE chess_game_id = ?";
    private static final String UPDATE_PIECE_QUERY =
            "UPDATE Piece SET position_file = ?, position_rank = ? "
                    + "where chess_game_id = ? AND position_file = ? AND Position_rank = ?";
    private static final String DELETE_BOARD_QUERY =
            "DELETE FROM piece WHERE chess_game_id = ?";

    static {
        PIECE_MAPPER.put(PieceType.PAWN, Pawn::new);
        PIECE_MAPPER.put(PieceType.KNIGHT, Knight::new);
        PIECE_MAPPER.put(PieceType.BISHOP, Bishop::new);
        PIECE_MAPPER.put(PieceType.ROOK, Rook::new);
        PIECE_MAPPER.put(PieceType.QUEEN, Queen::new);
        PIECE_MAPPER.put(PieceType.KING, King::new);
    }

    private final JdbcTemplate jdbcTemplate;

    public DataBasePieceDao(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void saveBoard(final Board board, final long gameId) {
        for (final Entry<Position, Piece> entry : board.getBoard().entrySet()) {
            final Position position = entry.getKey();
            final Piece piece = entry.getValue();
            jdbcTemplate.executeUpdate(SAVE_BOARD_QUERY, gameId, position.file().value(), position.rank().value(),
                    piece.getColor().name(), piece.getPieceType().name());
        }
    }

    @Override
    public void updatePiecePosition(final Position from, final Position to, final long gameId) {
        removeDestinationPosition(to, gameId);
        jdbcTemplate.executeUpdate(UPDATE_PIECE_QUERY, to.file().value(), to.rank().value(), gameId,
                from.file().value(), from.rank().value());
    }

    private void removeDestinationPosition(final Position to, final long gameId) {
        jdbcTemplate.executeUpdate(REMOVE_PIECE_QUERY, gameId,
                to.file().value(), to.rank().value());
    }

    @Override
    public Board loadBoard(final Long gameId, final Turn turn) {
        final Map<Position, Piece> positionPieceMap = jdbcTemplate.executeUpdate(LOAD_BOARD_QUERY,
                resultSet -> {
                    final Map<Position, Piece> board = new HashMap<>();
                    while (resultSet.next()) {
                        putPieceBoard(resultSet, board);
                    }
                    return board;
                }, gameId);
        return new Board(positionPieceMap, turn);
    }

    private static void putPieceBoard(final ResultSet resultSet, final Map<Position, Piece> board) throws SQLException {
        final int file = resultSet.getInt(1);
        final int rank = resultSet.getInt(2);
        final Color color = Color.valueOf(resultSet.getString(3));
        final PieceType pieceType = PieceType.valueOf(resultSet.getString(4));
        final Position position = new Position(file, rank);
        board.put(position, PIECE_MAPPER.get(pieceType).apply(color));
    }

    @Override
    public void deleteBoard(final long gameId) {
        try (final Connection connection = ConnectionGenerator.getConnection();
             final PreparedStatement preparedStatement =
                     connection.prepareStatement(DELETE_BOARD_QUERY)) {
            preparedStatement.setLong(1, gameId);
            preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            throw new QueryFailException();
        }
    }
}
