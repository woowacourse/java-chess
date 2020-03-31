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

public class KnightMoveStrategyTest {
    private MoveStrategy knightStrategy;

    @BeforeEach
    private void setUp() {
        knightStrategy = new KnightMoveStrategy();
    }

    @DisplayName("나이트의 이동 가능 위치에 이동")
    @Test
    void moveTest() {
        Position source = Positions.of("d4");
        Map<Position, Piece> emptyBoard = new HashMap<>();
        Board board = new Board(emptyBoard);

        Position upLeft = Positions.of("c6");
        Position upRight = Positions.of("e6");
        Position downLeft = Positions.of("c2");
        Position downRight = Positions.of("e2");
        Position leftUp = Positions.of("b5");
        Position leftDown = Positions.of("b3");
        Position rightUp = Positions.of("f5");
        Position rightDown = Positions.of("f3");

        Assertions.assertThat(knightStrategy.movable(source, upLeft, board)).isTrue();
        Assertions.assertThat(knightStrategy.movable(source, upRight, board)).isTrue();
        Assertions.assertThat(knightStrategy.movable(source, downLeft, board)).isTrue();
        Assertions.assertThat(knightStrategy.movable(source, downRight, board)).isTrue();
        Assertions.assertThat(knightStrategy.movable(source, leftUp, board)).isTrue();
        Assertions.assertThat(knightStrategy.movable(source, leftDown, board)).isTrue();
        Assertions.assertThat(knightStrategy.movable(source, rightUp, board)).isTrue();
        Assertions.assertThat(knightStrategy.movable(source, rightDown, board)).isTrue();
    }

    @DisplayName("타겟 위치에 적이 있을 때 이동")
    @Test
    void moveWhenEnemyTest() {
        Piece knight = new Piece(PieceType.KING, Team.BLACK);
        Piece enemy = new Piece(PieceType.PAWN, Team.WHITE);
        Position source = Positions.of("d4");
        Position target = Positions.of("e6");
        Map<Position, Piece> enemyEntry = new HashMap<>();
        enemyEntry.put(source, knight);
        enemyEntry.put(target, enemy);
        Board enemyBoard = new Board(enemyEntry);

        Assertions.assertThat(knightStrategy.movable(source, target, enemyBoard)).isTrue();
    }
}
