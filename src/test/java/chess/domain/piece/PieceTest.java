package chess.domain.piece;

import static org.assertj.core.api.Assertions.*;

import chess.domain.piece.property.Color;
import chess.domain.piece.state.started.StartedBishop;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PieceTest {
    @Test
    @DisplayName("체스말은 생성될때 색, 이름, 상태을 가진다.")
    void pieceTest() {
        assertThatCode(() -> new Piece(Color.BLACK, "k", 0, new StartedBishop()) {})
                .doesNotThrowAnyException();
    }
}
