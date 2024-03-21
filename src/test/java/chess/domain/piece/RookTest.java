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

class RookTest {
    Rook rook;
    Position source;

    @BeforeEach
    void setUp() {
        rook = new Rook(Color.WHITE);
        source = Positions.of(File.A, Rank.ONE);
    }

    @DisplayName("룩은 상하좌우로 움직일 수 있다.")
    @Test
    void canMove() {
        // given
        Position target = Positions.of(File.E, Rank.ONE);
        Color color = Color.NONE;

        // when
        boolean canMove = rook.canMove(source, target, color);

        //then
        assertThat(canMove).isTrue();
    }

    @DisplayName("target 위치에 같은 색의 말이 있다면 움직일 수 없다.")
    @Test
    void canNotMoveWithSameColor() {
        // given
        Position target = Positions.of(File.E, Rank.ONE);
        Color color = Color.WHITE;

        // when
        boolean canMove = rook.canMove(source, target, color);

        //then
        assertThat(canMove).isFalse();
    }

    @DisplayName("룩은 상하좌우가 아닌 위치로 움직일 수 없다.")
    @Test
    void canNotMoveInvalidPath() {
        // given
        Position target = Positions.of(File.B, Rank.TWO);
        Color color = Color.NONE;

        // when
        boolean canMove = rook.canMove(source, target, color);

        // then
        assertThat(canMove).isFalse();
    }

    @DisplayName("수직으로 이동한 룩의 이동 경로를 반환한다.")
    @Test
    void makePathVertical() {
        // given
        Position target = Positions.of(File.A, Rank.FOUR);

        // when
        List<Position> movingPath = rook.searchPath(source, target);

        // then
        assertThat(movingPath).contains(Positions.of(File.A, Rank.TWO)
                , Positions.of(File.A, Rank.THREE));
    }

    @DisplayName("수평으로 이동한 룩의 이동 경로를 반환한다.")
    @Test
    void makePathHorizon() {
        // given
        Position target = Positions.of(File.D, Rank.ONE);

        // when
        List<Position> movingPath = rook.searchPath(source, target);

        // then
        assertThat(movingPath).contains(Positions.of(File.B, Rank.ONE)
                , Positions.of(File.C, Rank.ONE));
    }
}
