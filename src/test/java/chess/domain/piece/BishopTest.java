package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.File;
import chess.domain.Rank;
import chess.domain.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BishopTest {

    private Bishop whiteBishop;

    @BeforeEach
    void setUp() {
        whiteBishop = new Bishop(Color.WHITE);
    }

    @Test
    @DisplayName("Bishop이 오른쪽 위 대각선으로 움직일 수 있는 경우 True를 반환한다")
    void isMovableRightUpTest() {
        Position from = Position.valueOf(File.C, Rank.ONE);
        Position to = Position.valueOf(File.D, Rank.TWO);

        assertThat(whiteBishop.isMovable(from, to)).isTrue();
    }

    @Test
    @DisplayName("Bishop이 왼쪽 위 대각선으로 움직일 수 있는 경우 True를 반환한다")
    void isMovableLeftUpTest() {
        Position from = Position.valueOf(File.C, Rank.ONE);
        Position to = Position.valueOf(File.B, Rank.TWO);

        assertThat(whiteBishop.isMovable(from, to)).isTrue();
    }

    @Test
    @DisplayName("Bishop이 왼쪽 아래 대각선으로 움직일 수 있는 경우 True를 반환한다")
    void isMovableLeftDownTest() {
        Position from = Position.valueOf(File.B, Rank.TWO);
        Position to = Position.valueOf(File.A, Rank.ONE);

        assertThat(whiteBishop.isMovable(from, to)).isTrue();
    }

    @Test
    @DisplayName("Bishop이 오른쪽 아래 대각선으로 움직일 수 있는 경우 True를 반환한다")
    void isMovableRightDownTest() {
        Position from = Position.valueOf(File.B, Rank.TWO);
        Position to = Position.valueOf(File.C, Rank.ONE);

        assertThat(whiteBishop.isMovable(from, to)).isTrue();
    }

    @Test
    @DisplayName("Bishop이 움직일 수 없는 경우 False 를 반환한다")
    void isNotMovableTest() {
        Position from = Position.valueOf(File.B, Rank.TWO);
        Position to = Position.valueOf(File.B, Rank.ONE);

        assertThat(whiteBishop.isMovable(from, to)).isFalse();
    }
}
