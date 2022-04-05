package chess.domain.piece.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.piece.MovePath;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import chess.domain.board.Direction;
import chess.domain.board.Position;

class MovePathTest {

    @Test
    @DisplayName("기물의 이동에 관한 경로를 생성할 수 있다.")
    void createMovePath() {
        assertThat(new MovePath(Position.valueOf("a1"), Position.valueOf("a2"), Direction.NORTH))
                .isEqualTo(new MovePath(Position.valueOf("a1"), Position.valueOf("a2"), Direction.NORTH));
    }

    @Test
    @DisplayName("기물의 이동에 관한 경로의 시작지점을 한 칸 움직일 수 있다.")
    void movePathOfPositionToHasNext() {
        final MovePath movePath = new MovePath(Position.valueOf("a1"), Position.valueOf("a2"), Direction.NORTH);

        assertThat(movePath.next())
                .isEqualTo(Position.valueOf("a2"));
    }

    @Test
    @DisplayName("기물의 시작지점과 다음 도착지점의 같을 경우 false를 반환 할 수 있다.")
    void returnFalseOfSourcePositionAndTargetPositionIsSame() {
        final MovePath movePath = new MovePath(Position.valueOf("a2"), Position.valueOf("a3"), Direction.NORTH);

        assertThat(movePath.hasNext()).isFalse();
    }
}
