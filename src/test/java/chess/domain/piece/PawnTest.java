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

class PawnTest {
    Board board;
    Map<Position, Piece> testBoard = new LinkedHashMap<>();

    @BeforeEach
    void setUp() {
        for (Position position : Position.getPositions()) {
            testBoard.put(position, null);
        }
        testBoard.put(Position.of(Horizontal.B, Vertical.TWO), new Pawn(Team.WHITE));
        testBoard.put(Position.of(Horizontal.D, Vertical.TWO), new Pawn(Team.WHITE));
        testBoard.put(Position.of(Horizontal.F, Vertical.TWO), new Pawn(Team.WHITE));
        testBoard.put(Position.of(Horizontal.A, Vertical.THREE), new Rook(Team.BLACK));
        testBoard.put(Position.of(Horizontal.B, Vertical.THREE), new Rook(Team.BLACK));
        testBoard.put(Position.of(Horizontal.C, Vertical.THREE), new Rook(Team.WHITE));
        testBoard.put(Position.of(Horizontal.D, Vertical.FOUR), new Rook(Team.BLACK));

        board = new Board(testBoard);
    }

    @Test
    @DisplayName("블랙 폰인데 첫 이동일 경우")
    void blackPawnFirstMove() {
        Pawn pawn = new Pawn(Team.BLACK);
        List<Position> positions = pawn.searchMovablePositions(Position.of(Horizontal.A, Vertical.SEVEN));
        assertThat(positions).containsExactly(
                Position.of(Horizontal.A, Vertical.SIX),
                Position.of(Horizontal.B, Vertical.SIX),
                Position.of(Horizontal.A, Vertical.FIVE)
        );
    }

    @Test
    @DisplayName("블랙 폰인데 첫 이동이 아닐 경우")
    void blackPawnNotFirstMove() {
        Pawn pawn = new Pawn(Team.BLACK);
        List<Position> positions = pawn.searchMovablePositions(Position.of(Horizontal.A, Vertical.SIX));
        assertThat(positions).containsExactly(
                Position.of(Horizontal.A, Vertical.FIVE),
                Position.of(Horizontal.B, Vertical.FIVE)
        );
    }

    @Test
    @DisplayName("화이트 폰인데 첫 이동일 경우")
    void whitePawnFirstMove() {
        Pawn pawn = new Pawn(Team.WHITE);
        List<Position> positions = pawn.searchMovablePositions(Position.of(Horizontal.A, Vertical.TWO));
        assertThat(positions).containsExactly(
                Position.of(Horizontal.A, Vertical.THREE),
                Position.of(Horizontal.B, Vertical.THREE),
                Position.of(Horizontal.A, Vertical.FOUR)
        );
    }

    @Test
    @DisplayName("화이트 폰인데 첫 이동이 아닐 경우")
    void whitePawnNotFirstMove() {
        Pawn pawn = new Pawn(Team.WHITE);
        List<Position> positions = pawn.searchMovablePositions(Position.of(Horizontal.A, Vertical.SEVEN));
        assertThat(positions).containsExactly(
                Position.of(Horizontal.A, Vertical.EIGHT),
                Position.of(Horizontal.B, Vertical.EIGHT)
        );
    }

    @Test
    @DisplayName("대각선 이동인데 상대기물이 있는 경우")
    void pawnDiagonalMoveWhenDifferentTeamPieceExistTest() {
        Pawn pawn = new Pawn(Team.WHITE);
        assertTrue(pawn.isMovable(Position.of(Horizontal.B, Vertical.TWO),
                Position.of(Horizontal.A, Vertical.THREE), board));
    }

    @Test
    @DisplayName("대각선 이동인데 같은 팀 기물이 있는 경우")
    void pawnDiagonalMoveWhenSameTeamPieceExistTest() {
        Pawn pawn = new Pawn(Team.WHITE);
        assertFalse(pawn.isMovable(Position.of(Horizontal.B, Vertical.TWO),
                Position.of(Horizontal.C, Vertical.THREE), board));
    }

    @Test
    @DisplayName("1칸 직진 이동인데 기물이 있는 경우")
    void pawnGoForwardOneStepWhenOtherPieceExistTest() {
        Pawn pawn = new Pawn(Team.WHITE);
        assertFalse(pawn.isMovable(Position.of(Horizontal.B, Vertical.TWO),
                Position.of(Horizontal.B, Vertical.THREE), board));
    }

    @Test
    @DisplayName("2칸 직진 이동인데 기물이 있는 경우")
    void pawnGoForwardTwoStepWhenOtherPieceExistTest() {
        Pawn pawn = new Pawn(Team.WHITE);
        assertFalse(pawn.isMovable(Position.of(Horizontal.D, Vertical.TWO),
                Position.of(Horizontal.D, Vertical.FOUR), board));
    }

    @Test
    @DisplayName("대각선 이동인데 기물이 없는 경우")
    void pawnDiagonalMoveWhenPieceNotExistTest() {
        Pawn pawn = new Pawn(Team.WHITE);
        assertFalse(pawn.isMovable(Position.of(Horizontal.D, Vertical.TWO),
                Position.of(Horizontal.E, Vertical.THREE), board));
    }

    @Test
    @DisplayName("1칸 직진 이동인데 기물이 없는 경우")
    void pawnGoForwardOneStepTest() {
        Pawn pawn = new Pawn(Team.WHITE);
        assertFalse(pawn.isMovable(Position.of(Horizontal.F, Vertical.TWO),
                Position.of(Horizontal.D, Vertical.THREE), board));
    }

    @Test
    @DisplayName("2칸 직진 이동인데 기물이 없는 경우")
    void pawnGoForwardTwoStepTest() {
        Pawn pawn = new Pawn(Team.WHITE);
        assertFalse(pawn.isMovable(Position.of(Horizontal.F, Vertical.TWO),
                Position.of(Horizontal.D, Vertical.FOUR), board));
    }

}