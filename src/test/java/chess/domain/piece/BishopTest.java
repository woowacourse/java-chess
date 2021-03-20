package chess.domain.piece;

import static chess.domain.TeamColor.WHITE;

import chess.domain.Position;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BishopTest {

    @Test
    @DisplayName("기물들이 있을 때 비숍의 움직임 테스트")
    void movablePositions() {
        List<Position> expectedMovablePositions = Arrays.asList(
            Position.of(4, 4),
            Position.of(4, 2),
            Position.of(5, 1),
            Position.of(6, 0),
            Position.of(2, 2),
            Position.of(2, 4)
        );

        Bishop bishop = new Bishop(WHITE, Position.of(3,3));

        List<Position> existPiecePositions =
            new ArrayList<>(Arrays.asList(
                Position.of(1, 1),
                Position.of(5, 5),
                Position.of(6, 6)
            ));

        List<Position> enemiesPositions = Collections.singletonList(
            Position.of(2, 4)
        );
        existPiecePositions.addAll(enemiesPositions);

        bishop.updateMovablePositions(existPiecePositions, enemiesPositions);

        Assertions.assertThat(bishop.movablePositions()).hasSameElementsAs(expectedMovablePositions);
    }
}