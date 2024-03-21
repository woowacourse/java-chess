package domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import domain.board.File;
import domain.board.Position;
import domain.board.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class QueenTest {

    @Test
    @DisplayName("오른쪽 위 대각선 방향으로 퀸 이동 가능")
    void canMove_RightUp() {
        Queen queen = new Queen(Color.WHITE);
        Position position1 = new Position(new File(1), new Rank(3));
        Position position2 = new Position(new File(6), new Rank(8));
        assertThat(queen.canMove(position1, position2)).isTrue();
    }

    @Test
    @DisplayName("오른쪽 아래 대각선 방향으로 퀸 이동 가능")
    void canMove_RightDown() {
        Queen queen = new Queen(Color.WHITE);
        Position position1 = new Position(new File(4), new Rank(6));
        Position position2 = new Position(new File(2), new Rank(8));
        assertThat(queen.canMove(position1, position2)).isTrue();
    }

    @Test
    @DisplayName("왼쪽 위 대각선 방향으로 퀸 이동 가능")
    void canMove_LeftUp() {
        Queen queen = new Queen(Color.WHITE);
        Position position1 = new Position(new File(3), new Rank(5));
        Position position2 = new Position(new File(1), new Rank(7));
        assertThat(queen.canMove(position1, position2)).isTrue();
    }

    @Test
    @DisplayName("왼쪽 아래 대각선 방향으로 퀸 이동 가능")
    void canMove_LeftDown() {
        Queen queen = new Queen(Color.WHITE);
        Position position1 = new Position(new File(3), new Rank(5));
        Position position2 = new Position(new File(1), new Rank(3));
        assertThat(queen.canMove(position1, position2)).isTrue();
    }

    @Test
    @DisplayName("같은 대각선상이 아니면 퀸 이동 불가")
    void cannotMove() {
        Queen queen = new Queen(Color.WHITE);
        Position position1 = new Position(new File(1), new Rank(1));
        Position position2 = new Position(new File(7), new Rank(8));
        assertThat(queen.canMove(position1, position2)).isFalse();
    }

    @Test
    @DisplayName("Rank가 같으면 퀸 이동 가능")
    void canMove_SameRank() {
        Queen queen = new Queen(Color.WHITE);
        Position position1 = new Position(new File(1), new Rank(1));
        Position position2 = new Position(new File(8), new Rank(1));
        assertThat(queen.canMove(position1, position2)).isTrue();
    }

    @Test
    @DisplayName("File이 같으면 퀸 이동 가능")
    void canMove_SameFile() {
        Queen queen = new Queen(Color.WHITE);
        Position position1 = new Position(new File(1), new Rank(1));
        Position position2 = new Position(new File(1), new Rank(8));
        assertThat(queen.canMove(position1, position2)).isTrue();
    }

    @Test
    @DisplayName("같은 위치로는 이동 불가")
    void cannotMove_SamePosition() {
        Queen queen = new Queen(Color.WHITE);
        Position position1 = new Position(new File(1), new Rank(1));
        Position position2 = new Position(new File(1), new Rank(1));
        assertThat(queen.canMove(position1, position2)).isFalse();
    }
}
