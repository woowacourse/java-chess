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

public class RookMoveStrategyTest {
    private MoveStrategy rookStrategy;
    private Board board;

    @BeforeEach
    private void setUp() {
        rookStrategy = new RookMoveStrategy();
        Map<Position, Piece> emptyBoard = new HashMap<>();
        board = new Board(emptyBoard);
    }

    @DisplayName("룩의 상단 직진 이동")
    @Test
    void upTest() {
        Position source = Position.of("a1");
        Position target = Position.of("a6");

        Assertions.assertThat(rookStrategy.movable(source, target, board)).isTrue();
    }

    @DisplayName("룩의 하단 직진 이동")
    @Test
    void downTest() {
        Position source = Position.of("a6");
        Position target = Position.of("a1");

        Assertions.assertThat(rookStrategy.movable(source, target, board)).isTrue();
    }

    @DisplayName("룩의 우측 직진 이동")
    @Test
    void rightTest() {
        Position source = Position.of("a1");
        Position target = Position.of("g1");

        Assertions.assertThat(rookStrategy.movable(source, target, board)).isTrue();
    }

    @DisplayName("룩의 좌측 직진 이동")
    @Test
    void leftTest() {
        Position source = Position.of("g1");
        Position target = Position.of("a1");

        Assertions.assertThat(rookStrategy.movable(source, target, board)).isTrue();
    }

    @DisplayName("타겟 위치에 적이 있을 때 이동 테스트")
    @Test
    void moveWhenEnemyTest() {
        Piece rook = new Piece(PieceType.ROOK, Team.BLACK);
        Piece enemy = new Piece(PieceType.PAWN, Team.WHITE);
        Position source = Position.of("a1");
        Position target = Position.of("a6");
        Map<Position, Piece> enemyEntry = new HashMap<>();
        enemyEntry.put(source, rook);
        enemyEntry.put(target, enemy);
        Board enemyBoard = new Board(enemyEntry);

        Assertions.assertThat(rookStrategy.movable(source, target, enemyBoard)).isTrue();
    }
}
