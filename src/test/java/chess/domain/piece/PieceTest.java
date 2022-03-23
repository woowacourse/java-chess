package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import chess.domain.Color;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class PieceTest {

    @DisplayName("King을 생성한다.")
    @Test
    void King을_생성한다() {
        assertDoesNotThrow(() -> new King(Color.BLACK));
    }

    @DisplayName("진영에 맞는 표기 정보를 반환한다")
    @ParameterizedTest
    @CsvSource(value = {"BLACK,K", "WHITE,k"})
    void 진영에_맞는_표기_정보를_반환한다(Color color, String notation) {
        Piece piece = new King(color);

        assertThat(piece.getNotation()).isEqualTo(notation);
    }
}
