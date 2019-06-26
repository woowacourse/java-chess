package chess.domain.piece.piecefigure;

import chess.domain.board.Board;
import chess.domain.board.BoardGenerator;
import chess.domain.board.BoardInputForTest;
import chess.domain.board.Position;
import chess.domain.piece.pieceinfo.TeamType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class BishopTest {
    private static final String START_POSITION = "72";

    private Piece bishop;

    @BeforeEach
    public void setUp() {

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
        Board board = BoardGenerator.createBoard(BoardInputForTest.EXAMPLE_BOARD);
        Set<Position> positions = bishop.makePossiblePositions(Position.of(START_POSITION), board::getCurrentPiece);
        Set<Position> expectedPositions = new HashSet<>(Arrays.asList(
                Position.of("63"), Position.of("54"), Position.of("45"),
                Position.of("36"), Position.of("27")));

        assertThat(positions).isEqualTo(expectedPositions);
    }

    @AfterEach
    public void tearDown() {
        bishop = null;
    }
}
