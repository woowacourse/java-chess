package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.File;
import chess.domain.Rank;
import chess.domain.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RookTest {

    private Rook whiteRook;

    @BeforeEach
    void setUp() {
        whiteRook = new Rook(Color.WHITE);
    }
    
    @Test
    @DisplayName("Rook이 세로로 움직일 수 있는 경우 True를 반환한다")
    void isMovableColumnTest() {
        Position from = Position.valueOf(File.A, Rank.ONE);
        Position to = Position.valueOf(File.A, Rank.FIVE);

        assertThat(whiteRook.isMovable(from, to)).isTrue();
    }

    @Test
    @DisplayName("Rook이 가로로 움직일 수 있는 경우 True를 반환한다")
    void isMovableRowTest() {
        Position from = Position.valueOf(File.A, Rank.ONE);
        Position to = Position.valueOf(File.H, Rank.ONE);

        assertThat(whiteRook.isMovable(from, to)).isTrue();
    }

    @Test
    @DisplayName("Rook이 움직일 수 없을 때 False를 반환한다")
    void isNotMovableTest() {
        Position from = Position.valueOf(File.A, Rank.ONE);
        Position to = Position.valueOf(File.B, Rank.TWO);

        assertThat(whiteRook.isMovable(from, to)).isFalse();
    }
}
