package chess.domain.piece;

import org.junit.jupiter.api.Test;

import static chess.domain.position.Fixtures.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class KnightTest {

    @Test
    void moveTo_When_Success() {
        Piece knight = new Knight(C3, Team.BLACK);
        knight.moveTo(D1);
        knight.moveTo(B2);

        assertThat(knight.getPosition()).isEqualTo(B2);
    }

    @Test
    void moveTo_When_Fail() {
        Piece knight = new Knight(C3, Team.BLACK);
        assertThatIllegalArgumentException()
                .isThrownBy(() -> knight.moveTo(D3))
                .withMessage("기물의 이동 범위에 속하지 않습니다.");
    }
}
