package domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import domain.board.File;
import domain.board.Position;
import domain.board.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class KingTest {

    @Test
    @DisplayName("오른쪽 위 대각선 방향으로 킹 이동 가능")
    void canMove_RightUp() {
        King king = new King(Color.WHITE);
        Position position1 = new Position(new File(1), new Rank(3));
        Position position2 = new Position(new File(2), new Rank(4));
        assertThat(king.canMove(position1, position2)).isTrue();
    }

    @Test
    @DisplayName("오른쪽 아래 대각선 방향으로 킹 이동 가능")
    void canMove_RightDown() {
        King king = new King(Color.WHITE);
        Position position1 = new Position(new File(4), new Rank(6));
        Position position2 = new Position(new File(3), new Rank(7));
        assertThat(king.canMove(position1, position2)).isTrue();
    }

    @Test
    @DisplayName("왼쪽 위 대각선 방향으로 킹 이동 가능")
    void canMove_LeftUp() {
        King king = new King(Color.WHITE);
        Position position1 = new Position(new File(3), new Rank(5));
        Position position2 = new Position(new File(2), new Rank(6));
        assertThat(king.canMove(position1, position2)).isTrue();
    }

    @Test
    @DisplayName("왼쪽 아래 대각선 방향으로 킹 이동 가능")
    void canMove_LeftDown() {
        King king = new King(Color.WHITE);
        Position position1 = new Position(new File(3), new Rank(5));
        Position position2 = new Position(new File(2), new Rank(4));
        assertThat(king.canMove(position1, position2)).isTrue();
    }

    @Test
    @DisplayName("1칸을 초과하면 킹 이동 불가")
    void cannotMove() {
        King king = new King(Color.WHITE);
        Position position1 = new Position(new File(1), new Rank(1));
        Position position2 = new Position(new File(1), new Rank(3));
        assertThat(king.canMove(position1, position2)).isFalse();
    }

    @Test
    @DisplayName("Rank가 같고 File 차이가 한 칸이면 킹 이동 가능")
    void canMove_SameRank() {
        King king = new King(Color.WHITE);
        Position position1 = new Position(new File(1), new Rank(1));
        Position position2 = new Position(new File(2), new Rank(1));
        assertThat(king.canMove(position1, position2)).isTrue();
    }

    @Test
    @DisplayName("File이 같고 Rank 차이가 한 칸이면 킹 이동 가능")
    void canMove_SameFile() {
        King king = new King(Color.WHITE);
        Position position1 = new Position(new File(1), new Rank(1));
        Position position2 = new Position(new File(1), new Rank(2));
        assertThat(king.canMove(position1, position2)).isTrue();
    }

    @Test
    @DisplayName("같은 위치로는 이동 불가")
    void cannotMove_SamePosition() {
        King king = new King(Color.WHITE);
        Position position1 = new Position(new File(1), new Rank(1));
        Position position2 = new Position(new File(1), new Rank(1));
        assertThat(king.canMove(position1, position2)).isFalse();
    }
}
