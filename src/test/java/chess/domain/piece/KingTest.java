package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Positions;
import chess.domain.position.Rank;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class KingTest {
    King king;
    Position source;

    @BeforeEach
    void setUp() {
        king = new King(Color.WHITE);
        source = Positions.of(File.E, Rank.ONE);
    }

    @DisplayName("킹은 상하좌우로 한 칸 움직일 수 있다.")
    @Test
    void canMove() {
        // given
        Position target = Positions.of(File.D, Rank.ONE);
        Color color = Color.NONE;

        // when
        boolean canMove = king.canMove(source, target, color);

        //then
        assertThat(canMove).isTrue();
    }

    @DisplayName("킹은 대각선으로 한 칸 움직일 수 있다.")
    @Test
    void canMoveDiagonal() {
        // given
        Position target = Positions.of(File.D, Rank.TWO);
        Color color = Color.NONE;

        // when
        boolean canMove = king.canMove(source, target, color);

        //then
        assertThat(canMove).isTrue();
    }

    @DisplayName("target 위치에 같은 색의 말이 있다면 움직일 수 없다.")
    @Test
    void canNotMoveWithSameColor() {
        // given
        Position target = Positions.of(File.D, Rank.TWO);
        Color color = Color.WHITE;

        // when
        boolean canMove = king.canMove(source, target, color);

        //then
        assertThat(canMove).isFalse();
    }

    @DisplayName("킹은 상하좌우 혹은 대각선이 아닌 위치로 움직일 수 없다.")
    @Test
    void canNotMoveInvalidPath() {
        // given
        Position target = Positions.of(File.B, Rank.THREE);
        Color color = Color.NONE;

        // when
        boolean canMove = king.canMove(source, target, color);

        // then
        assertThat(canMove).isFalse();
    }

    @DisplayName("킹의 이동 경로는 비어있다.")
    @Test
    void makePath() {
        // given
        Position target = Positions.of(File.D, Rank.TWO);

        // when
        List<Position> movingPath = king.searchPath(source, target);

        // then
        assertThat(movingPath).isEmpty();
    }
}
