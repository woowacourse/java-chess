package chess.domain.piece;

import chess.domain.Position;
import chess.domain.TeamColor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class RookTest {

    @Test
    @DisplayName("룩의 이동 조건 테스트")
    void testAddMovable() {
        //given
        Rook rook = new Rook(TeamColor.WHITE, Position.of(4, 2));
        List<Position> existPiecePositions = Arrays.asList(
                Position.of(4, 0), Position.of(4, 3),
                Position.of(5, 2), Position.of(0, 2)
        );
        List<Position> enemiesPositions = Arrays.asList(
                Position.of(5, 2), Position.of(0, 2)
        );
        List<Position> expectedPosition = new ArrayList<>(
                Arrays.asList(
                        Position.of(4, 1),
                        Position.of(3, 2),
                        Position.of(2, 2),
                        Position.of(1, 2)
                ));
        expectedPosition.addAll(enemiesPositions);

        //when
        rook.addMovablePositions(existPiecePositions, enemiesPositions);
        List<Position> movablePosition = rook.movablePositions();

        //then
        assertThat(movablePosition).hasSameElementsAs(expectedPosition);
    }
}