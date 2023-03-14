package chess.piece;

import chess.Position;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;

class RookTest {

    @Test
    @DisplayName("initialPosition() : 팀에 따라서 룩의 초기 위치는 달라질 수 있다.")
    void test_initialPosition() {
        //given
        final List<Position> rookWhitePositions = Rook.initialPosition(Team.WHITE);
        final List<Position> rookBlackPositions = Rook.initialPosition(Team.BLACK);

        //when & then
        assertAll(
                () -> Assertions.assertThat(rookWhitePositions)
                                .contains(new Position(1, 1),
                                          new Position(8, 1)),
                () -> Assertions.assertThat(rookBlackPositions)
                                .contains(new Position(5, 8),
                                          new Position(8, 8))
        );
    }
}
