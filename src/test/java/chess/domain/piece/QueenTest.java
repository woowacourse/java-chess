package chess.domain.piece;

import chess.domain.Color;
import chess.domain.position.Position;
import chess.domain.position.PositionX;
import chess.domain.position.PositionY;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

public class QueenTest {
    @ParameterizedTest
    @CsvSource(value = {"BLACK:Q", "WHITE:q"}, delimiter = ':')
    @DisplayName("기물의 색깔에 맞는 이름을 반환하는지")
    void checkNameByColor(Color color, String pieceName) {
        Queen queen = new Queen(new Position(PositionX.A, PositionY.RANK_2), color);

        assertThat(queen.signature()).isEqualTo(pieceName);
    }
}
