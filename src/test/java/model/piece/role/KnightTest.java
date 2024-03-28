package model.piece.role;

import static model.direction.Direction.EEN;
import static model.direction.Direction.EES;
import static model.direction.Direction.NNE;
import static model.direction.Direction.NNW;
import static model.direction.Direction.SSE;
import static model.direction.Direction.SSW;
import static model.direction.Direction.WWN;
import static model.direction.Direction.WWS;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import model.piece.Color;
import model.position.Position;
import model.position.Route;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

class KnightTest {

    @ParameterizedTest
    @EnumSource(Color.class)
    @DisplayName("Knight의 현재 위치에서 목적지 전까지의 경로인 Route를 반환한다.")
    void findDirectRoute_ReturnsPossiblePositions_WhenCurrentPositionIsGiven(Color color) {
        Role knight = Knight.from(color);
        Position initialPosition = Position.of(4, 4);

        Position dest1 = Position.of(5, 6);
        Position dest2 = Position.of(3, 6);
        Position dest3 = Position.of(5, 2);
        Position dest4 = Position.of(3, 2);
        Position dest5 = Position.of(6, 5);
        Position dest6 = Position.of(6, 3);
        Position dest7 = Position.of(2, 5);
        Position dest8 = Position.of(2, 3);

        Route actualRoute1 = knight.findDirectRoute(initialPosition, dest1);
        Route actualRoute2 = knight.findDirectRoute(initialPosition, dest2);
        Route actualRoute3 = knight.findDirectRoute(initialPosition, dest3);
        Route actualRoute4 = knight.findDirectRoute(initialPosition, dest4);
        Route actualRoute5 = knight.findDirectRoute(initialPosition, dest5);
        Route actualRoute6 = knight.findDirectRoute(initialPosition, dest6);
        Route actualRoute7 = knight.findDirectRoute(initialPosition, dest7);
        Route actualRoute8 = knight.findDirectRoute(initialPosition, dest8);

        Route expectedRoute1 = new Route(NNE, List.of(Position.of(5, 6)));
        Route expectedRoute2 = new Route(NNW, List.of(Position.of(3, 6)));
        Route expectedRoute3 = new Route(SSE, List.of(Position.of(5, 2)));
        Route expectedRoute4 = new Route(SSW, List.of(Position.of(3, 2)));
        Route expectedRoute5 = new Route(EEN, List.of(Position.of(6, 5)));
        Route expectedRoute6 = new Route(EES, List.of(Position.of(6, 3)));
        Route expectedRoute7 = new Route(WWN, List.of(Position.of(2, 5)));
        Route expectedRoute8 = new Route(WWS, List.of(Position.of(2, 3)));

        assertAll( () -> {
            assertEquals(expectedRoute1, actualRoute1);
            assertEquals(expectedRoute2, actualRoute2);
            assertEquals(expectedRoute3, actualRoute3);
            assertEquals(expectedRoute4, actualRoute4);
            assertEquals(expectedRoute5, actualRoute5);
            assertEquals(expectedRoute6, actualRoute6);
            assertEquals(expectedRoute7, actualRoute7);
            assertEquals(expectedRoute8, actualRoute8);
        });
    }
}
