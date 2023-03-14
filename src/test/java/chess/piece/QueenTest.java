package chess.piece;

import chess.Position;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;

class QueenTest {

    @Test
    @DisplayName("initialPosition() : 팀에 따라서 퀸의 초기 위치는 달라질 수 있다.")
    void test_initialPosition() {
        //given
        final List<Position> queenWhitePositions = Queen.initialPosition(Team.WHITE);
        final List<Position> queenBlackPositions = Queen.initialPosition(Team.BLACK);

        //when & then
        assertAll(
                () -> Assertions.assertThat(queenWhitePositions)
                                .containsExactly(new Position(4, 1)),
                () -> Assertions.assertThat(queenBlackPositions)
                                .containsExactly(new Position(4, 8))
        );
    }
}
