package chess.domain.piece;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.domain.Color;
import chess.domain.File;
import chess.domain.Position;
import chess.domain.Rank;

public class PieceTest {
    @Test
    @DisplayName("체스말은 생성될때 위치, 색, 이름을 가진다.")
    void pieceTest() {
        assertThatCode(() -> mock(Piece.class,
            withSettings()
            .useConstructor(Position.of(File.a, Rank.One), Color.Black, "k")
            .defaultAnswer(CALLS_REAL_METHODS)))
            .doesNotThrowAnyException();
    }
}
