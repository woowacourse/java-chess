package chess.domain.piece;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;

public class KingTest {

    private King whiteKing;

    @BeforeEach
    void setUp() {
        whiteKing = new King(Color.WHITE);
    }

    @Test
    @DisplayName("King이 오른쪽 위 대각선으로 한 칸 움직일 수 있는 경우 True를 반환한다")
    void isMovableRightUpTest() {
        Position from = Position.valueOf(File.C, Rank.ONE);
        Position to = Position.valueOf(File.D, Rank.TWO);

        assertThat(whiteKing.isMovable(from, to)).isTrue();
    }

    @Test
    @DisplayName("King이 왼쪽 위 대각선으로 한 칸 움직일 수 있는 경우 True를 반환한다")
    void isMovableLeftUpTest() {
        Position from = Position.valueOf(File.C, Rank.ONE);
        Position to = Position.valueOf(File.B, Rank.TWO);

        assertThat(whiteKing.isMovable(from, to)).isTrue();
    }

    @Test
    @DisplayName("King이 왼쪽 아래 대각선으로 한 칸 움직일 수 있는 경우 True를 반환한다")
    void isMovableLeftDownTest() {
        Position from = Position.valueOf(File.B, Rank.TWO);
        Position to = Position.valueOf(File.A, Rank.ONE);

        assertThat(whiteKing.isMovable(from, to)).isTrue();
    }

    @Test
    @DisplayName("King이 오른쪽 아래 대각선으로 한 칸 움직일 수 있는 경우 True를 반환한다")
    void isMovableRightDownTest() {
        Position from = Position.valueOf(File.B, Rank.TWO);
        Position to = Position.valueOf(File.C, Rank.ONE);

        assertThat(whiteKing.isMovable(from, to)).isTrue();
    }

    @Test
    @DisplayName("King이 세로로 한 칸 움직일 수 있는 경우 True를 반환한다")
    void isMovableColumnTest() {
        Position from = Position.valueOf(File.A, Rank.ONE);
        Position to = Position.valueOf(File.A, Rank.TWO);

        assertThat(whiteKing.isMovable(from, to)).isTrue();
    }

    @Test
    @DisplayName("King이 가로로 한 칸 움직일 수 있는 경우 True를 반환한다")
    void isMovableRowTest() {
        Position from = Position.valueOf(File.A, Rank.ONE);
        Position to = Position.valueOf(File.B, Rank.ONE);

        assertThat(whiteKing.isMovable(from, to)).isTrue();
    }

    @Test
    @DisplayName("King이 움직일 수 없는 경우 False를 반환한다.")
    void isNotMovableTest() {
        Position from = Position.valueOf(File.B, Rank.ONE);
        Position to = Position.valueOf(File.B, Rank.THREE);

        assertThat(whiteKing.isMovable(from, to)).isFalse();
    }
}
