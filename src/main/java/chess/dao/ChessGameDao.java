package chess.dao;

import chess.domain.game.ChessGame;
import chess.domain.team.Team;
import chess.dto.ChessGameDto;
import chess.dto.ChessRoomDto;
import chess.view.TeamName;

public class ChessGameDao {

    public static ChessGameDto createAndReturnDto() {
        final var query = "INSERT INTO chess_game(turn) VALUES (?)";

        final var id = JdbcTemplate.insertAndReturnKey(query, Team.WHITE.name());

        return findById(id);
    }

    public static ChessGameDto findById(final int id) {
        final var query = "SELECT * FROM chess_game WHERE id = ?";

        final RowMapper<ChessGameDto> mapper = makeChessGameDtoRowMapper();

        return JdbcTemplate.select(query, mapper, id);
    }

    private static RowMapper<ChessGameDto> makeChessGameDtoRowMapper() {
        final RowMapper<ChessGameDto> mapper = resultSet -> {
            if (resultSet.next()) {
                return ChessGameDto.of(
                        resultSet.getInt(1),
                        resultSet.getString(2)
                );
            }
            return null;
        };
        return mapper;
    }

    public ChessGameDto findByChessRoom(final ChessRoomDto chessRoomDto) {
        final var query = "SELECT * FROM chess_game WHERE id = ?";

        final RowMapper<ChessGameDto> mapper = makeChessGameDtoRowMapper();

        return JdbcTemplate.select(query, mapper, chessRoomDto.getChessGameId());
    }

    public static void updateTurn(final int id, final ChessGame chessGame) {
        final var query = "UPDATE chess_game SET turn = ? WHERE id = ?";
        JdbcTemplate.executeQuery(query, TeamName.findByTeam(chessGame.getTurn()), id);
    }
}
