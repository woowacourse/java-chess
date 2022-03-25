package chess.domain.piece;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.domain.piece.property.Color;
import chess.domain.piece.state.started.StartedBishop;

public class PieceTest {
    @Test
    @DisplayName("체스말은 생성될때 색, 이름, 상태을 가진다.")
    void pieceTest() {
        assertThatCode(() -> mock(Piece.class,
            withSettings()
                .useConstructor(Color.Black, "k", new StartedBishop())
                .defaultAnswer(CALLS_REAL_METHODS)))
            .doesNotThrowAnyException();
    }
}
