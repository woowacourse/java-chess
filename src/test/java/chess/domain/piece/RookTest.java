package chess.domain.piece;

import chess.domain.Color;
import chess.domain.position.Position;
import chess.domain.position.PositionX;
import chess.domain.position.PositionY;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

public class RookTest {
    @ParameterizedTest
    @CsvSource(value = {"BLACK:R", "WHITE:r"}, delimiter = ':')
    @DisplayName("기물의 색깔에 맞는 이름을 반환하는지")
    void checkNameByColor(Color color, String pieceName) {
        Rook rook = new Rook(color);

        assertThat(rook.signature()).isEqualTo(pieceName);
    }
}
