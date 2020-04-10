package chess.domain.piece;

import chess.domain.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PieceGeneratorTest {
    @Test
    @DisplayName("DB의 정보로 말을 잘 만드는지 확인")
    void generatePiece() {
        assertThat(PieceGenerator.generate("P", "WHITE", "e3"))
                .isEqualTo(new Pawn(new Position("e3"), Team.WHITE));
    }

    @Test
    @DisplayName("잘못된 정보가 들어올 시 예외 처리")
    void wrongPiece() {
        assertThatThrownBy(() -> PieceGenerator.generate("X", "WHITE", "e3"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("없는 기물");
    }
}
