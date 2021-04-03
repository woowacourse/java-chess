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

class KnightTest {
    Board board;
    Map<Position, Piece> testBoard = new LinkedHashMap<>();

    @BeforeEach
    void setUp() {
        for (Position position : Position.getPositions()) {
            testBoard.put(position, null);
        }
        testBoard.put(Position.of(Horizontal.D, Vertical.FOUR), new Knight(Team.WHITE));
        testBoard.put(Position.of(Horizontal.B, Vertical.FIVE), new Pawn(Team.WHITE));
        testBoard.put(Position.of(Horizontal.F, Vertical.THREE), new Rook(Team.BLACK));
        testBoard.put(Position.of(Horizontal.D, Vertical.FIVE), new Rook(Team.BLACK));
        testBoard.put(Position.of(Horizontal.D, Vertical.SIX), new Rook(Team.BLACK));
        testBoard.put(Position.of(Horizontal.E, Vertical.FOUR), new Rook(Team.BLACK));
        testBoard.put(Position.of(Horizontal.E, Vertical.FIVE), new Rook(Team.BLACK));

        board = new Board(testBoard);
    }

    @Test
    @DisplayName("나이트 움직일 수 있는 좌표 확인")
    void movablePositionTest() {
        Knight knight = new Knight(Team.BLACK);
        List<Position> positions = knight.searchMovablePositions(Position.of(Horizontal.B, Vertical.ONE));

        assertThat(positions).containsExactly(
                Position.of(Horizontal.C, Vertical.THREE),
                Position.of(Horizontal.D, Vertical.TWO),
                Position.of(Horizontal.A, Vertical.THREE)
        );
    }

    @Test
    @DisplayName("비어있는 좌표로 나이트를 이동하는 경우")
    void canMove() {
        Knight knight = new Knight(Team.WHITE);
        assertTrue(knight.isMovable(Position.of(Horizontal.D, Vertical.FOUR),
                Position.of(Horizontal.E, Vertical.SIX), board));
    }

    @Test
    @DisplayName("도착지에 상대편 기물이 있는 경우")
    void canMoveToSideTeamPiecePosition() {
        Knight knight = new Knight(Team.WHITE);
        assertTrue(knight.isMovable(Position.of(Horizontal.D, Vertical.FOUR),
                Position.of(Horizontal.F, Vertical.THREE), board));
    }

    @Test
    @DisplayName("도착지에 우리편 기물이 있는 경우")
    void canMoveToSameTeamPiecePosition() {
        Knight knight = new Knight(Team.WHITE);
        assertFalse(knight.isMovable(Position.of(Horizontal.D, Vertical.FOUR),
                Position.of(Horizontal.B, Vertical.FIVE), board));
    }
}