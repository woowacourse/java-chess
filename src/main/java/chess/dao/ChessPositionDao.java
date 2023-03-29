package chess.dao;

import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.dto.ChessPositionDto;
import chess.view.PieceName;
import chess.view.TeamName;

public class ChessPositionDao {

    public static ChessPositionDto findById(final int id) {
        final var query = "SELECT * FROM chess_position WHERE id = ?";

        final RowMapper<ChessPositionDto> mapper = resultSet -> {
            if (resultSet.next()) {
                return ChessPositionDto.of(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5)
                );
            }
            return null;
        };

        return JdbcTemplate.select(query, mapper, id);
    }

    public static ChessPositionDto findByPositionAndPiece(final Position position, final Piece piece) {
        final var query = "SELECT * FROM chess_position WHERE file = ? AND `rank` = ? AND piece = ? AND team = ?";

        final RowMapper<ChessPositionDto> mapper = resultSet -> {
            if (resultSet.next()) {
                return ChessPositionDto.of(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5)
                );
            }
            return null;
        };

        final String pieceName = PieceName.findByFullName(piece);
        final String teamName = TeamName.findByTeam(piece.team());

        return JdbcTemplate.select(query, mapper, String.valueOf(position.file()), String.valueOf(position.rank()), pieceName, teamName);
    }
}
