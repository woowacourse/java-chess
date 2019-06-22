package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.board.Position;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class BishopTest {
    private static final String START_POSITION = "11";

    private Set<Position> positions;
    private Piece bishop;

    @BeforeEach
    public void setUp() {
        positions = new HashSet<>(Arrays.asList(
                Position.of("00"), Position.of("22"), Position.of("33"), Position.of("44"), Position.of("55"), Position.of("66"), Position.of("77"),
                Position.of("02"), Position.of("20")));
        bishop = Bishop.of(TeamType.WHITE);
    }

    @Test
    public void create() {
        assertThat(bishop == Bishop.of(TeamType.WHITE)).isTrue();
    }

    @Test
    public void 같은_팀인지_테스트() {
        assertThat(bishop.isSameTeam(Bishop.of(TeamType.WHITE))).isTrue();
    }

    @Test
    public void 다른_팀인지_테스트() {
        assertThat(bishop.isSameTeam(Bishop.of(TeamType.BLACK))).isFalse();
    }

    @Test
    public void 이동가능한_모든_경로를_반환하는지_테스트() {
        Board board = new Board();
        Set<Position> positions = bishop.makePossiblePositions(Position.of(START_POSITION), board::getCurrentPiece);

        assertThat(positions).isEqualTo(this.positions);
    }

    @AfterEach
    public void tearDown() {
        bishop = null;
    }
}
