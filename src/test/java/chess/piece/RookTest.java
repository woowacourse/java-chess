package chess.piece;

import chess.board.File;
import chess.board.Position;
import chess.board.Rank;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class RookTest {

    @Nested
    class 룩이_움직일_때_이동방향은_ {
        @Test
        void 동일한_Rank_혹은_File이다() {
            //given
            Rook rook = new Rook(Team.WHITE);

            //when
            Position to = new Position(File.A, Rank.EIGHT);
            Position from = new Position(File.A, Rank.ONE);

            //then
            assertThat(rook.isMovable(from, to, PieceFixture.EMPTY_PIECE)).isTrue();
        }

        @Test
        void 동일한_Rank_혹은_File이_아니면_예외() {
            //given
            Rook rook = new Rook(Team.WHITE);

            //when
            Position to = new Position(File.B, Rank.EIGHT);
            Position from = new Position(File.A, Rank.ONE);

            //then
            assertThatThrownBy(() -> rook.isMovable(from, to, PieceFixture.EMPTY_PIECE))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("Rook이 이동할 수 없는 경로입니다.");
        }
    }
}
