package chess.piece;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import chess.board.File;
import chess.board.Position;
import chess.board.Rank;
import chess.fixture.FixturePosition;

class PawnTest {

    private Map<Position, Piece> board;

    @BeforeEach
    void setUp() {
        board = new HashMap<>();
        for (final File file : File.values()) {
            for (final Rank rank : Rank.values()) {
                board.put(new Position(file, rank), new EmptyPiece());
            }
        }
    }

    @Nested
    class 흰색_폰이_움직일_때_이동방향은_ {
        @Test
        void 위로_한_칸_이동한다() {
            //given
            Pawn pawn = new WhitePawn();

            Position from = FixturePosition.B2;
            Position to = FixturePosition.B3;

            //when & then
            assertDoesNotThrow(() -> pawn.validateMove(from, to, board));
        }

        @Test
        void 첫_위치면_두칸_위로갈_수_있다() {
            //given
            Pawn pawn = new WhitePawn();

            Position from = FixturePosition.B2;
            Position to = FixturePosition.B4;

            //when & then
            assertDoesNotThrow(() -> pawn.validateMove(from, to, board));
        }

        @Test
        void 상대_말이_있을_때만_위_대각선으로_한칸_이동할_수_있다() {
            //given
            Pawn pawn = new WhitePawn();

            Position from = FixturePosition.B2;
            Position to = FixturePosition.C3;

            //when & then
            assertDoesNotThrow(() -> pawn.validateMove(from, to, board));
        }

        @Test
        void 올바른_방향이_아니면_예외() {
            //given
            Pawn pawn = new WhitePawn();

            Position from = FixturePosition.B2;
            Position to = FixturePosition.C4;

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

            Position from = FixturePosition.B5;
            Position to = FixturePosition.B4;

            //when & then
            assertDoesNotThrow(() -> pawn.validateMove(from, to, board));
        }

        @Test
        void 첫_위치면_두칸_아래로_갈_수_있다() {
            //given
            Pawn pawn = new BlackPawn();

            Position from = FixturePosition.B7;
            Position to = FixturePosition.B5;

            //when & then
            assertDoesNotThrow(() -> pawn.validateMove(from, to, board));
        }

        @Test
        void 상대_말이_있을_때만_아래_대각선으로_한칸_이동할_수_있다() {
            //given
            Pawn pawn = new BlackPawn();

            Position from = FixturePosition.C7;
            Position to = FixturePosition.B6;

            //when & then
            assertDoesNotThrow(() -> pawn.validateMove(from, to, board));
        }

        @Test
        void 올바른_방향이_아니면_예외() {
            //given
            Pawn pawn = new BlackPawn();

            Position from = FixturePosition.B2;
            Position to = FixturePosition.C4;

            //when & then
            assertThatThrownBy(() -> pawn.validateMove(from, to, board))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("Pawn이 이동할 수 없는 경로입니다.");
        }
    }
}
