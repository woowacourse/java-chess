package chess.domain;

import chess.domain.piece.King;
import chess.domain.piece.Side;
import chess.domain.piece.Type;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class KingTest {

    @Test
    void 타입이_잘못되면_예외를_던진다() {
        assertThatThrownBy(() -> new King(Type.EMPTY, Side.WHITE))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("킹의 타입이 잘못되었습니다.");
    }

    @Test
    void 진영이_잘못되면_예외를_던진다() {
        assertThatThrownBy(() -> new King(Type.KING, Side.NEUTRALITY))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("킹은 중립적인 기물이 아닙니다.");
    }
}
