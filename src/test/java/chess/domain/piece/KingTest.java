package chess.domain.piece;

import chess.domain.Position;
import chess.domain.TeamColor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class KingTest {

    @Test
    @DisplayName("킹의 이동 조건 테스트")
    void testAddMovable() {
        //given
        King king = new King(TeamColor.WHITE, Position.of(1, 1));
        List<Position> existPiecePositions = Arrays.asList(
                Position.of(0, 0), Position.of(0, 1),
                Position.of(0, 2), Position.of(1, 2),
                Position.of(2, 2), Position.of(2, 1),
                Position.of(2, 0)
        );
        List<Position> enemiesPositions = Arrays.asList(
                Position.of(2, 1), Position.of(2, 0)
        );
        List<Position> expectedPosition = new ArrayList<>();
        expectedPosition.add(Position.of(1, 0));
        expectedPosition.addAll(enemiesPositions);

        //when
        king.addMovablePositions(existPiecePositions, enemiesPositions);
        List<Position> movablePosition = king.movablePositions();

        //then
        assertThat(movablePosition).hasSameElementsAs(expectedPosition);
    }
}