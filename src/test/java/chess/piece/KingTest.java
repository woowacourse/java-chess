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

class KingTest {

    private Map<Position, Piece> board;

    @BeforeEach
    void setUp() {
        board = new EmptyBoardFixture().getBoard();
    }

    @Nested
    class 킹이_움직일_때_이동방향은_ {
        @Test
        void 상하좌우_대각선으로_한칸이다() {
            //given
            King king = new King(Team.WHITE);

            Position from = PositionFixture.A1;
            Position to = PositionFixture.B2;

            //when & then
            assertDoesNotThrow(() -> king.validateMove(from, to, board));
        }

        @Test
        void 상하좌우_대각선으로_한칸이_아니면_예외() {
            King king = new King(Team.WHITE);

            Position from = PositionFixture.A1;
            Position to = PositionFixture.B3;

            //when & then
            assertThatThrownBy(() -> king.validateMove(from, to, board))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("King이 이동할 수 없는 경로입니다.");
        }
    }
}
