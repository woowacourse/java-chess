package chess.domain.piece;

import chess.domain.Color;
import chess.domain.position.Position;
import chess.domain.position.PositionX;
import chess.domain.position.PositionY;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

public class BishopTest {
    @ParameterizedTest
    @CsvSource(value = {"BLACK:B", "WHITE:b"}, delimiter = ':')
    @DisplayName("기물의 색깔에 맞는 이름을 반환하는지")
    void checkNameByColor(Color color, String pieceName) {
        Bishop bishop = new Bishop(new Position(PositionX.A, PositionY.RANK_2), color);

        assertThat(bishop.signature()).isEqualTo(pieceName);
    }
}
