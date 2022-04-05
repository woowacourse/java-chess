package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PieceFactoryTest {

    @DisplayName("DB에서 받은 color와 piece 문자열로 Piece를 만들 수 있다.")
    @Test
    void createPieceTest() {
        Piece origin = new Bishop(Color.WHITE);
        String color = origin.getColor();
        String type = origin.getType();

        Piece piece = PieceFactory.create(color, type);

        assertThat(piece).isEqualTo(origin);
    }
}
