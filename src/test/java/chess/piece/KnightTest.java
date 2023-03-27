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

class KnightTest {

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
    class 나이트가_움직일_때_이동방향은_ {
        @Test
        void 두_칸_전진한_상태에서_좌우로_한_칸_움직일_수_있다() {
            //given
            Knight knight = new Knight(Team.WHITE);

            Position from = FixturePosition.A1;
            Position to = FixturePosition.C2;

            //when & then
            assertDoesNotThrow(() -> knight.validateMove(from, to, board));
        }

        @Test
        void 대각선이_아니면_예외() {
            //given
            Knight knight = new Knight(Team.WHITE);

            Position from = FixturePosition.A1;
            Position to = FixturePosition.B8;

            //when & then
            assertThatThrownBy(() -> knight.validateMove(from, to, board))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("Knight가 이동할 수 없는 경로입니다.");
        }
    }
}
