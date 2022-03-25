package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.position.Position;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PieceFactoryTest {
    @DisplayName("팩토리로 생성시 크기 확인")
    @Test
    public void size() {
        //given
        Map<Position, Piece> pieces = PieceFactory.createPieces();

        //when
        int size = pieces.size();

        //then
        assertThat(size).isEqualTo(32);
    }
}
