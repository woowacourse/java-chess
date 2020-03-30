import chess.domain.Player;
import chess.domain.chesspieces.Empty;
import chess.domain.chesspieces.King;
import chess.domain.position.Positions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class EmptyTest {
    @DisplayName("Empty의 Player 비교")
    @Test
    void comparePlayer() {
        Empty empty = Empty.getInstance();
        assertThat(empty.isSamePlayer(new King(Player.WHITE))).isFalse();
    }

    @DisplayName("(예외) empty는 이동 할 수 없다.")
    @Test
    void test2() {
        Empty empty = Empty.getInstance();
        assertThatThrownBy(() -> empty.movable(Positions.of("a1"), Positions.of("a2")))
                .isInstanceOf(UnsupportedOperationException.class);
    }
}
