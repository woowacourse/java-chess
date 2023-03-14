package chess.piece;

import chess.Position;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;

class BishopTest {

    @Test
    @DisplayName("initialPosition() : 팀에 따라서 비숍의 초기 위치는 달라질 수 있다.")
    void test_initialPosition() {
        //given
        final List<Position> bishopWhitePositions = Bishop.initialPosition(Team.WHITE);
        final List<Position> bishopBlackPositions = Bishop.initialPosition(Team.BLACK);

        //when & then
        assertAll(
                () -> Assertions.assertThat(bishopWhitePositions)
                                .contains(new Position(3, 1),
                                          new Position(6, 1)),
                () -> Assertions.assertThat(bishopBlackPositions)
                                .contains(new Position(3, 8),
                                          new Position(6, 8))
        );
    }
}
