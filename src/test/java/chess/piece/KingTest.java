package chess.piece;

import chess.board.File;
import chess.board.Position;
import chess.board.Rank;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class KingTest {

    @Nested
    class 킹이_움직일_때_이동방향은_ {
        @Test
        void 상하좌우_대각선으로_한칸이다() {
            //given
            King king = new King(Team.WHITE);

            //when
            Position to = new Position(File.B, Rank.TWO);
            Position from = new Position(File.A, Rank.ONE);

            //then
            assertThat(king.isMovable(from, to)).isTrue();
        }

        @Test
        void 상하좌우_대각선으로_한칸이_아니면_예외() {
            King king = new King(Team.WHITE);

            //when
            Position to = new Position(File.B, Rank.THREE);
            Position from = new Position(File.A, Rank.ONE);

            //then
            assertThatThrownBy(() -> king.isMovable(from, to))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("King이 이동할 수 없는 경로입니다.");
        }
    }
}
