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

class QueenTest {
    Board board;
    Map<Position, Piece> testBoard = new LinkedHashMap<>();

    @BeforeEach
    void setUp() {
        for (Position position : Position.getPositions()) {
            testBoard.put(position, null);
        }
        testBoard.put(Position.of(Horizontal.D, Vertical.FOUR), new Queen(Team.WHITE));
        testBoard.put(Position.of(Horizontal.B, Vertical.FOUR), new Pawn(Team.WHITE));
        testBoard.put(Position.of(Horizontal.F, Vertical.SIX), new Rook(Team.BLACK));

        board = new Board(testBoard);
    }

    @Test
    @DisplayName("퀸 움직일 수 있는 좌표 확인")
    void movablePositionTest() {
        Queen queen = new Queen(Team.BLACK);
        List<Position> positions = queen.searchMovablePositions(Position.of(Horizontal.B, Vertical.ONE));

        assertThat(positions).containsExactly(
                Position.of(Horizontal.C, Vertical.TWO),
                Position.of(Horizontal.D, Vertical.THREE),
                Position.of(Horizontal.E, Vertical.FOUR),
                Position.of(Horizontal.F, Vertical.FIVE),
                Position.of(Horizontal.G, Vertical.SIX),
                Position.of(Horizontal.H, Vertical.SEVEN),

                Position.of(Horizontal.A, Vertical.TWO),

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
    @DisplayName("비어있는 좌표로 퀸을 이동하는 경우")
    void canMove() {
        Queen queen = new Queen(Team.WHITE);
        assertTrue(queen.isMovable(Position.of(Horizontal.D, Vertical.FOUR),
                Position.of(Horizontal.C, Vertical.FOUR), board));
    }

    @Test
    @DisplayName("길목에 다른 기물이 있는 경우")
    void canMoveImpossible() {
        Queen queen = new Queen(Team.WHITE);
        assertFalse(queen.isMovable(Position.of(Horizontal.D, Vertical.FOUR),
                Position.of(Horizontal.G, Vertical.SEVEN), board));
    }

    @Test
    @DisplayName("도착지에 상대편 기물이 있는 경우")
    void canMoveToSideTeamPiecePosition() {
        Queen queen = new Queen(Team.WHITE);
        assertTrue(queen.isMovable(Position.of(Horizontal.D, Vertical.FOUR),
                Position.of(Horizontal.F, Vertical.SIX), board));
    }

    @Test
    @DisplayName("도착지에 우리편 기물이 있는 경우")
    void canMoveToSameTeamPiecePosition() {
        Queen queen = new Queen(Team.WHITE);
        assertFalse(queen.isMovable(Position.of(Horizontal.D, Vertical.FOUR),
                Position.of(Horizontal.B, Vertical.FOUR), board));
    }
}