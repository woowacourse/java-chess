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

class KingTest {
    Board board;
    Map<Position, Piece> testBoard = new LinkedHashMap<>();

    @BeforeEach
    void setUp() {
        for (Position position : Position.getPositions()) {
            testBoard.put(position, null);
        }
        testBoard.put(Position.of(Horizontal.D, Vertical.FOUR), new King(Team.WHITE));
        testBoard.put(Position.of(Horizontal.C, Vertical.FOUR), new Pawn(Team.WHITE));
        testBoard.put(Position.of(Horizontal.C, Vertical.THREE), new Rook(Team.BLACK));

        board = new Board(testBoard);
    }

    @Test
    @DisplayName("킹 움직일 수 있는 좌표 확인")
    void movablePositionTest() {
        King king = new King(Team.BLACK);
        List<Position> positions = king.searchMovablePositions(Position.of(Horizontal.B, Vertical.ONE));

        assertThat(positions).containsExactly(
                Position.of(Horizontal.C, Vertical.TWO),
                Position.of(Horizontal.A, Vertical.TWO),

                Position.of(Horizontal.B, Vertical.TWO),
                Position.of(Horizontal.C, Vertical.ONE),
                Position.of(Horizontal.A, Vertical.ONE)

        );
    }

    @Test
    @DisplayName("비어있는 좌표로 퀸을 이동하는 경우")
    void canMove() {
        King king = new King(Team.WHITE);
        assertTrue(king.isMovable(Position.of(Horizontal.D, Vertical.FOUR),
                Position.of(Horizontal.D, Vertical.FIVE), board));
    }

    @Test
    @DisplayName("도착지에 상대편 기물이 있는 경우")
    void canMoveToSideTeamPiecePosition() {
        King king = new King(Team.WHITE);
        assertTrue(king.isMovable(Position.of(Horizontal.D, Vertical.FOUR),
                Position.of(Horizontal.C, Vertical.THREE), board));
    }

    @Test
    @DisplayName("도착지에 우리편 기물이 있는 경우")
    void canMoveToSameTeamPiecePosition() {
        King king = new King(Team.WHITE);
        assertFalse(king.isMovable(Position.of(Horizontal.D, Vertical.FOUR),
                Position.of(Horizontal.C, Vertical.FOUR), board));
    }
}