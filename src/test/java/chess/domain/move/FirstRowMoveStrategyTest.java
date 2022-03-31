package chess.domain.move;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.piece.Blank;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Team;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class FirstRowMoveStrategyTest {

    class FirstRowMoveStrategyForTest extends FirstRowMoveStrategy {

        @Override
        public boolean isMovable(Board board, Position source, Position target) {
            return false;
        }

        @Override
        public boolean isMovableToTarget(Piece targetPiece, Team sourceTeam) {
            return super.isMovableToTarget(targetPiece, sourceTeam);
        }
    }

    private FirstRowMoveStrategyForTest firstRowMoveStrategyForTest;

    @BeforeEach
    void setUp() {
        firstRowMoveStrategyForTest = new FirstRowMoveStrategyForTest();
    }

    @Test
    @DisplayName("target 위치에 기물이 없기 때문에 기물을 움직일 수 있다.")
    void isMovableToTarget_WhenBlank() {
        assertThat(firstRowMoveStrategyForTest.isMovableToTarget(new Blank(), Team.BLACK)).isTrue();
    }

    @Test
    @DisplayName("target 위치에 다른편 기물이 있기 때문에 기물을 움직일 수 있다.")
    void isMovableToTarget_WhenExistSameTeam() {
        assertThat(firstRowMoveStrategyForTest.isMovableToTarget(new Queen(Team.BLACK), Team.WHITE)).isTrue();
    }

    @Test
    @DisplayName("target 위치에 같은편 기물이 있기 때문에 기물을 움직일 수 없다.")
    void isMovableToTarget_WhenExistOppositeTeam() {
        assertThat(firstRowMoveStrategyForTest.isMovableToTarget(new Queen(Team.BLACK), Team.BLACK)).isFalse();
    }
}