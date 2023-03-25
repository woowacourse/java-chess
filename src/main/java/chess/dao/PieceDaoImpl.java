package chess.dao;

import chess.dao.dto.PieceDto;
import chess.domain.board.File;
import chess.domain.board.Rank;
import chess.domain.board.Square;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PieceDaoImpl implements PieceDao {

    private final JdbcTemplate jdbcTemplate;

    public PieceDaoImpl(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void save(final Long chessGameId, final Square square, final Piece piece) {
        final Color color = piece.getColor();
        final PieceType pieceType = piece.getPieceType();
        final File file = square.getFile();
        final Rank rank = square.getRank();

        final String query = "insert into piece(chess_game_id, color, type, file, `rank`) values(?, ?, ?, ?, ?)";
        final List<String> parameters = List.of(
                String.valueOf(chessGameId), color.name(), pieceType.name(), file.name(), rank.name()
        );
        jdbcTemplate.executeUpdate(query, parameters);
    }

    @Override
    public Optional<PieceDto> findByFileAndRank(final Long chessGameId, final File file, final Rank rank) {
        final String query = "select * from piece as p where p.chess_game_id = ? and p.file = ? and p.rank = ?";
        final List<String> parameters = List.of(String.valueOf(chessGameId), file.name(), rank.name());

        return jdbcTemplate.executeQuery(query, resultSet -> {
            if (resultSet.next()) {
                final PieceDto pieceDto = PieceDto.of(
                        resultSet.getLong(1),
                        resultSet.getLong(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6)
                );
                return Optional.of(pieceDto);
            }
            return Optional.empty();
        }, parameters);
    }

    @Override
    public List<PieceDto> findAllByChessGameId(final Long chessGameId) {
        final String query = "select * from piece as p where p.chess_game_id = ?";
        final List<String> parameters = List.of(String.valueOf(chessGameId));

        return jdbcTemplate.executeQuery(query, resultSet -> {
            final List<PieceDto> result = new ArrayList<>();
            while (resultSet.next()) {
                result.add(
                        PieceDto.of(
                                resultSet.getLong(1),
                                resultSet.getLong(2),
                                resultSet.getString(3),
                                resultSet.getString(4),
                                resultSet.getString(5),
                                resultSet.getString(6)
                        )
                );
            }
            return result;
        }, parameters);
    }

    @Override
    public void update(final Long id, final Color color, final PieceType pieceType) {
        final String query = "update piece set color = ?, type = ? where id = ?";
        final List<String> parameters = List.of(color.name(), pieceType.name(), String.valueOf(id));
        jdbcTemplate.executeUpdate(query, parameters);
    }

    @Override
    public void delete(final Long id) {
        final String query = "delete from piece where id = ?";
        final List<String> parameters = List.of(String.valueOf(id));
        jdbcTemplate.executeUpdate(query, parameters);
    }
}
