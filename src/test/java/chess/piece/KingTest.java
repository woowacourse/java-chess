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

class KingTest {

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
    class 킹이_움직일_때_이동방향은_ {
        @Test
        void 상하좌우_대각선으로_한칸이다() {
            //given
            King king = new King(Team.WHITE);

            Position from = FixturePosition.A1;
            Position to = FixturePosition.B2;

            //when & then
            assertDoesNotThrow(() -> king.validateMove(from, to, board));
        }

        @Test
        void 상하좌우_대각선으로_한칸이_아니면_예외() {
            King king = new King(Team.WHITE);

            Position from = FixturePosition.A1;
            Position to = FixturePosition.B3;

            //when & then
            assertThatThrownBy(() -> king.validateMove(from, to, board))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("King이 이동할 수 없는 경로입니다.");
        }
    }
}
