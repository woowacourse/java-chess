package chess.domain.piece;

import org.junit.jupiter.api.Test;

import static chess.domain.position.Fixtures.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class BishopTest {

    @Test
    void moveTo_When_Success() {
        Piece bishop = new Bishop(C3, Team.BLACK);
        bishop.moveTo(H8);

        assertThat(bishop.getPosition()).isEqualTo(H8);
    }

    @Test
    void moveTo_When_Fail() {
        Piece bishop = new Bishop(C3, Team.BLACK);
        assertThatIllegalArgumentException()
                .isThrownBy(() -> bishop.moveTo(D3))
                .withMessage("기물의 이동 범위에 속하지 않습니다.");
    }
}