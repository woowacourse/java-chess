package domain.piece;


import static org.assertj.core.api.Assertions.assertThat;

import domain.board.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class KnightTest {

    @Test
    @DisplayName("위로 한칸 이동 후 오른쪽 대각선 방향으로 나이트 이동 가능")
    void canMove_UpUpRight() {
        Knight knight = new Knight(Color.WHITE);
        Position position1 = Position.of(4, 4);
        Position position2 = Position.of(5, 6);
        assertThat(knight.canMove(position1, position2)).isTrue();
    }

    @Test
    @DisplayName("위로 한칸 이동 후 왼쪽 대각선 방향으로 나이트 이동 가능")
    void canMove_UpUpLeft() {
        Knight knight = new Knight(Color.WHITE);
        Position position1 = Position.of(4, 4);
        Position position2 = Position.of(3, 6);
        assertThat(knight.canMove(position1, position2)).isTrue();
    }

    @Test
    @DisplayName("오른쪽으로 한칸 이동 후 위쪽 대각선 방향으로 나이트 이동 가능")
    void canMove_RightRightUp() {
        Knight knight = new Knight(Color.WHITE);
        Position position1 = Position.of(4, 4);
        Position position2 = Position.of(6, 5);
        assertThat(knight.canMove(position1, position2)).isTrue();
    }

    @Test
    @DisplayName("오른쪽으로 한칸 이동 후 아래쪽 대각선 방향으로 나이트 이동 가능")
    void canMove_RightRightDown() {
        Knight knight = new Knight(Color.WHITE);
        Position position1 = Position.of(4, 4);
        Position position2 = Position.of(6, 3);
        assertThat(knight.canMove(position1, position2)).isTrue();
    }

    @Test
    @DisplayName("아래로 한칸 이동 후 오른쪽 대각선 방향으로 나이트 이동 가능")
    void canMove_DownDownRight() {
        Knight knight = new Knight(Color.WHITE);
        Position position1 = Position.of(4, 4);
        Position position2 = Position.of(5, 2);
        assertThat(knight.canMove(position1, position2)).isTrue();
    }

    @Test
    @DisplayName("아래로 한칸 이동 후 왼쪽 대각선 방향으로 나이트 이동 가능")
    void canMove_DownDownLeft() {
        Knight knight = new Knight(Color.WHITE);
        Position position1 = Position.of(4, 4);
        Position position2 = Position.of(3, 2);
        assertThat(knight.canMove(position1, position2)).isTrue();
    }

    @Test
    @DisplayName("왼쪽으로 한칸 이동 후 아래쪽 대각선 방향으로 나이트 이동 가능")
    void canMove_LeftLeftDown() {
        Knight knight = new Knight(Color.WHITE);
        Position position1 = Position.of(4, 4);
        Position position2 = Position.of(2, 3);
        assertThat(knight.canMove(position1, position2)).isTrue();
    }

    @Test
    @DisplayName("위로 한칸 이동 후 오른쪽 대각선 방향으로 나이트 이동 가능")
    void canMove_LeftLeftUp() {
        Knight knight = new Knight(Color.WHITE);
        Position position1 = Position.of(4, 4);
        Position position2 = Position.of(2, 5);
        assertThat(knight.canMove(position1, position2)).isTrue();
    }

    @Test
    @DisplayName("나이트 이동 불가")
    void cannotMove() {
        Knight knight = new Knight(Color.WHITE);
        Position position1 = Position.of(4, 4);
        Position position2 = Position.of(3, 3);
        assertThat(knight.canMove(position1, position2)).isFalse();
    }

    @Test
    @DisplayName("같은 위치로는 이동 불가")
    void cannotMove_SamePosition() {
        Knight knight = new Knight(Color.WHITE);
        Position position1 = Position.of(1, 1);
        Position position2 = Position.of(1, 1);
        assertThat(knight.canMove(position1, position2)).isFalse();
    }
}
