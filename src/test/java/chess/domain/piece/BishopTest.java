package chess.domain.piece;

import chess.domain.Position;
import chess.domain.TeamColor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

public class BishopTest {

    @ParameterizedTest
    @CsvSource(value = {"0:0:false", "3:2:false", "3:3:true"}, delimiter = ':')
    @DisplayName("비숍의 이동 조건 테스트")
    void testMovable(int x, int y, boolean expectedMovable) {
        //given
        Position currentPosition = Position.of(0, 0);
        Position targetPosition = Position.of(x, y);
        Bishop bishop = new Bishop(TeamColor.WHITE);

        //when
        boolean movable = bishop.movable(currentPosition, targetPosition);

        //then
        assertThat(movable).isEqualTo(expectedMovable);
    }
}
