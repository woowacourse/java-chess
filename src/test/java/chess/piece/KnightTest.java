package chess.piece;

import chess.Position;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class KnightTest {

    @Test
    @DisplayName("initialPosition() : 팀에 따라서 나이트의 초기 위치는 달라질 수 있다.")
    void test_initialPosition() {
        //given
        final List<Position> knightWhitePositions = Knight.initialPosition(Team.WHITE);
        final List<Position> knightBlackPositions = Knight.initialPosition(Team.BLACK);

        //when & then
        assertAll(
                () -> Assertions.assertThat(knightWhitePositions)
                                .contains(new Position(2, 1),
                                          new Position(7, 1)),
                () -> Assertions.assertThat(knightBlackPositions)
                                .contains(new Position(2, 8),
                                          new Position(7, 8))
        );
    }
}
