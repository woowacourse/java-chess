package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BishopTest {
    Bishop bishop;
    Position source;
    Piece whitePiece;
    Piece empty;

    @BeforeEach
    void setUp() {
        bishop = new Bishop(Color.WHITE);
        whitePiece = new Bishop(Color.WHITE);
        empty = new Empty();
        source = Position.of(File.C, Rank.ONE);
    }

    @DisplayName("비숍은 대각선으로 움직일 수 있다.")
    @Test
    void canMove() {
        // given
        Position target = Position.of(File.E, Rank.THREE);

        // when
        boolean canMove = bishop.canMove(source, target, empty);

        //then
        assertThat(canMove).isTrue();
    }

    @DisplayName("비숍은 대각선이 아닌 위치로 움직일 수 없다.")
    @Test
    void canNotMoveInvalidPath() {
        // given
        Position target = Position.of(File.C, Rank.THREE);

        // when
        boolean canMove = bishop.canMove(source, target, empty);

        // then
        assertThat(canMove).isFalse();
    }

    @DisplayName("target 위치에 같은 색의 말이 있다면 움직일 수 없다.")
    @Test
    void canNotMoveWithSameColor() {
        // given
        Position target = Position.of(File.E, Rank.THREE);

        // when
        boolean canMove = bishop.canMove(source, target, whitePiece);

        //then
        assertThat(canMove).isFalse();
    }

    @DisplayName("비숍의 이동 경로를 반환한다.")
    @Test
    void makePath() {
        // given
        Position target = Position.of(File.F, Rank.FOUR);

        // when
        List<Position> movingPath = bishop.searchPath(source, target);

        // then
        assertThat(movingPath).contains(Position.of(File.D, Rank.TWO)
                , Position.of(File.E, Rank.THREE));
    }
}
