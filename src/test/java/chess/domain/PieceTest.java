package chess.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PieceTest {
    @Test
    @DisplayName("체스말은 생성될때 위치를 가진다.")
    void pieceTest() {
        assertThatCode(() -> new Piece(Position.of(File.a, Rank.One)))
            .doesNotThrowAnyException();
    }
}
