package chess.domain.piece;

import chess.domain.Position;
import chess.domain.TeamColor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class RookTest {

    @ParameterizedTest
    @CsvSource(value = {"0:0:false", "1:1:false", "0:2:true", "2:0:true"}, delimiter = ':')
    @DisplayName("룩의 이동 조건 테스트")
    void testMovable(int x, int y, boolean expectedMovable) {
        //given
        Position currentPosition = Position.of(0, 0);
        Position targetPosition = Position.of(x, y);
        Rook rook = new Rook(TeamColor.WHITE);

        //when
        boolean movable = rook.movable(currentPosition, targetPosition);

        //then
        assertThat(movable).isEqualTo(expectedMovable);
    }
}