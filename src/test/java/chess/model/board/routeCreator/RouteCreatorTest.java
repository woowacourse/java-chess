package chess.model.board.routeCreator;

import chess.model.board.Coordinate;
import chess.model.board.Route;
import chess.model.board.vector.Vector;
import chess.model.routeCreator.RouteCreator;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class RouteCreatorTest {
    @Test
    void 폰과_나이트외의_말의_이동경로_생성_확인() {
        Coordinate sourceCoordinateX = Coordinate.valueOf(1);
        Coordinate sourceCoordinateY = Coordinate.valueOf(1);
        Coordinate targetCoordinateX = Coordinate.valueOf(1);
        Coordinate targetCoordinateY = Coordinate.valueOf(3);
        Vector vector = new Vector(Arrays.asList(sourceCoordinateX, sourceCoordinateY, targetCoordinateX, targetCoordinateY));

        Route route = RouteCreator.createByNormalPiece(Arrays.asList(
                sourceCoordinateX,
                sourceCoordinateY,
                targetCoordinateX,
                targetCoordinateY),
                vector);
        assertThat(route).isEqualTo(new Route(Arrays.asList("12", "13")));
    }

    @Test
    void 폰과_나이트외의_말의_이동경로_생성오류_확인() {
        Coordinate sourceCoordinateX = Coordinate.valueOf(1);
        Coordinate sourceCoordinateY = Coordinate.valueOf(1);
        Coordinate targetCoordinateX = Coordinate.valueOf(1);
        Coordinate targetCoordinateY = Coordinate.valueOf(3);
        Vector vector = new Vector(Arrays.asList(sourceCoordinateX, sourceCoordinateY, targetCoordinateX, targetCoordinateY));

        Route route = RouteCreator.createByNormalPiece(Arrays.asList(
                sourceCoordinateX,
                sourceCoordinateY,
                targetCoordinateX,
                targetCoordinateY),
                vector);
        assertThat(route).isNotEqualTo(new Route(Arrays.asList("12", "21")));
    }

    @Test
    void 폰의_이동경로_생성_확인() {
        Coordinate sourceCoordinateX = Coordinate.valueOf(1);
        Coordinate sourceCoordinateY = Coordinate.valueOf(1);
        Coordinate targetCoordinateX = Coordinate.valueOf(1);
        Coordinate targetCoordinateY = Coordinate.valueOf(3);
        Vector vector = new Vector(Arrays.asList(sourceCoordinateX, sourceCoordinateY, targetCoordinateX, targetCoordinateY));

        Route route = RouteCreator.createByPawn(Arrays.asList(
                sourceCoordinateX,
                sourceCoordinateY,
                targetCoordinateX,
                targetCoordinateY),
                vector,
                true);
        assertThat(route).isEqualTo(new Route(Arrays.asList("12", "13")));
    }

    @Test
    void 폰의_이동경로_생성오류_확인_폰이_움직인적_있고_2칸_움직이려고_할_경우() {
        Coordinate sourceCoordinateX = Coordinate.valueOf(1);
        Coordinate sourceCoordinateY = Coordinate.valueOf(1);
        Coordinate targetCoordinateX = Coordinate.valueOf(1);
        Coordinate targetCoordinateY = Coordinate.valueOf(3);
        Vector vector = new Vector(Arrays.asList(sourceCoordinateX, sourceCoordinateY, targetCoordinateX, targetCoordinateY));

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> RouteCreator.createByPawn(Arrays.asList(
                        sourceCoordinateX,
                        sourceCoordinateY,
                        targetCoordinateX,
                        targetCoordinateY),
                        vector,
                        false));
    }

    @Test
    void 나이트의_이동경로_생성_확인() {
        Coordinate sourceCoordinateX = Coordinate.valueOf(1);
        Coordinate sourceCoordinateY = Coordinate.valueOf(1);
        Coordinate targetCoordinateX = Coordinate.valueOf(2);
        Coordinate targetCoordinateY = Coordinate.valueOf(3);
        Vector vector = new Vector(Arrays.asList(sourceCoordinateX, sourceCoordinateY, targetCoordinateX, targetCoordinateY));

        Route route = RouteCreator.createByKnight(Arrays.asList(
                sourceCoordinateX,
                sourceCoordinateY,
                targetCoordinateX,
                targetCoordinateY),
                vector);
        assertThat(route).isEqualTo(new Route(Arrays.asList("23")));
    }

    @Test
    void 나이트의_이동경로_생성오류_확인() {
        Coordinate sourceCoordinateX = Coordinate.valueOf(1);
        Coordinate sourceCoordinateY = Coordinate.valueOf(1);
        Coordinate targetCoordinateX = Coordinate.valueOf(2);
        Coordinate targetCoordinateY = Coordinate.valueOf(3);
        Vector vector = new Vector(Arrays.asList(sourceCoordinateX, sourceCoordinateY, targetCoordinateX, targetCoordinateY));

        Route route = RouteCreator.createByKnight(Arrays.asList(
                sourceCoordinateX,
                sourceCoordinateY,
                targetCoordinateX,
                targetCoordinateY),
                vector);
        assertThat(route).isNotEqualTo(new Route(Arrays.asList("24")));
    }
}
