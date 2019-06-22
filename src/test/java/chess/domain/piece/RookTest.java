package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.board.Position;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class RookTest {
    private List<Position> positions;
    private Piece rook;

    @BeforeEach
    public void setUp() {
        positions = Arrays.asList(
                Position.of("01"), Position.of("21"), Position.of("31"), Position.of("41"), Position.of("51"), Position.of("61"), Position.of("71"),
                Position.of("10"), Position.of("12"), Position.of("13"), Position.of("14"), Position.of("15"), Position.of("16"), Position.of("17"));
        rook = Rook.of(TeamType.WHITE);
    }

    @Test
    public void create() {
        assertThat(rook == Rook.of(TeamType.WHITE)).isTrue();
    }

    @Test
    public void 같은_팀인지_테스트() {
        assertThat(rook.isSameTeam(Rook.of(TeamType.WHITE))).isTrue();
    }

    @Test
    public void 이동가능한_모든_경로를_반환하는지_테스트() {
        Board board = new Board();
        List<Position> positions = rook.makePossiblePositions(Position.of("11"), board::getCurrentPiece);

        assertThat(positions).isEqualTo(this.positions);
    }

    @AfterEach
    public void tearDown() {
        rook = null;
    }
}
