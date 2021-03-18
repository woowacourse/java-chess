package chess.domain.piece;

import chess.domain.Position;
import chess.domain.TeamColor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class BishopTest {

    @Test
    @DisplayName("비숍의 이동 조건 테스트")
    void testAddMovable() {
        //given
        Bishop bishop = new Bishop(TeamColor.WHITE, Position.of(4, 2));
        List<Position> existPiecePositions = Arrays.asList(
                Position.of(2, 0), Position.of(2, 4),
                Position.of(6, 0), Position.of(6, 4)
        );
        List<Position> enemiesPositions = Arrays.asList(
                Position.of(6, 0), Position.of(6, 4)
        );
        List<Position> expectedPosition = new ArrayList<>(
                Arrays.asList(
                        Position.of(5, 3),
                        Position.of(5, 1),
                        Position.of(3, 3),
                        Position.of(3, 1)
                ));
        expectedPosition.addAll(enemiesPositions);

        //when
        bishop.addMovablePositions(existPiecePositions, enemiesPositions);
        List<Position> movablePosition = bishop.movablePositions();

        //then
        assertThat(movablePosition).hasSameElementsAs(expectedPosition);
    }
}
