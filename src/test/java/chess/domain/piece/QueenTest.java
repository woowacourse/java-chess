package chess.domain.piece;

import static chess.domain.TeamColor.WHITE;

import chess.domain.Position;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class QueenTest {

    @Test
    @DisplayName("기물들이 있을 때 퀸의 움직임 테스트")
    void movablePositions() {
        List<Position> expectedMovablePositions = Arrays.asList(
            Position.of(3, 4),
            Position.of(3, 5),
            Position.of(3, 6),
            Position.of(3, 7),
            Position.of(3, 2),
            Position.of(3, 1),
            Position.of(3, 0),
            Position.of(4, 4),
            Position.of(2, 4)
        );

        Queen queen = new Queen(WHITE, Position.of(3, 3));

        List<Position> existPiecePositions =
            new ArrayList<>(Arrays.asList(
                Position.of(2, 2),
                Position.of(2, 3),
                Position.of(4, 3),
                Position.of(4, 2),
                Position.of(5, 1)
            ));

        List<Position> enemiesPositions = Arrays.asList(
            Position.of(2, 4),
            Position.of(4, 4)
        );
        existPiecePositions.addAll(enemiesPositions);

        queen.updateMovablePositions(existPiecePositions, enemiesPositions);

        Assertions.assertThat(queen.movablePositions()).hasSameElementsAs(expectedMovablePositions);
    }
}