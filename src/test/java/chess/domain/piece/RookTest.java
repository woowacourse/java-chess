package chess.domain.piece;

import static chess.domain.PositionFixture.*;
import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.Color;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RookTest {
    @Test
    @DisplayName("이동 가능 여부 테스트")
    void canMove(){
        // given
        Piece rook = new Rook(Color.WHITE);
        Piece opponent = new Rook(Color.BLACK);

        // when
        List<Integer> distance = B1.calculateDistance(B3);
        boolean movable = rook.movable(distance, opponent);

        // then
        assertThat(movable).isTrue();
    }
}