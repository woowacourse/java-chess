package chess.domain.piece;

import chess.domain.Position;
import chess.domain.TeamColor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class QueenTest {

    @ParameterizedTest
    @CsvSource(value = {"0:0:false", "3:2:false", "0:2:true", "2:0:true", "2:2:true"}, delimiter = ':')
    @DisplayName("퀸의 이동 조건 테스트")
    void testMovable(int x, int y, boolean expectedMovable) {
        //given
        Position currentPosition = Position.of(0, 0);
        Position targetPosition = Position.of(x, y);
        Queen queen = new Queen(TeamColor.WHITE);

        //when
        boolean movable = queen.movable(currentPosition, targetPosition);

        //then
        assertThat(movable).isEqualTo(expectedMovable);
    }
}