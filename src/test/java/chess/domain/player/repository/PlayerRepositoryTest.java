package chess.domain.player.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.dao.dto.PlayerDto;
import chess.dao.mysql.PlayerDao;
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
        playerRepository = new PlayerRepository(PlayerDao.getInstance());
    }

    @DisplayName("데이터를 저장할 수 있어야 한다.")
    @Test
    void save() {
        final Player player = Player.of(Color.WHITE, Map.of(
                Position.from("a1"), Rook.getInstance(),
                Position.from("a2"), Pawn.getPawnByColor(Color.WHITE)
        ));
        final Long playerId = playerRepository.save(player);
        assertThat(playerId).isNotEqualTo(0L);
    }

    @DisplayName("데이터를 저장할 수 있어야 한다.")
    @Test
    void findById() {
        final Color expectedColor = Color.WHITE;
        final Map<Position, Piece> expectedPieces = Map.of(
                Position.from("a1"), Rook.getInstance(),
                Position.from("a2"), Pawn.getPawnByColor(Color.WHITE));

        final Long playerId = playerRepository.save(Player.of(expectedColor, expectedPieces));
        final Player player = playerRepository.findById(playerId);

        final Color actualColor = player.getColor();
        assertAll(() -> {
            assertThat(player.getColor()).isEqualTo(expectedColor);
            assertThat(player.getPieces()).isEqualTo(expectedPieces);
        });
    }
}