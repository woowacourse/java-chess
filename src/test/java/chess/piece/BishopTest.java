package chess.piece;

import chess.board.File;
import chess.board.Position;
import chess.board.Rank;
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

            //when
            Position to = new Position(File.H, Rank.EIGHT);
            Position from = new Position(File.A, Rank.ONE);

            //then
            assertThat(bishop.isMovable(from, to)).isTrue();
        }

        @Test
        void 대각선이_아니면_예외() {
            //given
            Bishop bishop = new Bishop(Team.WHITE);

            //when
            Position to = new Position(File.B, Rank.EIGHT);
            Position from = new Position(File.A, Rank.ONE);

            //then
            assertThatThrownBy(() -> bishop.isMovable(from, to))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("Bishop이 이동할 수 없는 경로입니다.");
        }
    }
}
