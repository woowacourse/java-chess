package chess.domain.piece;

import chess.domain.piece.attribute.Color;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ColoredPiecesTest {
    ColoredPieces coloredPieces;

    @BeforeEach
    void setUp() {
        coloredPieces = ColoredPieces.createByColor(Color.BLACK);
    }

    @DisplayName("색깔별 기물들을 만들어주는지 확인한다")
    @ParameterizedTest
    @EnumSource(Color.class)
    void createByColorTest(Color color) {
        ColoredPieces coloredPieces = ColoredPieces.createByColor(color);

        assertThat(coloredPieces.size()).isEqualTo(16);
    }

    @DisplayName("킹이 살아있는지 확인한다")
    @Test
    void isKingAliveTest() {
        assertThat(coloredPieces.isKingAlive()).isTrue();
    }

    @DisplayName("기물을 제거한다")
    @Test
    void removeTest() {
        assertThat(coloredPieces.contains(new Rook(Color.BLACK))).isTrue();

        coloredPieces.remove(new Rook(Color.BLACK));
        coloredPieces.remove(new Rook(Color.BLACK));
        assertThat(coloredPieces.contains(new Rook(Color.BLACK))).isFalse();
    }

    @DisplayName("다른 진영의 말을 제거하려하면 예외")
    @Test
    void throwExceptionWhenOtherColorRemove() {
        assertThatThrownBy(() -> coloredPieces.remove(new Rook(Color.WHITE)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("진영이 다른 피스를 제거할 수 없습니다");
    }
}