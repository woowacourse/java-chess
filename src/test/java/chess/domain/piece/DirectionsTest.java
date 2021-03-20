package chess.domain.piece;

import chess.domain.Direction;
import chess.domain.Position;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DirectionsTest {

    @Test
    @DisplayName("계속해서 위로 올라갈 수 있는 말의 움직일 수 있는 자리를 테스트")
    void movablePositions_iterable() {
        List<Position> expectedMovablePositions =
            Arrays.asList(
                Position.of(0, 1),
                Position.of(0, 2),
                Position.of(0, 3),
                Position.of(0, 4)
            );

        Directions directions = new Directions(
            Collections.singletonList(Direction.UP),
            Collections.singletonList(Direction.UP)
        );

        Position currentPosition = Position.of(0, 0);
        List<Position> existPiecePositions = Collections.singletonList(Position.of(0, 5));
        boolean iterable = true;

        List<Position> movablePositions = directions.movablePositions(
            existPiecePositions,
            currentPosition,
            iterable
        );

        Assertions.assertThat(movablePositions).hasSameElementsAs(expectedMovablePositions);
    }

    @Test
    @DisplayName("딱 한 번만 위로 올라갈 수 있는 말의 움직일 수 있는 자리를 테스트")
    void movablePositions_notIterable() {
        List<Position> expectedMovablePositions = Collections.singletonList(Position.of(0, 1));

        Directions directions = new Directions(
            Collections.singletonList(Direction.UP),
            Collections.singletonList(Direction.UP)
        );

        Position currentPosition = Position.of(0, 0);
        List<Position> existPiecePositions = Collections.singletonList(Position.of(0, 5));
        boolean iterable = false;

        List<Position> movablePositions = directions.movablePositions(
            existPiecePositions,
            currentPosition,
            iterable
        );

        Assertions.assertThat(movablePositions).hasSameElementsAs(expectedMovablePositions);
    }

    @Test
    @DisplayName("계속해서 위로 올라갈 수 있는 말의 죽일 수 있는 자리를 테스트")
    void killablePositions_iterable() {
        List<Position> expectedMovablePositions = Collections.singletonList(Position.of(0, 5));

        Directions directions = new Directions(
            Collections.singletonList(Direction.UP),
            Collections.singletonList(Direction.UP)
        );

        Position currentPosition = Position.of(0, 0);
        List<Position> existPiecePositions = Collections.singletonList(Position.of(0, 5));
        boolean iterable = true;

        List<Position> movablePositions = directions.killablePositions(
            existPiecePositions,
            currentPosition,
            iterable
        );

        Assertions.assertThat(movablePositions).hasSameElementsAs(expectedMovablePositions);
    }

    @Test
    @DisplayName("딱 한 번만 위로 올라갈 수 있는 말의 죽일 수 있는 자리를 테스트")
    void killablePositions_notIterable() {
        List<Position> expectedMovablePositions = Collections.emptyList();

        Directions directions = new Directions(
            Collections.singletonList(Direction.UP),
            Collections.singletonList(Direction.UP)
        );

        Position currentPosition = Position.of(0, 0);
        List<Position> existPiecePositions = Collections.singletonList(Position.of(0, 5));
        boolean iterable = false;

        List<Position> movablePositions = directions.killablePositions(
            existPiecePositions,
            currentPosition,
            iterable
        );

        Assertions.assertThat(movablePositions).hasSameElementsAs(expectedMovablePositions);
    }


}