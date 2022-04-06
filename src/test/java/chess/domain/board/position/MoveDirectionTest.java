package chess.domain.board.position;

import static chess.domain.board.position.File.A;
import static chess.domain.board.position.File.B;
import static chess.domain.board.position.MoveDirection.RIGHT;
import static chess.domain.board.position.MoveDirection.TWO_DOWN_ONE_RIGHT;
import static chess.domain.board.position.MoveDirection.UP_LEFT;
import static chess.domain.board.position.Rank.EIGHT;
import static chess.domain.board.position.Rank.ONE;
import static chess.domain.board.position.Rank.SIX;
import static chess.domain.board.position.Rank.TWO;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MoveDirectionTest {

    @Test
    @DisplayName("1칸 오른쪽 이동을 검증한다")
    void right() {
        Position from = Position.of(A, ONE);
        Position to = Position.of(B, ONE);

        assertMoveDirectionCreation(from, to, RIGHT);
    }

    @Test
    @DisplayName("1칸 위, 1칸 왼쪽의 이동을 검증한다")
    void up_left() {
        Position from = Position.of(B, ONE);
        Position to = Position.of(A, TWO);

        assertMoveDirectionCreation(from, to, UP_LEFT);
    }

    @Test
    @DisplayName("2칸 아래, 1칸 오른쪽의 이동을 검증한다")
    void two_down_one_right() {
        Position from = Position.of(A, EIGHT);
        Position to = Position.of(B, SIX);

        assertMoveDirectionCreation(from, to, TWO_DOWN_ONE_RIGHT);
    }

    private void assertMoveDirectionCreation(Position from, Position to, MoveDirection direction) {
        assertThat(MoveDirection.of(from, to)).isEqualTo(direction);
    }
}
