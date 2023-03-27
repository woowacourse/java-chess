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

class RookTest {

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
    class 룩이_움직일_때_이동방향은_ {
        @Test
        void 동일한_Rank_혹은_File이다() {
            //given
            Rook rook = new Rook(Team.WHITE);

            Position from = FixturePosition.A1;
            Position to = FixturePosition.A8;

            //when & then
            assertDoesNotThrow(() -> rook.validateMove(from, to, board));
        }

        @Test
        void 동일한_Rank_혹은_File이_아니면_예외() {
            //given
            Rook rook = new Rook(Team.WHITE);

            Position from = FixturePosition.A1;
            Position to = FixturePosition.B8;

            //when & then
            assertThatThrownBy(() -> rook.validateMove(from, to, board))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("같은 Rank가 아니면 움직일 수 없습니다.");
        }
    }
}
