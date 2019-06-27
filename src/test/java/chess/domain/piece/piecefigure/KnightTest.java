package chess.domain.piece.piecefigure;

import chess.domain.board.Board;
import chess.domain.board.BoardGenerator;
import chess.domain.board.Position;
import chess.domain.piece.pieceinfo.TeamType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static chess.domain.board.BoardInputForTest.EXAMPLE_BOARD;
import static org.assertj.core.api.Assertions.assertThat;

class KnightTest {
    private static final String START_POSITION = "52";

    private Piece knight;

    @BeforeEach
    public void setUp() {
        knight = Knight.of(TeamType.WHITE);
    }

    @Test
    public void create() {
        assertThat(knight == Knight.of(TeamType.WHITE)).isTrue();
    }

    @Test
    public void 같은_팀인지_테스트() {
        assertThat(knight.isSameTeam(Knight.of(TeamType.WHITE))).isTrue();
    }

    @Test
    public void 다른_팀인지_테스트() {
        assertThat(knight.isSameTeam(Knight.of(TeamType.BLACK))).isFalse();
    }

    @Test
    public void 이동가능한_모든_경로를_반환하는지_테스트() {
        Board board = BoardGenerator.createBoard(EXAMPLE_BOARD);
        Set<Position> positions = knight.makePossiblePositions(Position.of(START_POSITION), board::getCurrentPiece);
        Set<Position> expectedPositions = new HashSet<>(Arrays.asList(
                Position.of("31"), Position.of("33"), Position.of("71"), Position.of("60")));

        assertThat(positions).isEqualTo(expectedPositions);
    }

    @AfterEach
    public void tearDown() {
        knight = null;
    }
}