package model.piece.role;

import static model.direction.Direction.N;
import static model.direction.Direction.NE;
import static model.direction.Direction.NW;
import static model.direction.Direction.S;
import static model.direction.Direction.SE;
import static model.direction.Direction.SW;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import model.piece.Color;
import model.position.Position;
import model.position.Route;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PawnTest {

    @Test
    @DisplayName("WHITE Pawn의 초기 위치에서 목적지 전까지의 경로인 Route를 반환한다.")
    void findDirectRoute_ReturnPossiblePositions_WhenAtInitialPosition_AndColorIsWhite() {
        Role pawn = Pawn.from(Color.WHITE);
        Position initialPosition = Position.of(5, 2);
        Position destination1 = Position.of(5, 4);
        Position destination2 = Position.of(5, 3);
        Position destination3 = Position.of(6, 3);
        Position destination4 = Position.of(4, 3);

        Route actualRoute1 = pawn.findDirectRoute(initialPosition, destination1);
        Route actualRoute2 = pawn.findDirectRoute(initialPosition, destination2);
        Route actualRoute3 = pawn.findDirectRoute(initialPosition, destination3);
        Route actualRoute4 = pawn.findDirectRoute(initialPosition, destination4);

        Route expectedRoutes1 = new Route(N, List.of(Position.of(5, 3), Position.of(5, 4)));
        Route expectedRoutes2 = new Route(N, List.of(Position.of(5, 3)));
        Route expectedRoutes3 = new Route(NE, List.of(Position.of(6, 3)));
        Route expectedRoutes4 = new Route(NW, List.of(Position.of(4, 3)));

        assertAll(() -> {
            assertEquals(expectedRoutes1, actualRoute1);
            assertEquals(expectedRoutes2, actualRoute2);
            assertEquals(expectedRoutes3, actualRoute3);
            assertEquals(expectedRoutes4, actualRoute4);
        });
    }

    @Test
    @DisplayName("BLACK Pawn의 초기 위치에서 목적지 전까지의 경로인 Route를 반환한다.")
    void findDirectRoute_ReturnPossiblePositions_WhenAtInitialPosition_AndColorIsBlack() {
        Role pawn = Pawn.from(Color.BLACK);
        Position initialPosition = Position.of(5, 7);
        Position destination1 = Position.of(5, 5);
        Position destination2 = Position.of(5, 6);
        Position destination3 = Position.of(6, 6);
        Position destination4 = Position.of(4, 6);

        Route actualRoute1 = pawn.findDirectRoute(initialPosition, destination1);
        Route actualRoute2 = pawn.findDirectRoute(initialPosition, destination2);
        Route actualRoute3 = pawn.findDirectRoute(initialPosition, destination3);
        Route actualRoute4 = pawn.findDirectRoute(initialPosition, destination4);

        Route expectedRoutes1 = new Route(S, List.of(Position.of(5, 6), Position.of(5, 5)));
        Route expectedRoutes2 = new Route(S, List.of(Position.of(5, 6)));
        Route expectedRoutes3 = new Route(SE, List.of(Position.of(6, 6)));
        Route expectedRoutes4 = new Route(SW, List.of(Position.of(4, 6)));

        assertAll(() -> {
            assertEquals(expectedRoutes1, actualRoute1);
            assertEquals(expectedRoutes2, actualRoute2);
            assertEquals(expectedRoutes3, actualRoute3);
            assertEquals(expectedRoutes4, actualRoute4);
        });
    }

    @Test
    @DisplayName("WHITE Pawn이 초기 위치가 아닌 위치에서 목적지 전까지의 경로인 Route를 반환한다.")
    void findDirectRoute_ReturnIllegalRoutes_WhenCurrentPositionIsGiven_AndColorIsWhite() {

        Role pawn = Pawn.from(Color.WHITE);
        Position initialPosition = Position.of(5, 3);
        Position destination1 = Position.of(5, 4);
        Position destination2 = Position.of(6, 4);
        Position destination3 = Position.of(4, 4);

        Route actualRoute1 = pawn.findDirectRoute(initialPosition, destination1);
        Route actualRoute2 = pawn.findDirectRoute(initialPosition, destination2);
        Route actualRoute3 = pawn.findDirectRoute(initialPosition, destination3);

        Route expectedRoutes1 = new Route(N, List.of(Position.of(5, 4)));
        Route expectedRoutes2 = new Route(NE, List.of(Position.of(6, 4)));
        Route expectedRoutes3 = new Route(NW, List.of(Position.of(4, 4)));

        assertAll(() -> {
            assertEquals(expectedRoutes1, actualRoute1);
            assertEquals(expectedRoutes2, actualRoute2);
            assertEquals(expectedRoutes3, actualRoute3);
        });
    }

    @Test
    @DisplayName("BLACK Pawn의 초기 위치가 아닌 위치에서 목적지 전까지의 경로인 Route를 반환한다.")
    void findDirectRoute_ReturnPossibleRoute_WhenCurrentPositionIsGiven_AndColorIsBlack() {
        Role pawn = Pawn.from(Color.BLACK);
        Position initialPosition = Position.of(5, 6);
        Position destination1 = Position.of(5, 5);
        Position destination2 = Position.of(6, 5);
        Position destination3 = Position.of(4, 5);

        Route actualRoute1 = pawn.findDirectRoute(initialPosition, destination1);
        Route actualRoute2 = pawn.findDirectRoute(initialPosition, destination2);
        Route actualRoute3 = pawn.findDirectRoute(initialPosition, destination3);

        Route expectedRoutes1 = new Route(S, List.of(Position.of(5, 5)));
        Route expectedRoutes2 = new Route(SE, List.of(Position.of(6, 5)));
        Route expectedRoutes3 = new Route(SW, List.of(Position.of(4, 5)));

        assertAll(() -> {
            assertEquals(expectedRoutes1, actualRoute1);
            assertEquals(expectedRoutes2, actualRoute2);
            assertEquals(expectedRoutes3, actualRoute3);
        });
    }

    @Test
    @DisplayName("Pawn이 상대방 기물이 없는 대각선으로 이동할 때, 예외를 던진다.")
    void traversalRoles_ShouldThrowException_WhenMoveToEmptyDiagonalPosition() {
        // Given
        Role pawn = Pawn.from(Color.WHITE);
        Position initialPosition = Position.of(5, 3);
        Position destination = Position.of(6, 4);

        // When
        pawn.findDirectRoute(initialPosition, destination);
        List<Role> roles = List.of();
        Role destinationRole = new Square();

        assertThrows(IllegalArgumentException.class, () -> {
            pawn.traversalRoles(roles, destinationRole);
        });
    }

    @Test
    @DisplayName("Pawn은 상대방 기물을 잡을 때 대각선으로 1칸 이동이 가능하다.")
    void traversalRoles_ShouldNotThrowException_WhenMoveToCounterDiagonalPosition() {
        // Given
        Role pawn = Pawn.from(Color.WHITE);
        Position initialPosition = Position.of(5, 3);
        Position destination = Position.of(6, 4);

        // When
        pawn.findDirectRoute(initialPosition, destination);
        List<Role> roles = List.of();
        Role destinationRole = Pawn.from(Color.BLACK);

        assertDoesNotThrow(() -> {
            pawn.traversalRoles(roles, destinationRole);
        });
    }

    @Test
    @DisplayName("Pawn은 상대방 기물을 잡을 때 대각선으로 1칸 이동이 가능하다.")
    void traversalRoles_ShouldThrowException_WhenMoveToSameColorDiagonalPosition() {
        // Given
        Role pawn = Pawn.from(Color.WHITE);
        Position initialPosition = Position.of(5, 3);
        Position destination = Position.of(6, 4);

        // When
        pawn.findDirectRoute(initialPosition, destination);
        List<Role> roles = List.of();
        Role destinationRole = Pawn.from(Color.WHITE);

        assertThrows(IllegalArgumentException.class, () -> {
            pawn.traversalRoles(roles, destinationRole);
        });
    }

    @Test
    @DisplayName("Pawn은 경로에 기물이 없을 때에만 전진이 가능하다 - 1칸 전진.")
    void traversalRoles_ShouldNotThrowException_WhenMoveForwardToSquarePosition() {
        // Given
        Role pawn = Pawn.from(Color.WHITE);
        Position initialPosition = Position.of(5, 3);
        Position destination = Position.of(5, 4);

        // When
        pawn.findDirectRoute(initialPosition, destination);
        List<Role> roles = List.of();
        Role destinationRole = new Square();

        assertDoesNotThrow(() -> {
            pawn.traversalRoles(roles, destinationRole);
        });
    }

    @Test
    @DisplayName("Pawn은 경로에 기물이 없을 때에만 전진이 가능하다 - 2칸 전진.")
    void traversalRoles_ShouldNotThrowException_WhenForwardTwiceToSquarePosition() {
        // Given
        Role pawn = Pawn.from(Color.WHITE);
        Position initialPosition = Position.of(5, 2);
        Position destination = Position.of(5, 4);

        // When
        pawn.findDirectRoute(initialPosition, destination);
        List<Role> roles = List.of(new Square());
        Role destinationRole = new Square();

        assertDoesNotThrow(() -> {
            pawn.traversalRoles(roles, destinationRole);
        });
    }

    @Test
    @DisplayName("Pawn은 경로에 모든 색깔의 기물이 있을 때 전진 시도하면 예외를 던진다.")
    void traversalRoles_ShouldNThrowException_WhenForwardTwiceToOccupiedPosition() {
        // Given
        Role pawn = Pawn.from(Color.WHITE);
        Position initialPosition = Position.of(5, 2);
        Position destination = Position.of(5, 3);

        // When
        pawn.findDirectRoute(initialPosition, destination);
        List<Role> roles = List.of();
        Role blackRoleIndestination = Pawn.from(Color.BLACK);
        Role whiteRoleIndestination = Pawn.from(Color.WHITE);

        assertAll(() -> {
            assertThrows(IllegalArgumentException.class, () -> {
                pawn.traversalRoles(roles, blackRoleIndestination);
            });
            assertThrows(IllegalArgumentException.class, () -> {
                pawn.traversalRoles(roles, whiteRoleIndestination);
            });
        });
    }
}
