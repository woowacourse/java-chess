package chess.dao.Piece;

import chess.dao.JdbcConnection;
import chess.dao.JdbcTemplate;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceFactory;
import chess.domain.piece.Side;
import chess.domain.piece.Type;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JdbcPieceDao implements PieceDao {

    private final Connection connection;
    private final JdbcTemplate jdbcTemplate;

    public JdbcPieceDao() {
        this.connection = JdbcConnection.getConnection();
        this.jdbcTemplate = new JdbcTemplate(connection);
    }


    @Override
    public void insert(final long chessGameId, final Position position, final Piece piece) {
        final String query = "INSERT INTO piece (chess_game_id, piece_file, piece_rank, side, type) " +
                "VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.executeUpdate(query, chessGameId, position.getFileIndex(),
                position.getRankIndex(), piece.getSide().name(), piece.getType().name());
    }

    @Override
    public void delete(final long chessGameId, final Position position) {
        final String query = "DELETE FROM piece WHERE piece_file = ? AND piece_rank = ?";
        jdbcTemplate.executeUpdate(query, position.getFileIndex(), position.getRankIndex());
    }

    @Override
    public Map<Position, Piece> findAllById(final long chessGameId) {
        Map<Position, Piece> board = new HashMap<>();

        final String query = "SELECT * FROM piece WHERE chess_game_id = ?";
        final List<String> resultParameters = List.of("type", "side", "piece_file", "piece_rank");
        final List<Object> result = jdbcTemplate.executeQuery(query, resultParameters, chessGameId);

        int index = 0;
        while (index < result.size()) {
            final String type = (String) result.get(index++);
            final String side = (String) result.get(index++);
            final int file = (int) result.get(index++);
            final int rank = (int) result.get(index++);
            board.put(Position.of(File.getFile(file), Rank.getRank(rank)), getPieceByType(type, side));
        }
        return board;
    }

    @Override
    public void deleteAll() {
        final String query = "DELETE FROM piece";
        jdbcTemplate.executeUpdate(query);
    }

    private Piece getPieceByType(final String type, final String side) {
        final Type pieceType = Type.valueOf(type);
        final Side pieceSide = Side.valueOf(side);

        return PieceFactory.createPiece(pieceType, pieceSide);
    }
}
