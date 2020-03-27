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

public class KingMoveStrategyTest {
    private MoveStrategy kingStrategy;

    @BeforeEach
    private void setUp() {
        kingStrategy = new KingMoveStrategy();
    }

    @DisplayName("킹의 이동 가능 위치에 대해 이동 테스트")
    @Test
    void moveTest() {
        Map<Position, Piece> emptyBoard = new HashMap<>();
        Board board = new Board(emptyBoard);
        Position source = Positions.of("d4");

        Position up = Positions.of("d5");
        Position down = Positions.of("d3");
        Position left = Positions.of("c4");
        Position right = Positions.of("e4");
        Position rightUp = Positions.of("e5");
        Position rightDown = Positions.of("e3");
        Position leftUp = Positions.of("c5");
        Position leftDown = Positions.of("c3");

        Assertions.assertThat(kingStrategy.movable(source, up, board)).isTrue();
        Assertions.assertThat(kingStrategy.movable(source, down, board)).isTrue();
        Assertions.assertThat(kingStrategy.movable(source, left, board)).isTrue();
        Assertions.assertThat(kingStrategy.movable(source, right, board)).isTrue();
        Assertions.assertThat(kingStrategy.movable(source, rightUp, board)).isTrue();
        Assertions.assertThat(kingStrategy.movable(source, rightDown, board)).isTrue();
        Assertions.assertThat(kingStrategy.movable(source, leftUp, board)).isTrue();
        Assertions.assertThat(kingStrategy.movable(source, leftDown, board)).isTrue();
    }

    @DisplayName("타겟 위치에 적이 있을 때 이동")
    @Test
    void moveWhenEnemyTest() {
        Piece king = new Piece(PieceType.KING, Team.BLACK);
        Piece enemy = new Piece(PieceType.PAWN, Team.WHITE);
        Position source = Positions.of("d4");
        Position target = Positions.of("e5");
        Map<Position, Piece> enemyEntry = new HashMap<>();
        enemyEntry.put(source, king);
        enemyEntry.put(target, enemy);
        Board enemyBoard = new Board(enemyEntry);

        Assertions.assertThat(kingStrategy.movable(source, target, enemyBoard)).isTrue();
    }
}
