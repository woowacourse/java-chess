package chess.dao.chess;

import chess.database.JdbcTemplate;
import chess.entity.PieceEntity;

import java.util.List;

public class PieceDaoImpl implements PieceDao {

    private final JdbcTemplate jdbcTemplate;

    public PieceDaoImpl(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<PieceEntity> findByChessGameId(final Long chessGameId) {
        final String query = "SELECT * FROM piece WHERE chess_game_id = ?";
        return jdbcTemplate.findAll(query, (resultSet -> PieceEntity.createWithId(
                resultSet.getLong("piece_id"),
                resultSet.getInt("piece_rank"),
                resultSet.getInt("piece_file"),
                resultSet.getString("piece_type"),
                resultSet.getString("camp"))), String.valueOf(chessGameId));
    }

    @Override
    public Long save(final PieceEntity pieceEntity) {
        final String query = "INSERT INTO piece(piece_rank, piece_file, piece_type, camp, chess_game_id) " +
                "VALUES (?, ?, ?, ?, ?)";
        return jdbcTemplate.executeUpdate(query,
                String.valueOf(pieceEntity.getRank()),
                String.valueOf(pieceEntity.getFile()),
                pieceEntity.getPieceType(), pieceEntity.getCampType(),
                String.valueOf(pieceEntity.getChessGameId()));
    }

    @Override
    public void deleteByPositions(final Long chessGameId, final PieceEntity... pieceEntity) {
        if (pieceEntity.length == 1) {
            deleteOneByPosition(chessGameId, pieceEntity[0]);
        }
        final PieceEntity source = pieceEntity[0];
        final PieceEntity destination = pieceEntity[1];
        deleteTwoByPosition(chessGameId, source, destination);
    }

    @Override
    public void deleteByChessGameId(final Long chessGameId) {
        final String query = "DELETE FROM piece where chess_game_id = ?";

        jdbcTemplate.executeUpdate(query,
                String.valueOf(chessGameId));
    }

    private void deleteOneByPosition(final Long chessGameId, final PieceEntity pieceEntity) {
        final String query = "DELETE FROM piece WHERE chess_game_id = ? " +
                "and piece_rank = ? and piece_file = ?";

        jdbcTemplate.executeUpdate(query,
                String.valueOf(chessGameId),
                String.valueOf(pieceEntity.getRank()),
                String.valueOf(pieceEntity.getFile()));
    }

    private void deleteTwoByPosition(final Long chessGameId, final PieceEntity source, final PieceEntity destination) {
        final String query = "DELETE FROM piece WHERE chess_game_id = ? " +
                "and (" +
                " (piece_rank = ? and piece_file = ?) or (piece_rank = ? and piece_file = ?)" +
                ")";

        jdbcTemplate.executeUpdate(query,
                String.valueOf(chessGameId),
                String.valueOf(source.getRank()),
                String.valueOf(source.getFile()),
                String.valueOf(destination.getRank()),
                String.valueOf(destination.getFile()));
    }
}
