package chess.domain.piece;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class RookTest {
    private CurrentPieces currentPieces;

    @BeforeEach
    void setUp() {
        currentPieces = CurrentPieces.generate();
    }

    @DisplayName("Rook 객체 생성 확인")
    @Test
    void 룩_객체_생성() {
        Rook rook = new Rook(Position.of('a', '8'), "P");

        assertThat(rook.getPosition()).isEqualTo(Position.of('a', '8'));
        assertThat(rook.getName()).isEqualTo("P");
    }

    @DisplayName("초기화된 룩 객체들 생성 확인")
    @Test
    void 룩_객체들_생성() {
        List<Rook> rooks = Rook.generate();

        assertThat(rooks.size()).isEqualTo(4);
    }
}
