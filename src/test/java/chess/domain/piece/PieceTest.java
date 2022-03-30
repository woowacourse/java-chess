package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.piece.fixedmovablepiece.King;
import chess.domain.piece.straightmovablepiece.Rook;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class PieceTest {

    @ParameterizedTest
    @CsvSource(value = {"BLACK, true", "WHITE, false"})
    @DisplayName("말끼리 서로 같은 편인지 확인한다.")
    void isSameColor(Color color, boolean expected) {
        Rook rook = new Rook(Color.BLACK);
        King king = new King(color);

        assertThat(rook.isSameColor(king)).isEqualTo(expected);
    }
}
