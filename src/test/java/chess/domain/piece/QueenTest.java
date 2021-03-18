package chess.domain.piece;

import chess.domain.Position;
import chess.domain.TeamColor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class QueenTest {

    @Test
    @DisplayName("퀸의 이동 조건 테스트")
    void testAddMovable() {
        //given
        Queen queen = new Queen(TeamColor.WHITE, Position.of(3, 3));
        List<Position> existPiecePositions = Arrays.asList(
                Position.of(5, 5), Position.of(2, 2),
                Position.of(2, 4), Position.of(4, 2),
                Position.of(3, 4), Position.of(3, 2),
                Position.of(4, 3)
        );
        List<Position> enemiesPositions = Arrays.asList(
                Position.of(3, 4), Position.of(3, 2),
                Position.of(4, 3)
        );
        List<Position> expectedPosition = new ArrayList<>(Arrays.asList(
                Position.of(4, 4),
                Position.of(2, 3),
                Position.of(1, 3),
                Position.of(0, 3)
        ));
        expectedPosition.addAll(enemiesPositions);

        //when
        queen.addMovablePositions(existPiecePositions, enemiesPositions);
        List<Position> movablePosition = queen.movablePositions();

        //then
        assertThat(movablePosition).hasSameElementsAs(expectedPosition);
    }
}