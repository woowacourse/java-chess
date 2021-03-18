package chess.domain.piece;

import chess.domain.Position;
import chess.domain.TeamColor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class KnightTest {

    @Test
    @DisplayName("나이트의 이동 조건 테스트")
    void testAddMovable() {
        //given
        Knight knight = new Knight(TeamColor.WHITE, Position.of(3, 3));
        List<Position> existPiecePositions = Arrays.asList(
                Position.of(4, 1), Position.of(4, 5),
                Position.of(5, 4), Position.of(1, 4),
                Position.of(2, 1), Position.of(4, 1)
        );
        List<Position> enemiesPositions = Arrays.asList(
                Position.of(4, 1), Position.of(4, 5)
        );
        List<Position> expectedPosition = new ArrayList<>();
        expectedPosition.add(Position.of(5, 2));
        expectedPosition.add(Position.of(1, 2));
        expectedPosition.addAll(enemiesPositions);

        //when
        knight.addMovablePositions(existPiecePositions, enemiesPositions);
        List<Position> movablePosition = knight.movablePositions();

        //then
        assertThat(movablePosition).hasSameElementsAs(expectedPosition);
    }
}