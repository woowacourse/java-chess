package chess.domain.piece.piecefigure;

import chess.domain.board.Board;
import chess.domain.board.Position;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class BlackPawnTest {
    private static final String START_POSITION = "31";
    private static final String DEFAULT_START_POSITION = "10";

    private Piece blackPawn;

    @BeforeEach
    public void setUp() {
        blackPawn = BlackPawn.of();
    }

    @Test
    public void create() {
        assertThat(blackPawn == BlackPawn.of()).isTrue();
    }

    @Test
    public void 이동할_수_있는_모든위치를_반환하는지_테스트() {
        Set<Position> positions = new HashSet<>(Collections.singletonList(Position.of("41")));
        assertThat(blackPawn.makePossiblePositions(Position.of(START_POSITION), new Board()::getCurrentPiece))
                .isEqualTo(positions);
    }

    @Test
    public void 첫_번째_이동하는_경우의_모든위치를_반환하는지_테스트() {
        Set<Position> positions = new HashSet<>(Arrays.asList(Position.of("20"), Position.of("30")));
        assertThat(blackPawn.makePossiblePositions(Position.of(DEFAULT_START_POSITION), new Board()::getCurrentPiece))
                .isEqualTo(positions);
    }

    @AfterEach
    public void tearDown() {
        blackPawn = null;
    }
}