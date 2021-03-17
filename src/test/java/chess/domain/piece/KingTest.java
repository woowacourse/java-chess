package chess.domain.piece;

import chess.domain.Position;
import chess.domain.TeamColor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class KingTest {

    @ParameterizedTest
    @CsvSource(value = {"0:0:false", "1:2:false", "1:0:true", "0:1:true", "1:1:true"}, delimiter = ':')
    @DisplayName("킹의 이동 조건 테스트")
    void testMovable(int x, int y, boolean expectedMovable) {
        //given
        Position currentPosition = Position.of(0, 0);
        Position targetPosition = Position.of(x, y);
        King king = new King(TeamColor.WHITE);

        //when
        boolean movable = king.movable(currentPosition, targetPosition);

        //then
        assertThat(movable).isEqualTo(expectedMovable);
    }
}