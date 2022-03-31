package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.File;
import chess.domain.Rank;
import chess.domain.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class KnightTest {

    private Knight whiteKnight;

    @BeforeEach
    void setUp() {
        whiteKnight = new Knight(Color.WHITE);
    }

    @Test
    @DisplayName("Knight가 왼쪽으로 두 칸 이동 후 아래로 한칸 내려갈 경우  True를 반환한다")
    void isMovableLeftDownTest() {
        Position from = Position.valueOf(File.C, Rank.TWO);
        Position to = Position.valueOf(File.A, Rank.ONE);

        assertThat(whiteKnight.isMovable(from, to)).isTrue();
    }

    @Test
    @DisplayName("Knight가 왼쪽으로 두 칸 이동 후 위로 한칸 올라갈 경우 True를 반환한다")
    void isMovableLeftUpTest() {
        Position from = Position.valueOf(File.C, Rank.ONE);
        Position to = Position.valueOf(File.A, Rank.TWO);

        assertThat(whiteKnight.isMovable(from, to)).isTrue();
    }

    @Test
    @DisplayName("Knight가 오른쪽으로 두 칸 이동 후 위로 한칸 올라갈 경우 True를 반환한다")
    void isMovableRightUpTest() {
        Position from = Position.valueOf(File.B, Rank.TWO);
        Position to = Position.valueOf(File.D, Rank.THREE);

        assertThat(whiteKnight.isMovable(from, to)).isTrue();
    }

    @Test
    @DisplayName("Knight가 오른쪽으로 두 칸 이동 후 아래로 한칸 내려갈 경우 True를 반환한다")
    void isMovableRightDownTest() {
        Position from = Position.valueOf(File.B, Rank.TWO);
        Position to = Position.valueOf(File.D, Rank.ONE);

        assertThat(whiteKnight.isMovable(from, to)).isTrue();
    }

    @Test
    @DisplayName("Knight가 위쪽으로 두 칸 이동 후 왼쪽으로 한칸 이동할 경우 True를 반환한다.")
    void isMovableUpLeftTest() {
        Position from = Position.valueOf(File.B, Rank.ONE);
        Position to = Position.valueOf(File.A, Rank.THREE);

        assertThat(whiteKnight.isMovable(from, to)).isTrue();
    }

    @Test
    @DisplayName("Knight가 위쪽으로 두 칸 이동 후 오른쪽으로 한칸 이동할 경우 True를 반환한다.")
    void isMovableUpRightTest() {
        Position from = Position.valueOf(File.A, Rank.ONE);
        Position to = Position.valueOf(File.B, Rank.THREE);

        assertThat(whiteKnight.isMovable(from, to)).isTrue();
    }

    @Test
    @DisplayName("Knight가 아래쪽으로 두 칸 이동 후 왼쪽으로 한칸 이동할 경우 True를 반환한다.")
    void isMovableDownLeftTest() {
        Position from = Position.valueOf(File.B, Rank.THREE);
        Position to = Position.valueOf(File.A, Rank.ONE);

        assertThat(whiteKnight.isMovable(from, to)).isTrue();
    }

    @Test
    @DisplayName("Knight가 아래쪽으로 두 칸 이동 후 오른쪽으로 한칸 이동할 경우 True를 반환한다.")
    void isMovableDownRightTest() {
        Position from = Position.valueOf(File.A, Rank.THREE);
        Position to = Position.valueOf(File.B, Rank.ONE);

        assertThat(whiteKnight.isMovable(from, to)).isTrue();
    }

    @Test
    @DisplayName("Knight가 움직일 수 없는 경우 False를 반환한다.")
    void isNotMovableTest() {
        Position from = Position.valueOf(File.B, Rank.ONE);
        Position to = Position.valueOf(File.B, Rank.THREE);

        assertThat(whiteKnight.isMovable(from, to)).isFalse();
    }
}
