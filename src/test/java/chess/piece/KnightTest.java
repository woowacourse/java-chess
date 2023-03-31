package chess.piece;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import chess.board.Position;
import chess.fixture.EmptyBoardFixture;
import chess.fixture.PositionFixture;

class KnightTest {

    private Map<Position, Piece> board;

    @BeforeEach
    void setUp() {
        board = new EmptyBoardFixture().getBoard();
    }

    @Nested
    class 나이트가_움직일_때_이동방향은_ {
        @Test
        void 두_칸_전진한_상태에서_좌우로_한_칸_움직일_수_있다() {
            //given
            Knight knight = new Knight(Team.WHITE);

            Position from = PositionFixture.A1;
            Position to = PositionFixture.C2;

            //when & then
            assertDoesNotThrow(() -> knight.validateMove(from, to, board));
        }

        @Test
        void 대각선이_아니면_예외() {
            //given
            Knight knight = new Knight(Team.WHITE);

            Position from = PositionFixture.A1;
            Position to = PositionFixture.B8;

            //when & then
            assertThatThrownBy(() -> knight.validateMove(from, to, board))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("Knight가 이동할 수 없는 경로입니다.");
        }
    }
}
