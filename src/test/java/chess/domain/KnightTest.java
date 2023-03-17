package chess.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.piece.Knight;
import chess.domain.piece.Side;
import chess.domain.piece.Type;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class KnightTest {

    @Test
    void 타입이_잘못되면_예외를_던진다() {
        assertThatThrownBy(() -> new Knight(Type.EMPTY, Side.WHITE))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("나이트의 타입이 잘못되었습니다.");
    }

    @Test
    void 진영이_잘못되면_예외를_던진다() {
        assertThatThrownBy(() -> new Knight(Type.KNIGHT, Side.NEUTRALITY))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("나이트는 중립적인 기물이 아닙니다.");
    }
}
