package chess.domain.piece.piecefigure;

import chess.domain.board.Board;
import chess.domain.board.Position;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

class WhitePawnTest {
    private static final String START_POSITION = "11";
    private static final String DEFAULT_START_POSITION = "60";

    private Piece whitePawn;

    @BeforeEach
    public void setUp() {
        whitePawn = WhitePawn.of();
    }

    @Test
    public void create() {
        assertThat(whitePawn == WhitePawn.of()).isTrue();
    }

    @Test
    public void 이동할_수_있는_모든위치를_반환하는지_테스트() {
        Set<Position> positions = new HashSet<>(Collections.singletonList(Position.of("01")));
        assertThat(whitePawn.makePossiblePositions(Position.of(START_POSITION), new Board(new HashMap<>())::getCurrentPiece))
                .isEqualTo(positions);
    }

    @Test
    public void 첫_번째_이동하는_경우의_모든위치를_반환하는지_테스트() {
        Set<Position> positions = new HashSet<>(Arrays.asList(Position.of("50"), Position.of("40")));
        assertThat(whitePawn.makePossiblePositions(Position.of(DEFAULT_START_POSITION), new Board(new HashMap<>())::getCurrentPiece))
                .isEqualTo(positions);
    }

    @AfterEach
    public void tearDown() {
        whitePawn = null;
    }
}