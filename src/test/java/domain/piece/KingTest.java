package domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import domain.board.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class KingTest {

    @Test
    @DisplayName("오른쪽 위 대각선 방향으로 킹 이동 가능")
    void canMove_RightUp() {
        King king = new King(Color.WHITE);
        Position position1 = Position.of(1, 3);
        Position position2 = Position.of(2, 4);
        assertThat(king.canMove(position1, position2)).isTrue();
    }

    @Test
    @DisplayName("오른쪽 아래 대각선 방향으로 킹 이동 가능")
    void canMove_RightDown() {
        King king = new King(Color.WHITE);
        Position position1 = Position.of(4, 6);
        Position position2 = Position.of(3, 7);
        assertThat(king.canMove(position1, position2)).isTrue();
    }

    @Test
    @DisplayName("왼쪽 위 대각선 방향으로 킹 이동 가능")
    void canMove_LeftUp() {
        King king = new King(Color.WHITE);
        Position position1 = Position.of(3, 5);
        Position position2 = Position.of(2, 6);
        assertThat(king.canMove(position1, position2)).isTrue();
    }

    @Test
    @DisplayName("왼쪽 아래 대각선 방향으로 킹 이동 가능")
    void canMove_LeftDown() {
        King king = new King(Color.WHITE);
        Position position1 = Position.of(3, 5);
        Position position2 = Position.of(2, 4);
        assertThat(king.canMove(position1, position2)).isTrue();
    }

    @Test
    @DisplayName("1칸을 초과하면 킹 이동 불가")
    void cannotMove() {
        King king = new King(Color.WHITE);
        Position position1 = Position.of(1, 1);
        Position position2 = Position.of(1, 3);
        assertThat(king.canMove(position1, position2)).isFalse();
    }

    @Test
    @DisplayName("Rank가 같고 File 차이가 한 칸이면 킹 이동 가능")
    void canMove_SameRank() {
        King king = new King(Color.WHITE);
        Position position1 = Position.of(1, 1);
        Position position2 = Position.of(2, 1);
        assertThat(king.canMove(position1, position2)).isTrue();
    }

    @Test
    @DisplayName("File이 같고 Rank 차이가 한 칸이면 킹 이동 가능")
    void canMove_SameFile() {
        King king = new King(Color.WHITE);
        Position position1 = Position.of(1, 1);
        Position position2 = Position.of(1, 2);
        assertThat(king.canMove(position1, position2)).isTrue();
    }
}
