package chess.model.piece;

import static chess.model.Fixtures.A3;
import static chess.model.Fixtures.A4;
import static chess.model.Fixtures.EMPTY_PIECES;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.model.material.Color;
import chess.model.material.Type;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class NoneTest {

    @DisplayName("빈 기물을 이동하면 예외가 발생한다")
    @Test
    void noneCanNotMove() {
        Piece piece = new None(Type.NONE, Color.NONE);
        assertThatThrownBy(() -> piece.move(A3, A4, EMPTY_PIECES))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("이동할 기물이 존재하지 않습니다.");
    }
}
