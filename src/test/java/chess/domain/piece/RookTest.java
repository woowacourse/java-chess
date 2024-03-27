package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RookTest {
    Rook rook;
    Position source;
    Piece whitePiece;
    Piece empty;

    @BeforeEach
    void setUp() {
        rook = new Rook(Color.WHITE);
        whitePiece = new Bishop(Color.WHITE);
        empty = new Empty();
        source = Position.of(File.A, Rank.ONE);
    }

    @DisplayName("룩은 상하좌우로 움직일 수 있다.")
    @Test
    void canMove() {
        // given
        Position target = Position.of(File.E, Rank.ONE);

        // when
        boolean canMove = rook.canMove(source, target, empty);

        //then
        assertThat(canMove).isTrue();
    }

    @DisplayName("target 위치에 같은 색의 말이 있다면 움직일 수 없다.")
    @Test
    void canNotMoveWithSameColor() {
        // given
        Position target = Position.of(File.E, Rank.ONE);

        // when
        boolean canMove = rook.canMove(source, target, whitePiece);

        //then
        assertThat(canMove).isFalse();
    }

    @DisplayName("룩은 상하좌우가 아닌 위치로 움직일 수 없다.")
    @Test
    void canNotMoveInvalidPath() {
        // given
        Position target = Position.of(File.B, Rank.TWO);

        // when
        boolean canMove = rook.canMove(source, target, empty);

        // then
        assertThat(canMove).isFalse();
    }

    @DisplayName("수직으로 이동한 룩의 이동 경로를 반환한다.")
    @Test
    void makePathVertical() {
        // given
        Position target = Position.of(File.A, Rank.FOUR);

        // when
        List<Position> movingPath = rook.searchPath(source, target);

        // then
        assertThat(movingPath).contains(Position.of(File.A, Rank.TWO)
                , Position.of(File.A, Rank.THREE));
    }

    @DisplayName("수평으로 이동한 룩의 이동 경로를 반환한다.")
    @Test
    void makePathHorizon() {
        // given
        Position target = Position.of(File.D, Rank.ONE);

        // when
        List<Position> movingPath = rook.searchPath(source, target);

        // then
        assertThat(movingPath).contains(Position.of(File.B, Rank.ONE)
                , Position.of(File.C, Rank.ONE));
    }
}
