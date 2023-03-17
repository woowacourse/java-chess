package chess.piece;

import chess.board.Position;
import chess.fixture.FixturePosition;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BishopTest {

    @Nested
    class 비숍이_움직일_때_이동방향은_ {
        @Test
        void 대각선이다() {
            //given
            Bishop bishop = new Bishop(Team.WHITE);

            Position from = FixturePosition.A1;
            Position to = FixturePosition.H8;

            //when & then
            assertThat(bishop.isMovable(from, to, PieceFixture.EMPTY_PIECE)).isTrue();
        }

        @Test
        void 대각선이_아니면_예외() {
            //given
            Bishop bishop = new Bishop(Team.WHITE);

            Position from = FixturePosition.A1;
            Position to = FixturePosition.B8;

            //when & then
            assertThatThrownBy(() -> bishop.isMovable(from, to, PieceFixture.EMPTY_PIECE))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("Bishop이 이동할 수 없는 경로입니다.");
        }
    }
}
