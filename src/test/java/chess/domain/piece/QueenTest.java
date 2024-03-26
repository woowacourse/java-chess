package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class QueenTest {
    Queen queen;
    Position source;
    Piece whitePiece;
    Piece empty;

    @BeforeEach
    void setUp() {
        queen = new Queen(Color.WHITE);
        whitePiece = new Bishop(Color.WHITE);
        empty = new Empty();
        source = Position.of(File.D, Rank.ONE);
    }

    @DisplayName("퀸은 상하좌우로 움직일 수 있다.")
    @Test
    void canMove() {
        // given
        Position target = Position.of(File.E, Rank.ONE);

        // when
        boolean canMove = queen.canMove(source, target, empty);

        //then
        assertThat(canMove).isTrue();
    }

    @DisplayName("퀸은 대각선으로 움직일 수 있다.")
    @Test
    void canMoveDiagonal() {
        // given
        Position target = Position.of(File.E, Rank.TWO);

        // when
        boolean canMove = queen.canMove(source, target, empty);

        //then
        assertThat(canMove).isTrue();
    }

    @DisplayName("target 위치에 같은 색의 말이 있다면 움직일 수 없다.")
    @Test
    void canNotMoveWithSameColor() {
        // given
        Position target = Position.of(File.E, Rank.TWO);

        // when
        boolean canMove = queen.canMove(source, target, whitePiece);

        //then
        assertThat(canMove).isFalse();
    }

    @DisplayName("퀸은 상하좌우 혹은 대각선이 아닌 위치로 움직일 수 없다.")
    @Test
    void canNotMoveInvalidPath() {
        // given
        Position target = Position.of(File.E, Rank.THREE);

        // when
        boolean canMove = queen.canMove(source, target, empty);

        // then
        assertThat(canMove).isFalse();
    }

    @DisplayName("대각선으로 이동한 퀸의 이동 경로를 반환한다.")
    @Test
    void makePathDiagonal() {
        // given
        Position target = Position.of(File.G, Rank.FOUR);

        // when
        List<Position> movingPath = queen.searchPath(source, target);

        // then
        assertThat(movingPath).contains(Position.of(File.E, Rank.TWO)
                , Position.of(File.F, Rank.THREE));
    }

    @DisplayName("수직으로 이동한 퀸의 이동 경로를 반환한다.")
    @Test
    void makePathVertical() {
        // given
        Position target = Position.of(File.D, Rank.FOUR);

        // when
        List<Position> movingPath = queen.searchPath(source, target);

        // then
        assertThat(movingPath).contains(Position.of(File.D, Rank.TWO)
                , Position.of(File.D, Rank.THREE));
    }

    @DisplayName("수평으로 이동한 퀸의 이동 경로를 반환한다.")
    @Test
    void makePathHorizon() {
        // given
        Position target = Position.of(File.G, Rank.ONE);

        // when
        List<Position> movingPath = queen.searchPath(source, target);

        // then
        assertThat(movingPath).contains(Position.of(File.E, Rank.ONE)
                , Position.of(File.F, Rank.ONE));
    }
}
