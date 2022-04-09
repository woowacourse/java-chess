package chess.domain.player.repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import chess.dao.connect.JdbcTemplate;
import chess.dao.dto.PlayerDto;
import chess.dao.mysql.PlayerDao;
import chess.domain.Color;
import chess.domain.Position;
import chess.domain.piece.Piece;
import chess.domain.player.Player;
import chess.domain.player.Players;

public class PlayerRepository {

    private final PlayerDao playerDao;

    public PlayerRepository(final JdbcTemplate jdbcTemplate) {
        this(new PlayerDao(jdbcTemplate));
    }

    public PlayerRepository(final PlayerDao playerDao) {
        this.playerDao = playerDao;
    }

    public List<Player> savePlayers(final Players players) {
        return players.getPlayers()
                .stream()
                .map(this::save)
                .collect(Collectors.toUnmodifiableList());
    }

    public Player save(final Player player) {
        final Color color = player.getColor();
        final Map<Position, Piece> pieces = player.getPieces();
        final PlayerDto playerDto = new PlayerDto(player.getId(), color.getName(), convertPiecesToString(pieces));
        return new Player(playerDao.save(playerDto), color, new HashMap<>(pieces));
    }

    public Player findById(final Long id) {
        final PlayerDto playerDto = playerDao.findById(id);
        final Color color = Color.from(playerDto.getColorName());
        final Map<Position, Piece> pieces = convertStringToPieces(color, playerDto.getPieces());
        return new Player(id, color, pieces);
    }

    public void update(final Player player) {
        final Color color = player.getColor();
        final Map<Position, Piece> pieces = player.getPieces();
        final PlayerDto playerDto = new PlayerDto(player.getId(), color.getName(), convertPiecesToString(pieces));
        playerDao.update(playerDto);
    }

    public void remove(final Long id) {
        playerDao.remove(id);
    }

    private String convertPiecesToString(final Map<Position, Piece> pieces) {
        final List<String> values = new ArrayList<>();
        for (Entry<Position, Piece> entry : pieces.entrySet()) {
            final Position position = entry.getKey();
            final Piece piece = entry.getValue();
            values.add(concatPositionAndPiece(position, piece));
        }
        return String.join(",", values);
    }

    private String concatPositionAndPiece(final Position position, final Piece piece) {
        return String.valueOf(position.getColumn()) + position.getRow() + ":" + piece.getPieceName();
    }

    private Map<Position, Piece> convertStringToPieces(final Color color, final String pieces) {
        return Arrays.stream(pieces.split(","))
                .map(value -> value.split(":"))
                .collect(Collectors.toMap(
                        value -> Position.from(value[0]),
                        value -> PieceConvertor.convertToPiece(color, value[1])
                ));
    }
}
