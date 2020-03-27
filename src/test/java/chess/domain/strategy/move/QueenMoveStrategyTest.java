package chess.domain.strategy.move;

import chess.domain.board.Board;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.piece.Team;
import chess.domain.position.Position;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class QueenMoveStrategyTest {
    private MoveStrategy queenStrategy;
    private Board board;

    @BeforeEach
    private void setUp() {
        queenStrategy = new QueenMoveStrategy();
        Map<Position, Piece> emptyBoard = new HashMap<>();
        board = new Board(emptyBoard);
    }

    @DisplayName("퀸의 우 상단 대각 이동")
    @Test
    void rightUpTest() {
        Position source = Position.of("a1");
        Position target = Position.of("e5");

        Assertions.assertThat(queenStrategy.movable(source, target, board)).isTrue();
    }

    @DisplayName("퀸의 우 하단 대각 이동")
    @Test
    void rightDownTest() {
        Position source = Position.of("a7");
        Position target = Position.of("e3");

        Assertions.assertThat(queenStrategy.movable(source, target, board)).isTrue();
    }

    @DisplayName("퀸의 좌 상단 대각 이동")
    @Test
    void leftUpTest() {
        Position source = Position.of("g1");
        Position target = Position.of("c5");

        Assertions.assertThat(queenStrategy.movable(source, target, board)).isTrue();
    }

    @DisplayName("퀸의 좌 하단 대각 이동")
    @Test
    void leftDownTest() {
        Position source = Position.of("g7");
        Position target = Position.of("c3");

        Assertions.assertThat(queenStrategy.movable(source, target, board)).isTrue();
    }

    @DisplayName("퀸의 상단 직진 이동")
    @Test
    void upTest() {
        Position source = Position.of("a1");
        Position target = Position.of("a6");

        Assertions.assertThat(queenStrategy.movable(source, target, board)).isTrue();
    }

    @DisplayName("퀸의 하단 직진 이동")
    @Test
    void downTest() {
        Position source = Position.of("a6");
        Position target = Position.of("a1");

        Assertions.assertThat(queenStrategy.movable(source, target, board)).isTrue();
    }

    @DisplayName("퀸의 우측 직진 이동")
    @Test
    void rightTest() {
        Position source = Position.of("a1");
        Position target = Position.of("g1");

        Assertions.assertThat(queenStrategy.movable(source, target, board)).isTrue();
    }

    @DisplayName("퀸의 좌측 직진 이동")
    @Test
    void leftTest() {
        Position source = Position.of("g1");
        Position target = Position.of("a1");

        Assertions.assertThat(queenStrategy.movable(source, target, board)).isTrue();
    }

    @DisplayName("타겟 위치에 적이 있을 때 이동")
    @Test
    void moveWhenEnemyTest() {
        Piece queen = new Piece(PieceType.QUEEN, Team.BLACK);
        Piece enemy = new Piece(PieceType.PAWN, Team.WHITE);
        Position source = Position.of("a1");
        Position target = Position.of("a6");
        Map<Position, Piece> enemyEntry = new HashMap<>();
        enemyEntry.put(source, queen);
        enemyEntry.put(target, enemy);
        Board enemyBoard = new Board(enemyEntry);

        Assertions.assertThat(queenStrategy.movable(source, target, enemyBoard)).isTrue();
    }
}
