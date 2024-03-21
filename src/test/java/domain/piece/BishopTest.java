package domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import domain.board.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BishopTest {

    @Test
    @DisplayName("오른쪽 위 대각선 방향으로 비숍 이동 가능")
    void canMove_RightUp() {
        Bishop bishop = new Bishop(Color.WHITE);
        Position position1 = Position.of(1, 3);
        Position position2 = Position.of(6, 8);
        assertThat(bishop.canMove(position1, position2)).isTrue();
    }

    @Test
    @DisplayName("오른쪽 아래 대각선 방향으로 비숍 이동 가능")
    void canMove_RightDown() {
        Bishop bishop = new Bishop(Color.WHITE);
        Position position1 = Position.of(4, 6);
        Position position2 = Position.of(2, 8);
        assertThat(bishop.canMove(position1, position2)).isTrue();
    }

    @Test
    @DisplayName("왼쪽 위 대각선 방향으로 비숍 이동 가능")
    void canMove_LeftUp() {
        Bishop bishop = new Bishop(Color.WHITE);
        Position position1 = Position.of(3, 5);
        Position position2 = Position.of(1, 7);
        assertThat(bishop.canMove(position1, position2)).isTrue();
    }

    @Test
    @DisplayName("왼쪽 아래 대각선 방향으로 비숍 이동 가능")
    void canMove_LeftDown() {
        Bishop bishop = new Bishop(Color.WHITE);
        Position position1 = Position.of(3, 5);
        Position position2 = Position.of(1, 3);
        assertThat(bishop.canMove(position1, position2)).isTrue();
    }

    @Test
    @DisplayName("같은 대각선상이 아니면 비숍 이동 불가")
    void cannotMove() {
        Bishop bishop = new Bishop(Color.WHITE);
        Position position1 = Position.of(1, 1);
        Position position2 = Position.of(7, 8);
        assertThat(bishop.canMove(position1, position2)).isFalse();
    }

    @Test
    @DisplayName("같은 위치로는 이동 불가")
    void cannotMove_SamePosition() {
        Bishop bishop = new Bishop(Color.WHITE);
        Position position1 = Position.of(1, 1);
        Position position2 = Position.of(1, 1);
        assertThat(bishop.canMove(position1, position2)).isFalse();
    }
}
