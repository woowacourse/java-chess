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

    @Test
    @DisplayName("체스말은 흑팀과 백팀으로 나뉜다.")
    void team() {
        assertThatCode(() -> new Piece(Position.of(File.a, Rank.One), Color.Black))
            .doesNotThrowAnyException();
    }
}
