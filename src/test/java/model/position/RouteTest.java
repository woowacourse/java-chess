package model.position;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import model.direction.Direction;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RouteTest {

    @Test
    @DisplayName("출발 지점부터 도착 지점까지의 Route를 반환한다.")
    void subRoute_ShouldReturnSubListOfHoleRoute_WhenGivenDestination() {
        // When
        List<Position> wholePositions = new ArrayList<>();
        for (int rank = 1; rank <= 8; rank++) {
            wholePositions.add(Position.of(1, rank));
        }
        Route initialRoute = new Route(Direction.N, wholePositions);

        List<Position> subPositions = new ArrayList<>();
        for (int rank = 1; rank <= 4; rank++) {
            subPositions.add(Position.of(1, rank));
        }

        Route subRoute = initialRoute.directRouteTo(Position.of(1, 4));

        assertEquals(subPositions, subRoute.getPositions());
    }

    @Test
    @DisplayName("주어진 루트를 뒤집어야 한다")
    void reverseRouteTowardSource_Should_ReverseBasicRoute_When_RouteIsGiven() {
        // Given
        List<Position> positions = Arrays.asList(Position.of(2, 3), Position.of(3, 4), Position.of(4, 5));
        Direction direction = Direction.NE;
        List<Position> reversedPositions = Arrays.asList(Position.of(3, 4), Position.of(2, 3), Position.of(1, 2));
        Direction reversedDirection = direction.toOppositeDirection();
        Route expectedReversedRoute = new Route(reversedDirection, reversedPositions);

        // When
        Route route = new Route(direction, positions);
        Route reversedRoute = route.reverseRouteTowardSource();

        // Then
        assertEquals(expectedReversedRoute, reversedRoute);
    }

    @Test
    @DisplayName("체스판 경계의 포지션을 포함하는 루트를 뒤집어야 한다")
    void reverseRouteTowardSource_Should_HandleEdgePositions_When_RouteIncludesBoardEdge() {
        // Given
        List<Position> positions = Arrays.asList(Position.of(1, 2), Position.of(1, 3));
        Route route = new Route(Direction.N, positions);

        // When & Then
        assertDoesNotThrow(route::reverseRouteTowardSource);
    }

    @Test
    @DisplayName("Single Shift로 이루어진 루트를 뒤집을 수 있어야 한다.")
    void reverseRouteTowardSource_Should_Fail_When_RouteHasSinglePosition() {
        // Given
        List<Position> positions = Arrays.asList(Position.of(4, 4));
        Direction direction = Direction.E;
        Direction reversedDirection = direction.toOppositeDirection();
        List<Position> reversedPositions = Arrays.asList(Position.of(3, 4));

        List<Position> positions2 = Arrays.asList(Position.of(4, 4));
        Direction direction2 = Direction.NNE;
        Direction reversedDirection2 = direction2.toOppositeDirection();
        List<Position> reversedPositions2 = Arrays.asList(Position.of(3, 2));

        Route route = new Route(direction, positions);
        Route route2 = new Route(direction2, positions2);

        Route reveredRoute1 = new Route(reversedDirection, reversedPositions);
        Route reveredRoute2 = new Route(reversedDirection2, reversedPositions2);
        // When & Then
        assertAll(() -> {
            assertEquals(reveredRoute1, route.reverseRouteTowardSource(), "E 반대 방향으로 한칸 움직이는 루트를 구하지 못했습니다.");
            assertEquals(reveredRoute2, route2.reverseRouteTowardSource(), "NNE 반대 방향으로 한칸 움직이는 루트를 구하지 못했습니다.");

        });
    }

}
