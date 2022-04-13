package chess.domain.player.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.dao.connect.JdbcTemplate;
import chess.dao.connect.TestDbConnector;
import chess.domain.Color;
import chess.domain.Position;
import chess.domain.piece.Piece;
import chess.domain.piece.movable.Pawn;
import chess.domain.piece.movable.multiple.Rook;
import chess.domain.player.Player;

class PlayerRepositoryTest {

    private PlayerRepository playerRepository;

    @BeforeEach
    void setUp() {
        playerRepository = new PlayerRepository(new JdbcTemplate(new TestDbConnector()));
    }

    @DisplayName("데이터를 저장할 수 있어야 한다.")
    @Test
    void save() {
        final Player player = Player.of(Color.WHITE, Map.of(
                Position.from("a1"), Rook.getInstance(),
                Position.from("a2"), Pawn.getPawnByColor(Color.WHITE)
        ));
        final Long playerId = playerRepository.save(player).getId();
        assertThat(playerId).isNotEqualTo(0L);
        playerRepository.remove(playerId);
    }

    @DisplayName("데이터를 조회할 수 있어야 한다.")
    @Test
    void findById() {
        final Color expectedColor = Color.WHITE;
        final Map<Position, Piece> expectedPieces = Map.of(
                Position.from("a1"), Rook.getInstance(),
                Position.from("a2"), Pawn.getPawnByColor(Color.WHITE));

        final Long playerId = playerRepository.save(Player.of(expectedColor, expectedPieces)).getId();
        final Player player = playerRepository.findById(playerId);

        assertAll(() -> {
            assertThat(player.getColor()).isEqualTo(expectedColor);
            assertThat(player.getPieces()).isEqualTo(expectedPieces);
        });
        playerRepository.remove(playerId);
    }

    @DisplayName("데이터를 수정할 수 있어야 한다.")
    @Test
    void update() {
        final Map<Position, Piece> pieces = Map.of(
                Position.from("a1"), Rook.getInstance(),
                Position.from("a2"), Pawn.getPawnByColor(Color.WHITE));
        final Long playerId = playerRepository.save(Player.of(Color.WHITE, pieces)).getId();

        final Map<Position, Piece> updatedPieces = Map.of(
                Position.from("a5"), Rook.getInstance(),
                Position.from("a7"), Pawn.getPawnByColor(Color.WHITE));
        playerRepository.update(new Player(playerId, Color.WHITE, updatedPieces));

        final Player player = playerRepository.findById(playerId);
        assertThat(player.getPieces()).isEqualTo(updatedPieces);
        playerRepository.remove(playerId);
    }
}