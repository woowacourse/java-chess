package chess.domain.piece;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class KnightTest {
    private CurrentPieces currentPieces;

    @BeforeEach
    void setUp() {
        currentPieces = CurrentPieces.generate();
    }

    @DisplayName("Knight 객체 생성 확인")
    @Test
    void 나이트_객체_생성() {
        Knight knight = new Knight(Position.of('b', '8'), "N");

        assertThat(knight.getPosition()).isEqualTo(Position.of('b', '8'));
        assertThat(knight.getName()).isEqualTo("N");
    }

    @DisplayName("초기화된 Knight 객체들 생성 확인")
    @Test
    void 나이트_객체들_생성() {
        List<Knight> nights = Knight.generate();

        assertThat(nights.size()).isEqualTo(4);
    }
}
