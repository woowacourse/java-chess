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
        return jdbcTemplate.findAll(query, (resultSet -> new PieceEntity(
                resultSet.getLong("piece_id"),
                resultSet.getInt("piece_rank"),
                resultSet.getInt("piece_file"),
                resultSet.getString("piece_type"),
                resultSet.getString("camp"))), String.valueOf(chessGameId));
    }

    @Override
    public Long save(final PieceEntity pieceEntity, final Long chessGameId) {
        final String query = "INSERT INTO piece(piece_rank, piece_file, piece_type, camp, chess_game_id) " +
                "VALUES (?, ?, ?, ?, ?)";
        return jdbcTemplate.executeUpdate(query,
                String.valueOf(pieceEntity.getRank()),
                String.valueOf(pieceEntity.getFile()),
                pieceEntity.getPieceType(), pieceEntity.getCampType(),
                String.valueOf(chessGameId));
    }
}
