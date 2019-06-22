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

public class QueenTest {
    private static final String START_POSITION = "11";

    private Set<Position> positions;
    private Piece queen;

    @BeforeEach
    public void setUp() {
        positions = new HashSet<>(Arrays.asList(
                Position.of("01"), Position.of("21"), Position.of("31"), Position.of("41"), Position.of("51"), Position.of("61"), Position.of("71"),
                Position.of("10"), Position.of("12"), Position.of("13"), Position.of("14"), Position.of("15"), Position.of("16"), Position.of("17"),
                Position.of("00"), Position.of("22"), Position.of("33"), Position.of("44"), Position.of("55"), Position.of("66"), Position.of("77"),
                Position.of("02"), Position.of("20")));
        queen = Queen.of(TeamType.WHITE);
    }

    @Test
    public void create() {
        assertThat(queen == Queen.of(TeamType.WHITE)).isTrue();
    }

    @Test
    public void 같은_팀인지_테스트() {
        assertThat(queen.isSameTeam(Queen.of(TeamType.WHITE))).isTrue();
    }

    @Test
    public void 다른_팀인지_테스트() {
        assertThat(queen.isSameTeam(Queen.of(TeamType.BLACK))).isFalse();
    }

    @Test
    public void 이동가능한_모든_경로를_반환하는지_테스트() {
        Board board = new Board();
        Set<Position> positions = queen.makePossiblePositions(Position.of(START_POSITION), board::getCurrentPiece);

        assertThat(positions).isEqualTo(this.positions);
    }

    @AfterEach
    public void tearDown() {
        queen = null;
    }
}
