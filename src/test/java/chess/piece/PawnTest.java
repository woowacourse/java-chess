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

class PawnTest {

    private Map<Position, Piece> board;

    @BeforeEach
    void setUp() {
        board = new EmptyBoardFixture().getBoard();
    }

    @Nested
    class 흰색_폰이_움직일_때_이동방향은_ {
        @Test
        void 위로_한_칸_이동한다() {
            //given
            Pawn pawn = new WhitePawn();

            Position from = PositionFixture.B2;
            Position to = PositionFixture.B3;

            //when & then
            assertDoesNotThrow(() -> pawn.validateMove(from, to, board));
        }

        @Test
        void 첫_위치면_두칸_위로갈_수_있다() {
            //given
            Pawn pawn = new WhitePawn();

            Position from = PositionFixture.B2;
            Position to = PositionFixture.B4;

            //when & then
            assertDoesNotThrow(() -> pawn.validateMove(from, to, board));
        }

        @Test
        void 상대_말이_있을_때만_위_대각선으로_한칸_이동할_수_있다() {
            //given
            Pawn pawn = new WhitePawn();

            Position from = PositionFixture.B2;
            Position to = PositionFixture.C3;

            //when & then
            assertDoesNotThrow(() -> pawn.validateMove(from, to, board));
        }

        @Test
        void 올바른_방향이_아니면_예외() {
            //given
            Pawn pawn = new WhitePawn();

            Position from = PositionFixture.B2;
            Position to = PositionFixture.C4;

            //when & then
            assertThatThrownBy(() -> pawn.validateMove(from, to, board))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("Pawn이 이동할 수 없는 경로입니다.");
        }
    }

    @Nested
    class 검은색_폰이_움직일_때_이동방향은_ {
        @Test
        void 아래로_한_칸_이동한다() {
            //given
            Pawn pawn = new BlackPawn();

            Position from = PositionFixture.B5;
            Position to = PositionFixture.B4;

            //when & then
            assertDoesNotThrow(() -> pawn.validateMove(from, to, board));
        }

        @Test
        void 첫_위치면_두칸_아래로_갈_수_있다() {
            //given
            Pawn pawn = new BlackPawn();

            Position from = PositionFixture.B7;
            Position to = PositionFixture.B5;

            //when & then
            assertDoesNotThrow(() -> pawn.validateMove(from, to, board));
        }

        @Test
        void 상대_말이_있을_때만_아래_대각선으로_한칸_이동할_수_있다() {
            //given
            Pawn pawn = new BlackPawn();

            Position from = PositionFixture.C7;
            Position to = PositionFixture.B6;

            //when & then
            assertDoesNotThrow(() -> pawn.validateMove(from, to, board));
        }

        @Test
        void 올바른_방향이_아니면_예외() {
            //given
            Pawn pawn = new BlackPawn();

            Position from = PositionFixture.B2;
            Position to = PositionFixture.C4;

            //when & then
            assertThatThrownBy(() -> pawn.validateMove(from, to, board))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("Pawn이 이동할 수 없는 경로입니다.");
        }
    }
}
