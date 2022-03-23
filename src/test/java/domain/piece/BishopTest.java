package domain.piece;

import domain.position.Position;
import domain.position.XPosition;
import domain.position.YPosition;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BishopTest {

    @Test
    @DisplayName("Bishop 은 대각선으로 이동할 수 있다.")
    void movePawnOneSpace() {
        Piece piece = new Bishop(Player.WHITE);

        Assertions.assertThat(piece.availableMove(new Position(XPosition.B, YPosition.TWO), new Position(XPosition.A, YPosition.THREE)))
                .isEqualTo(true);
    }
}
