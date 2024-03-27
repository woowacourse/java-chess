package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class PieceColorTest {

    @DisplayName("다음 턴으로 전환한다.")
    @ParameterizedTest
    @CsvSource({"BLACK, WHITE", "WHITE, BLACK"})
    void changeTurn(final PieceColor pieceColor, final PieceColor expected) {
        final PieceColor actual = pieceColor.next();

        assertThat(actual).isEqualTo(expected);
    }
}
