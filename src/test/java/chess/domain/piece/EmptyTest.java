package chess.domain.piece;

import static chess.domain.PositionFixture.B_1;
import static chess.domain.PositionFixture.B_7;
import static chess.domain.piece.Color.WHITE;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
class EmptyTest {

    @Test
    void Empty말의_메서드를_호출하면_예외가_발생한다() {
        Empty empty = Empty.create();

        assertAll(
                () -> assertThatThrownBy(() -> empty.canMove(B_1, B_7, WHITE))
                        .isInstanceOf(UnsupportedOperationException.class)
                        .hasMessage("움직일 수 없습니다."),
                () -> assertThatThrownBy(() -> empty.move())
                        .isInstanceOf(UnsupportedOperationException.class)
                        .hasMessage("움직일 수 없습니다."));
    }

}
