package domain;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.piece.Color;
import chess.domain.piece.Queen;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class QueenTest {
    @DisplayName("퀸은 상하좌우로 움직일 수 있다.")
    @Test
    void canMove() {
        // given
        Queen queen = new Queen(Color.WHITE);

        Position source = new Position(Rank.ONE, File.C);
        Position target = new Position(Rank.ONE, File.E);
        Color color = Color.NONE;

        // when
        boolean canMove = queen.canMove(source, target, color);

        //then
        assertThat(canMove).isTrue();
    }

    @DisplayName("퀸은 대각선으로 움직일 수 있다.")
    @Test
    void canMoveDiagonal() {
        // given
        Queen queen = new Queen(Color.WHITE);

        Position source = new Position(Rank.ONE, File.C);
        Position target = new Position(Rank.THREE, File.E);
        Color color = Color.NONE;

        // when
        boolean canMove = queen.canMove(source, target, color);

        //then
        assertThat(canMove).isTrue();
    }

    @DisplayName("target 위치에 같은 색의 말이 있다면 움직일 수 없다.")
    @Test
    void canNotMoveWithSameColor() {
        // given
        Queen queen = new Queen(Color.WHITE);

        Position source = new Position(Rank.ONE, File.C);
        Position target = new Position(Rank.THREE, File.E);
        Color color = Color.WHITE;

        // when
        boolean canMove = queen.canMove(source, target, color);

        //then
        assertThat(canMove).isFalse();
    }

    @DisplayName("퀸은 상하좌우 혹은 대각선이 아닌 위치로 움직일 수 없다.")
    @Test
    void canNotMoveInvalidPath() {
        // given
        Queen queen = new Queen(Color.WHITE);

        Position source = new Position(Rank.ONE, File.C);
        Position target = new Position(Rank.THREE, File.B);
        Color color = Color.NONE;

        // when
        boolean canMove = queen.canMove(source, target, color);

        // then
        assertThat(canMove).isFalse();
    }

    @DisplayName("퀸의 이동 경로를 반환한다.")
    @Test
    void makePath() {
        // given
        Queen queen = new Queen(Color.WHITE);

        Position source = new Position(Rank.ONE, File.C);
        Position target = new Position(Rank.FOUR, File.F);

        // when
        List<Position> movingPath = queen.searchPath(source, target);

        // then
        assertThat(movingPath).contains(new Position(Rank.TWO, File.D)
                , new Position(Rank.THREE, File.E));
    }
}
