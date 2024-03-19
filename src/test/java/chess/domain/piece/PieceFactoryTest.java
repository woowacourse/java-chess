package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PieceFactoryTest {

    @Test
    @DisplayName("기물들을 생성할 수 있다.")
    void create() {
        List<Piece> pieces = PieceFactory.create();

        assertThat(pieces).hasSize(32);
    }
}