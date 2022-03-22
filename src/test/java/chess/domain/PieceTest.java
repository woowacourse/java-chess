package chess.domain;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PieceTest {
    @Test
    @DisplayName("체스말은 생성될때 위치를 가진다.")
    void pieceTest() {
        assertThatCode(() -> mock(Piece.class,
            withSettings()
            .useConstructor(Position.of(File.a, Rank.One))
            .defaultAnswer(CALLS_REAL_METHODS)))
            .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("체스말은 흑팀과 백팀으로 나뉜다.")
    void team() {
        assertThatCode(() -> mock(Piece.class,
            withSettings()
                .useConstructor(Position.of(File.a, Rank.One), Color.Black)
                .defaultAnswer(CALLS_REAL_METHODS)))
            .doesNotThrowAnyException();
    }
}
