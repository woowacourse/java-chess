package chess.domain.piece;

import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

public class EmptyTest {
    @Test
    @DisplayName("Empty 정상적으로 생성하는 지 테스트")
    public void init() {
        assertThatCode(() -> {
            new Bishop(Color.BLACK, 'a', '2');
            new Bishop(Color.BLACK, 'a', 2);
        }).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("Empty의 서로 다른 생성자에 따라서, 올바르게 포지션이 들어가는 지 테스트")
    public void init_isSamePosition() {
        Piece pawn = new Bishop(Color.BLACK, 'a', '2');
        Piece pawn2 = new Bishop(Color.BLACK, 'a', 2);
        Position expectedPosition = new Position("a2");
        assertThat(pawn.position()).isEqualTo(expectedPosition);
        assertThat(pawn2.position()).isEqualTo(expectedPosition);
    }
}
