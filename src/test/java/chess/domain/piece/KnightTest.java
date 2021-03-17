package chess.domain.piece;

import chess.domain.Position;
import chess.domain.TeamColor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class KnightTest {

    @ParameterizedTest
    @CsvSource(value = {"0:0:false", "1:1:false", "1:2:true", "2:1:true"}, delimiter = ':')
    @DisplayName("나이트의 이동 조건 테스트")
    void testMovable(int x, int y, boolean expectedMovable) {
        //given
        Position currentPosition = Position.of(0, 0);
        Position targetPosition = Position.of(x, y);
        Knight knight = new Knight(TeamColor.WHITE);

        //when
        boolean movable = knight.movable(currentPosition, targetPosition);

        //then
        assertThat(movable).isEqualTo(expectedMovable);
    }
}