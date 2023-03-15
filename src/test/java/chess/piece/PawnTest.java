package chess.piece;

import chess.board.File;
import chess.board.Position;
import chess.board.Rank;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PawnTest {

    @Nested
    class 흰색_폰이_움직일_때_이동방향은_ {
        @Test
        void 위로_한_칸_이동한다() {
            //given
            Pawn pawn = new Pawn(Team.WHITE);

            //when
            Position to = new Position(File.B, Rank.THREE);
            Position from = new Position(File.B, Rank.TWO);

            //then
            assertThat(pawn.isMovable(from, to)).isTrue();
        }

        @Test
        void 첫_위치면_두칸_위로갈_수_있다() {
            //given
            Pawn pawn = new Pawn(Team.WHITE);

            //when
            Position to = new Position(File.B, Rank.FOUR);
            Position from = new Position(File.B, Rank.TWO);

            //then
            assertThat(pawn.isMovable(from, to)).isTrue();
        }

        @Test
        void 위_대각선으로_한칸_이동할_수_있다() {
            //given
            Pawn pawn = new Pawn(Team.WHITE);

            //when
            Position to = new Position(File.C, Rank.THREE);
            Position from = new Position(File.B, Rank.TWO);

            //then
            assertThat(pawn.isMovable(from, to)).isTrue();
        }

        @Test
        void 올바른_방향이_아니면_예외() {
            //given
            Pawn pawn = new Pawn(Team.WHITE);

            //when
            Position to = new Position(File.C, Rank.FOUR);
            Position from = new Position(File.B, Rank.TWO);

            //then
            assertThatThrownBy(() -> pawn.isMovable(from, to))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("Pawn이 이동할 수 없는 경로입니다.");
        }
    }

    @Nested
    class 검은색_폰이_움직일_때_이동방향은_ {
        @Test
        void 아래로_한_칸_이동한다() {
            //given
            Pawn pawn = new Pawn(Team.BLACK);

            //when
            Position to = new Position(File.B, Rank.FOUR);
            Position from = new Position(File.B, Rank.FIVE);

            //then
            assertThat(pawn.isMovable(from, to)).isTrue();
        }

        @Test
        void 첫_위치면_두칸_아래로_갈_수_있다() {
            //given
            Pawn pawn = new Pawn(Team.BLACK);

            //when
            Position to = new Position(File.B, Rank.FIVE);
            Position from = new Position(File.B, Rank.SEVEN);

            //then
            assertThat(pawn.isMovable(from, to)).isTrue();
        }

        @Test
        void 아래_대각선으로_한칸_이동할_수_있다() {
            //given
            Pawn pawn = new Pawn(Team.BLACK);

            //when
            Position to = new Position(File.B, Rank.SIX);
            Position from = new Position(File.C, Rank.SEVEN);

            //then
            assertThat(pawn.isMovable(from, to)).isTrue();
        }

        @Test
        void 올바른_방향이_아니면_예외() {
            //given
            Pawn pawn = new Pawn(Team.BLACK);

            //when
            Position to = new Position(File.C, Rank.FOUR);
            Position from = new Position(File.B, Rank.TWO);

            //then
            assertThatThrownBy(() -> pawn.isMovable(from, to))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("Pawn이 이동할 수 없는 경로입니다.");
        }
    }
}
