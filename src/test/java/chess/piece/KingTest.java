package chess.piece;

import chess.Position;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;

class KingTest {

    @Test
    @DisplayName("initialPosition() : 팀에 따라서 킹의 초기 위치는 달라질 수 있다.")
    void test_initialPosition(){
        //given
        final List<Position> kingWhitePositions = King.initialPosition(Team.WHITE);
        final List<Position> kingBlackPositions = King.initialPosition(Team.BLACK);

        //when & then
        assertAll(
                () -> Assertions.assertThat(kingWhitePositions)
                                .containsExactly(new Position(5, 1)),
                () -> Assertions.assertThat(kingBlackPositions)
                                .containsExactly(new Position(5, 8))
        );
    }
}
