package chess.domain.piece;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PieceFactoryTest {
    @Test
    @DisplayName("각 체스 말의 이름으로 해당 체스 말을 잘 생성하는 지 테스트")
    public void from() {
        Piece piece = PieceFactory.from('p', Color.BLACK, 'a', '3');
        assertThat(piece.getClass()).isEqualTo(Pawn.class);
    }
}
