package chess.domain.piece;

import static chess.domain.TeamColor.BLACK;
import static chess.domain.TeamColor.WHITE;

import chess.domain.Position;
import chess.domain.PositionInformation;
import java.util.Arrays;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RookTest {

    @Test
    @DisplayName("기물들이 있을 때 룩의 움직임 테스트")
    void movablePositions() {
        List<Position> expectedMovablePositions = Arrays.asList(
            Position.of(3, 4),
            Position.of(3, 2),
            Position.of(3, 1),
            Position.of(3, 0),
            Position.of(2, 3),
            Position.of(1, 3),
            Position.of(0, 3),
            Position.of(4, 3)
        );

        Rook rook = new Rook(WHITE, Position.of(3, 3));

        List<PositionInformation> positionInformation = Arrays.asList(
            new PositionInformation(Position.of(3, 5), WHITE),
            new PositionInformation(Position.of(4, 3), BLACK)
        );

        rook.updateMovablePositions(positionInformation);

        Assertions.assertThat(rook.movablePositions()).hasSameElementsAs(expectedMovablePositions);
    }
}