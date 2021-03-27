package chess.domain.piece;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class PieceTypeTest {

    @DisplayName("흑백말은 대문자로 표현한다.")
    @ParameterizedTest
    @CsvSource(value = {"PAWN:P", "ROOK:R", "KNIGHT:N", "BISHOP:B", "QUEEN:Q",
        "KING:K"}, delimiter = ':')
    void toBlack(PieceType type, String expected) {
        assertEquals(type.toBlack(), expected);
    }
}