package domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import domain.board.File;
import domain.board.Position;
import domain.board.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BishopTest {

    @Test
    @DisplayName("오른쪽 위 대각선 방향으로 비숍 이동 가능")
    void canMove_RightUp() {
        Bishop bishop = new Bishop(Color.WHITE);
        Position position1 = new Position(new File(1), new Rank(3));
        Position position2 = new Position(new File(6), new Rank(8));
        assertThat(bishop.canMove(position1, position2)).isTrue();
    }

    @Test
    @DisplayName("오른쪽 아래 대각선 방향으로 비숍 이동 가능")
    void canMove_RightDown() {
        Bishop bishop = new Bishop(Color.WHITE);
        Position position1 = new Position(new File(4), new Rank(6));
        Position position2 = new Position(new File(2), new Rank(8));
        assertThat(bishop.canMove(position1, position2)).isTrue();
    }

    @Test
    @DisplayName("왼쪽 위 대각선 방향으로 비숍 이동 가능")
    void canMove_LeftUp() {
        Bishop bishop = new Bishop(Color.WHITE);
        Position position1 = new Position(new File(3), new Rank(5));
        Position position2 = new Position(new File(1), new Rank(7));
        assertThat(bishop.canMove(position1, position2)).isTrue();
    }

    @Test
    @DisplayName("왼쪽 아래 대각선 방향으로 비숍 이동 가능")
    void canMove_LeftDown() {
        Bishop bishop = new Bishop(Color.WHITE);
        Position position1 = new Position(new File(3), new Rank(5));
        Position position2 = new Position(new File(1), new Rank(3));
        assertThat(bishop.canMove(position1, position2)).isTrue();
    }

    @Test
    @DisplayName("같은 대각선상이 아니면 비숍 이동 불가")
    void cannotMove() {
        Bishop bishop = new Bishop(Color.WHITE);
        Position position1 = new Position(new File(1), new Rank(1));
        Position position2 = new Position(new File(7), new Rank(8));
        assertThat(bishop.canMove(position1, position2)).isFalse();
    }

    @Test
    @DisplayName("같은 위치로는 이동 불가")
    void cannotMove_SamePosition() {
        Bishop bishop = new Bishop(Color.WHITE);
        Position position1 = new Position(new File(1), new Rank(1));
        Position position2 = new Position(new File(1), new Rank(1));
        assertThat(bishop.canMove(position1, position2)).isFalse();
    }
}
