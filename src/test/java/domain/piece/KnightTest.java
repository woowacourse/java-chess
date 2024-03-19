package domain.piece;


import static org.assertj.core.api.Assertions.assertThat;

import domain.File;
import domain.Position;
import domain.Rank;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class KnightTest {

    @Test
    @DisplayName("위로 한칸 이동 후 오른쪽 대각선 방향으로 나이트 이동 가능")
    void canMove_UpUpRight() {
        Knight knight = new Knight(Color.WHITE);
        Position position1 = new Position(new File(4), new Rank(4));
        Position position2 = new Position(new File(5), new Rank(6));
        assertThat(knight.canMove(position1, position2)).isTrue();
    }

    @Test
    @DisplayName("위로 한칸 이동 후 왼쪽 대각선 방향으로 나이트 이동 가능")
    void canMove_UpUpLeft() {
        Knight knight = new Knight(Color.WHITE);
        Position position1 = new Position(new File(4), new Rank(4));
        Position position2 = new Position(new File(3), new Rank(6));
        assertThat(knight.canMove(position1, position2)).isTrue();
    }

    @Test
    @DisplayName("오른쪽으로 한칸 이동 후 위쪽 대각선 방향으로 나이트 이동 가능")
    void canMove_RightRightUp() {
        Knight knight = new Knight(Color.WHITE);
        Position position1 = new Position(new File(4), new Rank(4));
        Position position2 = new Position(new File(6), new Rank(5));
        assertThat(knight.canMove(position1, position2)).isTrue();
    }

    @Test
    @DisplayName("오른쪽으로 한칸 이동 후 아래쪽 대각선 방향으로 나이트 이동 가능")
    void canMove_RightRightDown() {
        Knight knight = new Knight(Color.WHITE);
        Position position1 = new Position(new File(4), new Rank(4));
        Position position2 = new Position(new File(6), new Rank(3));
        assertThat(knight.canMove(position1, position2)).isTrue();
    }

    @Test
    @DisplayName("아래로 한칸 이동 후 오른쪽 대각선 방향으로 나이트 이동 가능")
    void canMove_DownDownRight() {
        Knight knight = new Knight(Color.WHITE);
        Position position1 = new Position(new File(4), new Rank(4));
        Position position2 = new Position(new File(5), new Rank(2));
        assertThat(knight.canMove(position1, position2)).isTrue();
    }

    @Test
    @DisplayName("아래로 한칸 이동 후 왼쪽 대각선 방향으로 나이트 이동 가능")
    void canMove_DownDownLeft() {
        Knight knight = new Knight(Color.WHITE);
        Position position1 = new Position(new File(4), new Rank(4));
        Position position2 = new Position(new File(3), new Rank(2));
        assertThat(knight.canMove(position1, position2)).isTrue();
    }

    @Test
    @DisplayName("왼쪽으로 한칸 이동 후 아래쪽 대각선 방향으로 나이트 이동 가능")
    void canMove_LeftLeftDown() {
        Knight knight = new Knight(Color.WHITE);
        Position position1 = new Position(new File(4), new Rank(4));
        Position position2 = new Position(new File(2), new Rank(3));
        assertThat(knight.canMove(position1, position2)).isTrue();
    }

    @Test
    @DisplayName("위로 한칸 이동 후 오른쪽 대각선 방향으로 나이트 이동 가능")
    void canMove_LeftLeftUp() {
        Knight knight = new Knight(Color.WHITE);
        Position position1 = new Position(new File(4), new Rank(4));
        Position position2 = new Position(new File(2), new Rank(5));
        assertThat(knight.canMove(position1, position2)).isTrue();
    }

    @Test
    @DisplayName("나이트 이동 불가")
    void cannotMove() {
        Knight knight = new Knight(Color.WHITE);
        Position position1 = new Position(new File(4), new Rank(4));
        Position position2 = new Position(new File(3), new Rank(3));
        assertThat(knight.canMove(position1, position2)).isFalse();
    }

    @Test
    @DisplayName("같은 위치로는 이동 불가")
    void cannotMove_SamePosition() {
        Knight knight = new Knight(Color.WHITE);
        Position position1 = new Position(new File(1), new Rank(1));
        Position position2 = new Position(new File(1), new Rank(1));
        Assertions.assertThat(knight.canMove(position1, position2)).isFalse();
    }
}
