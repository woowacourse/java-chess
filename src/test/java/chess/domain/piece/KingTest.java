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

class KingTest {

    @Test
    @DisplayName("기물들이 있을 때 킹의 움직임 테스트")
    void movablePositions() {
        List<Position> expectedMovablePositions = Arrays.asList(
            Position.of(4, 2),
            Position.of(3, 4),
            Position.of(3, 2),
            Position.of(4, 4),
            Position.of(2, 4)
        );

        King king = new King(WHITE, Position.of(3, 3));

        List<PositionInformation> positionInformation = Arrays.asList(
            new PositionInformation(Position.of(2, 2), WHITE),
            new PositionInformation(Position.of(2, 3), WHITE),
            new PositionInformation(Position.of(4, 3), WHITE),
            new PositionInformation(Position.of(2, 4), BLACK),
            new PositionInformation(Position.of(4, 4), BLACK)
        );

        king.updateMovablePositions(positionInformation);

        Assertions.assertThat(king.movablePositions()).hasSameElementsAs(expectedMovablePositions);
    }
}