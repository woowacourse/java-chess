package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.board.Horizontal;
import chess.domain.board.Position;
import chess.domain.board.Vertical;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RookTest {
    Board board;
    Map<Position, Piece> testBoard = new LinkedHashMap<>();

    @BeforeEach
    void setUp() {
        for (Position position : Position.getPositions()) {
            testBoard.put(position, null);
        }
        testBoard.put(Position.of(Horizontal.A, Vertical.ONE), new Rook(Team.WHITE));
        testBoard.put(Position.of(Horizontal.A, Vertical.SEVEN), new Rook(Team.BLACK));
        testBoard.put(Position.of(Horizontal.C, Vertical.ONE), new Rook(Team.WHITE));

        board = new Board(testBoard);
    }

    @Test
    @DisplayName("룩 움직일 수 있는 좌표 확인")
    void movablePositionTest() {
        Rook rook = new Rook(Team.BLACK);
        List<Position> positions = rook.searchMovablePositions(Position.of(Horizontal.B, Vertical.ONE));

        assertThat(positions).containsExactly(
                Position.of(Horizontal.B, Vertical.TWO),

                Position.of(Horizontal.B, Vertical.THREE),
                Position.of(Horizontal.B, Vertical.FOUR),
                Position.of(Horizontal.B, Vertical.FIVE),
                Position.of(Horizontal.B, Vertical.SIX),
                Position.of(Horizontal.B, Vertical.SEVEN),
                Position.of(Horizontal.B, Vertical.EIGHT),

                Position.of(Horizontal.C, Vertical.ONE),
                Position.of(Horizontal.D, Vertical.ONE),
                Position.of(Horizontal.E, Vertical.ONE),
                Position.of(Horizontal.F, Vertical.ONE),
                Position.of(Horizontal.G, Vertical.ONE),
                Position.of(Horizontal.H, Vertical.ONE),

                Position.of(Horizontal.A, Vertical.ONE)
        );
    }

    @Test
    @DisplayName("비어있는 좌표로 룩을 이동하는 경우")
    void canMove() {
        Rook rook = new Rook(Team.WHITE);
        assertTrue(rook.isMovable(Position.of(Horizontal.A, Vertical.ONE), Position.of(Horizontal.A, Vertical.FOUR),
                board));
    }

    @Test
    @DisplayName("길목에 다른 기물이 있는 경우")
    void canMoveImpossible() {
        Rook rook = new Rook(Team.WHITE);
        assertFalse(rook.isMovable(Position.of(Horizontal.A, Vertical.ONE),
                Position.of(Horizontal.A, Vertical.EIGHT), board));
    }

    @Test
    @DisplayName("도착지에 상대편 기물이 있는 경우")
    void canMoveToSideTeamPiecePosition() {
        Rook rook = new Rook(Team.WHITE);
        assertTrue(rook.isMovable(Position.of(Horizontal.A, Vertical.ONE),
                Position.of(Horizontal.A, Vertical.SEVEN), board));
    }

    @Test
    @DisplayName("도착지에 우리편 기물이 있는 경우")
    void canMoveToSameTeamPiecePosition() {
        Rook rook = new Rook(Team.WHITE);
        assertFalse(rook.isMovable(Position.of(Horizontal.A, Vertical.ONE),
                Position.of(Horizontal.C, Vertical.ONE), board));
    }
}