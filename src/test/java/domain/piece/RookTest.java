package domain.piece;

import domain.position.XPosition;
import domain.position.Position;
import domain.position.YPosition;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RookTest {

    @Test
    @DisplayName("Rook 은 상하좌우로 이동할 수 있다.")
    void movePawnOneSpace() {
        Piece piece = new Rook(Player.WHITE);

        Assertions.assertThat(piece.availableMove(new Position(XPosition.B, YPosition.TWO), new Position(XPosition.B, YPosition.THREE)))
                .isEqualTo(true);
    }
}
