package chess.domain.piece;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static chess.fixture.PieceFixture.EMPTY_PIECE;
import static chess.fixture.PositionFixture.A1;
import static chess.fixture.PositionFixture.B3;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class EmptyPieceTest {

    @Test
    void 빈_말에_대해_메소드를_수행하면_예외() {
        //when & then
        Assertions.assertThatThrownBy(() -> EMPTY_PIECE.isMovable(A1, B3))
                .isInstanceOf(UnsupportedOperationException.class)
                .hasMessage("빈 말은 움직일 수 없습니다.");
    }
}
