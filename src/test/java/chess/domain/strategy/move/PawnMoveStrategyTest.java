package chess.domain.strategy.move;

import chess.domain.board.Board;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.piece.Team;
import chess.domain.position.Position;
import chess.domain.position.Positions;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class PawnMoveStrategyTest {
    private MoveStrategy pawnStrategy;

    @BeforeEach
    private void setUp() {
        pawnStrategy = new PawnMoveStrategy();
    }

    @DisplayName("폰이 시작 위치일 때 1칸 또는 2칸 이동")
    @Test
    void moveAtInitialPosition() {
        Piece whitePawn = new Piece(PieceType.PAWN, Team.WHITE);
        Piece blackPawn = new Piece(PieceType.PAWN, Team.BLACK);
        Position whiteInitial = Positions.of("a2");
        Position blackInitial = Positions.of("a7");
        Map<Position, Piece> emptyBoard = new HashMap<>();
        emptyBoard.put(whiteInitial, whitePawn);
        emptyBoard.put(blackInitial, blackPawn);
        Board board = new Board(emptyBoard);

        Position whiteOne = Positions.of("a3");
        Position whiteTwo = Positions.of("a4");
        Position blackOne = Positions.of("a6");
        Position blackTwo = Positions.of("a5");

        Assertions.assertThat(pawnStrategy.movable(whiteInitial, whiteOne, board)).isTrue();
        Assertions.assertThat(pawnStrategy.movable(whiteInitial, whiteTwo, board)).isTrue();
        Assertions.assertThat(pawnStrategy.movable(blackInitial, blackOne, board)).isTrue();
        Assertions.assertThat(pawnStrategy.movable(blackInitial, blackTwo, board)).isTrue();
    }

    @DisplayName("폰이 시작 위치가 아닐 때 1칸 전진")
    @Test
    void moveAtNoneInitialPosition() {
        Piece whitePawn = new Piece(PieceType.PAWN, Team.WHITE);
        Piece blackPawn = new Piece(PieceType.PAWN, Team.BLACK);
        Position whiteSource = Positions.of("a3");
        Position blackSource = Positions.of("a6");
        Map<Position, Piece> emptyBoard = new HashMap<>();
        emptyBoard.put(whiteSource, whitePawn);
        emptyBoard.put(blackSource, blackPawn);
        Board board = new Board(emptyBoard);

        Position upTarget = Positions.of("a4");
        Position downTarget = Positions.of("a5");

        Assertions.assertThat(pawnStrategy.movable(whiteSource, upTarget, board)).isTrue();
        Assertions.assertThat(pawnStrategy.movable(blackSource, downTarget, board)).isTrue();
    }

    @DisplayName("폰이 시작 위치가 아닐 때 대각선의 적을 공격 가능")
    @Test
    void moveDiagonalTest() {
        Piece whitePawn = new Piece(PieceType.PAWN, Team.WHITE);
        Piece whiteLeftEnemy = new Piece(PieceType.PAWN, Team.BLACK);
        Piece whiteRightEnemy = new Piece(PieceType.PAWN, Team.BLACK);

        Piece blackPawn = new Piece(PieceType.PAWN, Team.BLACK);
        Piece blackLeftEnemy = new Piece(PieceType.PAWN, Team.WHITE);
        Piece blackRightEnemy = new Piece(PieceType.PAWN, Team.WHITE);

        Position whiteSource = Positions.of("d3");
        Position whiteDiagonalLeft = Positions.of("c4");
        Position whiteDiagonalRight = Positions.of("e4");

        Position blackSource = Positions.of("d6");
        Position blackDiagonalLeft = Positions.of("c5");
        Position blackDiagonalRight = Positions.of("e5");

        Map<Position, Piece> entry = new HashMap<>();
        entry.put(whiteSource, whitePawn);
        entry.put(whiteDiagonalLeft, whiteLeftEnemy);
        entry.put(whiteDiagonalRight, whiteRightEnemy);
        entry.put(blackSource, blackPawn);
        entry.put(blackDiagonalLeft, blackLeftEnemy);
        entry.put(blackDiagonalRight, blackRightEnemy);
        Board board = new Board(entry);

        Assertions.assertThat(pawnStrategy.movable(whiteSource, whiteDiagonalLeft, board)).isTrue();
        Assertions.assertThat(pawnStrategy.movable(whiteSource, whiteDiagonalRight, board)).isTrue();
        Assertions.assertThat(pawnStrategy.movable(blackSource, blackDiagonalLeft, board)).isTrue();
        Assertions.assertThat(pawnStrategy.movable(blackSource, blackDiagonalRight, board)).isTrue();
    }
}
