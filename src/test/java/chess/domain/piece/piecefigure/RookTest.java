package chess.domain.piece.piecefigure;

import chess.domain.board.BoardInputForTest;
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

import static org.assertj.core.api.Assertions.assertThat;

public class RookTest {
    private static final String START_POSITION = "70";

    private Piece rook;

    @BeforeEach
    public void setUp() {
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
    public void 다른_팀인지_테스트() {
        assertThat(rook.isSameTeam(Rook.of(TeamType.BLACK))).isFalse();
    }

    @Test
    public void 이동가능한_모든_경로를_반환하는지_테스트() {
        Board board = BoardGenerator.createBoard(BoardInputForTest.EXAMPLE_BOARD);
        Set<Position> positions = rook.makePossiblePositions(Position.of(START_POSITION), board::getCurrentPiece);
        Set<Position> expectedPositions = new HashSet<>(Arrays.asList(
                Position.of("60"), Position.of("50"), Position.of("71")));

        assertThat(positions).isEqualTo(expectedPositions);
    }

    @AfterEach
    public void tearDown() {
        rook = null;
    }
}
