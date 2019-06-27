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

public class KingTest {
    private static final String START_POSITION = "74";

    private Piece king;

    @BeforeEach
    public void setUp() {
        king = King.of(TeamType.WHITE);
    }

    @Test
    public void create() {
        assertThat(king == King.of(TeamType.WHITE)).isTrue();
    }

    @Test
    public void 같은_팀인지_테스트() {
        assertThat(king.isSameTeam(King.of(TeamType.WHITE))).isTrue();
    }

    @Test
    public void 다른_팀인지_테스트() {
        assertThat(king.isSameTeam(King.of(TeamType.BLACK))).isFalse();
    }

    @Test
    public void 이동가능한_모든_경로를_반환하는지_테스트() {
        Board board = BoardGenerator.createBoard(EXAMPLE_BOARD);
        Set<Position> positions = king.makePossiblePositions(Position.of(START_POSITION), board::getCurrentPiece);
        Set<Position> expectedPositions = new HashSet<>(Arrays.asList(Position.of("63"), Position.of("75")));

        assertThat(positions).isEqualTo(expectedPositions);
    }

    @AfterEach
    public void tearDown() {
        king = null;
    }
}
