package chess.domain.piece;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static chess.fixture.PieceFixture.KING_WHITE;
import static chess.fixture.PositionFixture.A1;
import static chess.fixture.PositionFixture.B2;
import static chess.fixture.PositionFixture.B3;
import static org.assertj.core.api.Assertions.assertThat;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class KingTest {

    @Nested
    class 킹이_움직일_때_이동방향은_ {
        @Test
        void 상하좌우_대각선으로_한칸이다() {
            //when & then
            assertThat(KING_WHITE.isMovable(A1, B2)).isTrue();
        }

        @Test
        void 상하좌우_대각선으로_한칸이_아니면_False() {
            //when & then
            assertThat(KING_WHITE.isMovable(A1, B3)).isFalse();
        }
    }
}
