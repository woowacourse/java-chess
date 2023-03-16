package chess.piece;

import chess.board.File;
import chess.board.Position;
import chess.board.Rank;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class KnightTest {


    @Nested
    class 나이트가_움직일_때_이동방향은_ {
        @Test
        void 두_칸_전진한_상태에서_좌우로_한_칸_움직일_수_있다() {
            //given
            Knight knight = new Knight(Team.WHITE);

            //when
            Position to = new Position(File.C, Rank.TWO);
            Position from = new Position(File.A, Rank.ONE);

            //then
            assertThat(knight.isMovable(from, to, PieceFixture.EMPTY_PIECE)).isTrue();
        }

        @Test
        void 대각선이_아니면_예외() {
            //given
            Knight knight = new Knight(Team.WHITE);

            //when
            Position to = new Position(File.B, Rank.EIGHT);
            Position from = new Position(File.A, Rank.ONE);

            //then
            assertThatThrownBy(() -> knight.isMovable(from, to, PieceFixture.EMPTY_PIECE))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("Knight가 이동할 수 없는 경로입니다.");
        }
    }
}
