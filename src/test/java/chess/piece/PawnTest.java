package chess.piece;

import chess.Position;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;

class PawnTest {
    @Test
    @DisplayName("initialPosition() : 팀에 따라서 폰의 초기 위치는 달라질 수 있다.")
    void test_initialPosition() {
        //given
        final List<Position> pawnWhitePositions = Pawn.initialPosition(Team.WHITE);
        final List<Position> pawnBlackPositions = Pawn.initialPosition(Team.BLACK);

        //when & then
        assertAll(
                () -> Assertions.assertThat(pawnWhitePositions)
                                .contains(new Position(1,2),
                                          new Position(2,2),
                                          new Position(3,2),
                                          new Position(4,2),
                                          new Position(5,2),
                                          new Position(6,2),
                                          new Position(7,2),
                                          new Position(8,2)),
                () -> Assertions.assertThat(pawnBlackPositions)
                                .contains(new Position(1,7),
                                          new Position(2,7),
                                          new Position(3,7),
                                          new Position(4,7),
                                          new Position(5,7),
                                          new Position(6,7),
                                          new Position(7,7),
                                          new Position(8,7))
        );
    }
}
