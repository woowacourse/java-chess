package domain;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.piece.Color;
import chess.domain.piece.Rook;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RookTest {
    @DisplayName("룩은 상하좌우로 움직일 수 있다.")
    @Test
    void canMove() {
        // given
        Rook rook = new Rook(Color.WHITE);

        Position source = new Position(Rank.ONE, File.C);
        Position target = new Position(Rank.ONE, File.E);
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
        Rook rook = new Rook(Color.WHITE);

        Position source = new Position(Rank.ONE, File.C);
        Position target = new Position(Rank.ONE, File.E);
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
        Rook rook = new Rook(Color.WHITE);

        Position source = new Position(Rank.ONE, File.C);
        Position target = new Position(Rank.THREE, File.F);
        Color color = Color.NONE;

        // when
        boolean canMove = rook.canMove(source, target, color);

        // then
        assertThat(canMove).isFalse();
    }

    @DisplayName("룩의 이동 경로를 반환한다.")
    @Test
    void makePath() {
        // given
        Rook rook = new Rook(Color.WHITE);

        Position source = new Position(Rank.ONE, File.C);
        Position target = new Position(Rank.FOUR, File.C);

        // when
        List<Position> movingPath = rook.searchPath(source, target);

        // then
        assertThat(movingPath).contains(new Position(Rank.TWO, File.C)
                , new Position(Rank.THREE, File.C));
    }
}
