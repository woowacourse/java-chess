package domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import domain.board.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class QueenTest {

    @Test
    @DisplayName("오른쪽 위 대각선 방향으로 퀸 이동 가능")
    void canMove_RightUp() {
        Queen queen = new Queen(Color.WHITE);
        Position position1 = Position.of(1, 3);
        Position position2 = Position.of(6, 8);
        assertThat(queen.canMove(position1, position2)).isTrue();
    }

    @Test
    @DisplayName("오른쪽 아래 대각선 방향으로 퀸 이동 가능")
    void canMove_RightDown() {
        Queen queen = new Queen(Color.WHITE);
        Position position1 = Position.of(4, 6);
        Position position2 = Position.of(2, 8);
        assertThat(queen.canMove(position1, position2)).isTrue();
    }

    @Test
    @DisplayName("왼쪽 위 대각선 방향으로 퀸 이동 가능")
    void canMove_LeftUp() {
        Queen queen = new Queen(Color.WHITE);
        Position position1 = Position.of(3, 5);
        Position position2 = Position.of(1, 7);
        assertThat(queen.canMove(position1, position2)).isTrue();
    }

    @Test
    @DisplayName("왼쪽 아래 대각선 방향으로 퀸 이동 가능")
    void canMove_LeftDown() {
        Queen queen = new Queen(Color.WHITE);
        Position position1 = Position.of(3, 5);
        Position position2 = Position.of(1, 3);
        assertThat(queen.canMove(position1, position2)).isTrue();
    }

    @Test
    @DisplayName("같은 대각선상이 아니면 퀸 이동 불가")
    void cannotMove() {
        Queen queen = new Queen(Color.WHITE);
        Position position1 = Position.of(1, 1);
        Position position2 = Position.of(7, 8);
        assertThat(queen.canMove(position1, position2)).isFalse();
    }

    @Test
    @DisplayName("Rank가 같으면 퀸 이동 가능")
    void canMove_SameRank() {
        Queen queen = new Queen(Color.WHITE);
        Position position1 = Position.of(1, 1);
        Position position2 = Position.of(8, 1);
        assertThat(queen.canMove(position1, position2)).isTrue();
    }

    @Test
    @DisplayName("File이 같으면 퀸 이동 가능")
    void canMove_SameFile() {
        Queen queen = new Queen(Color.WHITE);
        Position position1 = Position.of(1, 1);
        Position position2 = Position.of(1, 8);
        assertThat(queen.canMove(position1, position2)).isTrue();
    }

    @Test
    @DisplayName("같은 위치로는 이동 불가")
    void cannotMove_SamePosition() {
        Queen queen = new Queen(Color.WHITE);
        Position position1 = Position.of(1, 1);
        Position position2 = Position.of(1, 1);
        assertThat(queen.canMove(position1, position2)).isFalse();
    }
}
