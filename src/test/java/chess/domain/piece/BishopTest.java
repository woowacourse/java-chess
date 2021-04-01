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

class BishopTest {
    Board board;
    Map<Position, Piece> testBoard = new LinkedHashMap<>();

    @BeforeEach
    void setUp() {
        for (Position position : Position.getPositions()) {
            testBoard.put(position, null);
        }
        testBoard.put(Position.of(Horizontal.D, Vertical.FOUR), new Bishop(Team.WHITE));
        testBoard.put(Position.of(Horizontal.A, Vertical.ONE), new Pawn(Team.WHITE));
        testBoard.put(Position.of(Horizontal.F, Vertical.SIX), new Rook(Team.BLACK));

        board = new Board(testBoard);
    }

    @Test
    @DisplayName("비숍 움직일 수 있는 좌표 확인")
    void movablePositionsTest() {
        Bishop bishop = new Bishop(Team.BLACK);
        List<Position> positions = bishop.searchMovablePositions(Position.of(Horizontal.D, Vertical.FOUR));

        assertThat(positions).containsExactly(
                Position.of(Horizontal.E, Vertical.FIVE),
                Position.of(Horizontal.F, Vertical.SIX),
                Position.of(Horizontal.G, Vertical.SEVEN),
                Position.of(Horizontal.H, Vertical.EIGHT),
                Position.of(Horizontal.C, Vertical.FIVE),
                Position.of(Horizontal.B, Vertical.SIX),
                Position.of(Horizontal.A, Vertical.SEVEN),
                Position.of(Horizontal.C, Vertical.THREE),
                Position.of(Horizontal.B, Vertical.TWO),
                Position.of(Horizontal.A, Vertical.ONE),
                Position.of(Horizontal.E, Vertical.THREE),
                Position.of(Horizontal.F, Vertical.TWO),
                Position.of(Horizontal.G, Vertical.ONE)
        );
    }

    @Test
    @DisplayName("비숍 모서리에서 움직일 수 있는 좌표 확인")
    void movablePositionsWhenBorderTest() {
        Bishop bishop = new Bishop(Team.BLACK);
        List<Position> positions = bishop.searchMovablePositions(Position.of(Horizontal.A, Vertical.ONE));

        assertThat(positions).containsExactly(
                Position.of(Horizontal.B, Vertical.TWO),
                Position.of(Horizontal.C, Vertical.THREE),
                Position.of(Horizontal.D, Vertical.FOUR),
                Position.of(Horizontal.E, Vertical.FIVE),
                Position.of(Horizontal.F, Vertical.SIX),
                Position.of(Horizontal.G, Vertical.SEVEN),
                Position.of(Horizontal.H, Vertical.EIGHT)
        );
    }

    @Test
    @DisplayName("비어있는 좌표로 비숍을 이동하는 경우")
    void canMove() {
        Bishop bishop = new Bishop(Team.WHITE);
        assertTrue(bishop.isMovable(Position.of(Horizontal.D, Vertical.FOUR),
                Position.of(Horizontal.B, Vertical.TWO), board));
    }

    @Test
    @DisplayName("길목에 다른 기물이 있는 경우")
    void canMoveImpossible() {
        Bishop bishop = new Bishop(Team.WHITE);
        assertFalse(bishop.isMovable(Position.of(Horizontal.D, Vertical.FOUR),
                Position.of(Horizontal.G, Vertical.SEVEN), board));
    }

    @Test
    @DisplayName("도착지에 상대편 기물이 있는 경우")
    void canMoveToSideTeamPiecePosition() {
        Bishop bishop = new Bishop(Team.WHITE);
        assertTrue(bishop.isMovable(Position.of(Horizontal.D, Vertical.FOUR),
                Position.of(Horizontal.F, Vertical.SIX), board));
    }

    @Test
    @DisplayName("도착지에 우리편 기물이 있는 경우")
    void canMoveToSameTeamPiecePosition() {
        Bishop bishop = new Bishop(Team.WHITE);
        assertFalse(bishop.isMovable(Position.of(Horizontal.D, Vertical.FOUR),
                Position.of(Horizontal.A, Vertical.ONE), board));
    }
}