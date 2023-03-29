package chess.dao;

import chess.controller.ChessState;
import chess.dto.ChessGameDto;
import chess.dto.ChessRoomDto;
import chess.dto.PlayerDto;

import java.util.Optional;

public class ChessRoomDao {

    public Optional<ChessRoomDto> findByPlayer(final PlayerDto playerDto) {
        final var query = "SELECT id, chess_game_id, player_id, state FROM chess_room WHERE player_id = ? AND state != \"END\"";

        final RowMapper<ChessRoomDto> mapper = resultSet -> {
            if (resultSet.next()) {
                return ChessRoomDto.of(
                        resultSet.getInt(1),
                        resultSet.getInt(2),
                        resultSet.getInt(3),
                        resultSet.getString(4)
                );
            }
            return null;
        };

        return Optional.ofNullable(JdbcTemplate.select(query, mapper, playerDto.getId()));
    }

    private void create(final PlayerDto playerDto) {
        final ChessGameDto chessGameDto = ChessGameDao.createAndReturnDto();

        final var query = "INSERT INTO chess_room(chess_game_id, player_id, state) VALUES (?, ?, ?)";

        JdbcTemplate.executeQuery(query, chessGameDto.getId(), playerDto.getId(), "INIT");
    }

    public void createIfNotExist(final PlayerDto playerDto) {
        if (findByPlayer(playerDto).isEmpty()) {
            create(playerDto);
        }
    }

    public void updateState(final ChessRoomDto chessRoomDto, final ChessState state) {
        final var query = "UPDATE chess_room SET state = ? WHERE id = ?";

        JdbcTemplate.executeQuery(query, state.name(), chessRoomDto.getId());
    }
}
