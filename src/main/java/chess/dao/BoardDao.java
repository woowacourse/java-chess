package chess.dao;

import chess.domain.board.Board;
import chess.domain.piece.Empty;
import chess.domain.piece.Piece;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import chess.domain.team.Team;
import chess.view.PieceName;

import java.util.HashMap;
import java.util.Map;

public class BoardDao {

    private static final String NOT_EXIST_PIECE_ERROR_MESSAGE = "해당하는 위치에 체스말이 존재하지 않습니다";

    public static Board create() {
        final var query = "INSERT INTO board() VALUES()";

        final int id = JdbcTemplate.insertAndReturnKey(query);

        return new Board(id, createMapById(id));
    }

    private static Map<Position, Piece> createMapById(final int boardId) {
        final Map<Position, Piece> board = new HashMap<>();

        for (final File file : File.values()) {
            for (final Rank rank : Rank.values()) {
                final String position = file.value() + String.valueOf(rank.value());
                board.put(Position.of(file, rank), findByPosition(boardId, position));
            }
        }
        return board;
    }

    private static Piece findByPosition(final int boardId, final String position) {
        final var query = "SELECT " + position + " FROM board WHERE id = ?";

        final RowMapper<Piece> mapper = resultSet -> {
            if (resultSet.next()) {
                final String name = resultSet.getString(1);
                return PieceName.findByName(name);
            }
            throw new RuntimeException(NOT_EXIST_PIECE_ERROR_MESSAGE);
        };

        return JdbcTemplate.select(query, mapper, boardId);
    }

    public static Board findById(final int boardId) {
        final var query = "SELECT id FROM board WHERE id = ?";

        final RowMapper<Board> mapper = resultSet -> {
            if (resultSet.next()) {
                final int id = resultSet.getInt(1);
                return new Board(id, createMapById(id));
            }
            return null;
        };

        return JdbcTemplate.select(query, mapper, boardId);
    }

    public static void updateByMove(final Board board,
                                    final Position source,
                                    final Position target,
                                    final Piece sourcePiece) {
        final var query = "UPDATE board SET %s = ? WHERE id = ?";

        final var targetQuery = String.format(query, target.getCoordinate());
        JdbcTemplate.executeQuery(targetQuery, PieceName.findByPiece(sourcePiece), board.getId());

        final var sourceQuery = String.format(query, source.getCoordinate());
        JdbcTemplate.executeQuery(sourceQuery, PieceName.findByPiece(new Empty(Team.NONE)), board.getId());
    }
}
