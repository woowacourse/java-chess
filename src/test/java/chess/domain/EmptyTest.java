package chess.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.piece.Empty;
import chess.domain.piece.Side;
import chess.domain.piece.Type;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class EmptyTest {

    @Test
    void 타입이_잘못되면_예외를_던진다() {
        assertThatThrownBy(() -> new Empty(Type.PAWN, Side.NEUTRALITY))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Empty의 타입이 잘못되었습니다.");
    }

    @Test
    void 진영이_잘못되면_예외를_던진다() {
        assertThatThrownBy(() -> new Empty(Type.EMPTY, Side.WHITE))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Empty는 진영을 가질 수 없습니다.");
    }
}
