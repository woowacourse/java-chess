package chess.model.piece;

import chess.model.board.Coordinate;
import chess.model.vector.Vector;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class KnightTest {
    private Piece knight;

    @BeforeEach
    void setUp() {
        knight = new Knight("white");
    }

    @Test
    void 잘못된_팀을_입력할_경우_확인() {
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> new Bishop("purple"));
    }

    @Test
    void 경로확인오류_coordinates가_null일_경우() {
        Coordinate sourceCoordinateX = Coordinate.valueOf(5);
        Coordinate sourceCoordinateY = Coordinate.valueOf(5);
        Coordinate targetCoordinateX = Coordinate.valueOf(5);
        Coordinate targetCoordinateY = Coordinate.valueOf(6);
        Vector vector = new Vector(Arrays.asList(sourceCoordinateX, sourceCoordinateY, targetCoordinateX, targetCoordinateY));

        assertThatExceptionOfType(NullPointerException.class)
                .isThrownBy(() -> knight.produceRoute(null, vector));
    }

    @Test
    void 경로확인오류_coordinates가_비어있을_경우() {
        Coordinate sourceCoordinateX = Coordinate.valueOf(5);
        Coordinate sourceCoordinateY = Coordinate.valueOf(5);
        Coordinate targetCoordinateX = Coordinate.valueOf(5);
        Coordinate targetCoordinateY = Coordinate.valueOf(6);
        Vector vector = new Vector(Arrays.asList(sourceCoordinateX, sourceCoordinateY, targetCoordinateX, targetCoordinateY));

        assertThatExceptionOfType(NullPointerException.class)
                .isThrownBy(() -> knight.produceRoute(Collections.emptyList(), vector));
    }

    @Test
    void 경로확인오류_vector가_null인_경우() {
        Coordinate sourceCoordinateX = Coordinate.valueOf(5);
        Coordinate sourceCoordinateY = Coordinate.valueOf(5);

        assertThatExceptionOfType(NullPointerException.class)
                .isThrownBy(() -> knight.produceRoute(Arrays.asList(sourceCoordinateX, sourceCoordinateY), null));
    }

    @Test
    void 경로확인_남동방향() {
        Coordinate sourceCoordinateX = Coordinate.valueOf(5);
        Coordinate sourceCoordinateY = Coordinate.valueOf(5);
        Coordinate targetCoordinateX = Coordinate.valueOf(6);
        Coordinate targetCoordinateY = Coordinate.valueOf(3);

        Vector vector = new Vector(Arrays.asList(sourceCoordinateX, sourceCoordinateY, targetCoordinateX, targetCoordinateY));

        Route route = knight.produceRoute(Arrays.asList(sourceCoordinateX, sourceCoordinateY), vector);
        assertThat(route).isEqualTo(new Route(Arrays.asList("63")));
    }

    @Test
    void 경로확인_남서방향() {
        Coordinate sourceCoordinateX = Coordinate.valueOf(5);
        Coordinate sourceCoordinateY = Coordinate.valueOf(5);
        Coordinate targetCoordinateX = Coordinate.valueOf(4);
        Coordinate targetCoordinateY = Coordinate.valueOf(3);

        Vector vector = new Vector(Arrays.asList(sourceCoordinateX, sourceCoordinateY, targetCoordinateX, targetCoordinateY));

        Route route = knight.produceRoute(Arrays.asList(sourceCoordinateX, sourceCoordinateY), vector);
        assertThat(route).isEqualTo(new Route(Arrays.asList("43")));
    }

    @Test
    void 경로확인_북동방향() {
        Coordinate sourceCoordinateX = Coordinate.valueOf(5);
        Coordinate sourceCoordinateY = Coordinate.valueOf(5);
        Coordinate targetCoordinateX = Coordinate.valueOf(6);
        Coordinate targetCoordinateY = Coordinate.valueOf(7);

        Vector vector = new Vector(Arrays.asList(sourceCoordinateX, sourceCoordinateY, targetCoordinateX, targetCoordinateY));

        Route route = knight.produceRoute(Arrays.asList(sourceCoordinateX, sourceCoordinateY), vector);
        assertThat(route).isEqualTo(new Route(Arrays.asList("67")));
    }

    @Test
    void 경로확인_북서방향() {
        Coordinate sourceCoordinateX = Coordinate.valueOf(5);
        Coordinate sourceCoordinateY = Coordinate.valueOf(5);
        Coordinate targetCoordinateX = Coordinate.valueOf(4);
        Coordinate targetCoordinateY = Coordinate.valueOf(7);

        Vector vector = new Vector(Arrays.asList(sourceCoordinateX, sourceCoordinateY, targetCoordinateX, targetCoordinateY));

        Route route = knight.produceRoute(Arrays.asList(sourceCoordinateX, sourceCoordinateY), vector);
        assertThat(route).isEqualTo(new Route(Arrays.asList("47")));
    }

    @Test
    void 경로확인_서북방향() {
        Coordinate sourceCoordinateX = Coordinate.valueOf(5);
        Coordinate sourceCoordinateY = Coordinate.valueOf(5);
        Coordinate targetCoordinateX = Coordinate.valueOf(3);
        Coordinate targetCoordinateY = Coordinate.valueOf(6);

        Vector vector = new Vector(Arrays.asList(sourceCoordinateX, sourceCoordinateY, targetCoordinateX, targetCoordinateY));

        Route route = knight.produceRoute(Arrays.asList(sourceCoordinateX, sourceCoordinateY), vector);
        assertThat(route).isEqualTo(new Route(Arrays.asList("36")));
    }

    @Test
    void 경로확인_서남방향() {
        Coordinate sourceCoordinateX = Coordinate.valueOf(5);
        Coordinate sourceCoordinateY = Coordinate.valueOf(5);
        Coordinate targetCoordinateX = Coordinate.valueOf(3);
        Coordinate targetCoordinateY = Coordinate.valueOf(4);

        Vector vector = new Vector(Arrays.asList(sourceCoordinateX, sourceCoordinateY, targetCoordinateX, targetCoordinateY));

        Route route = knight.produceRoute(Arrays.asList(sourceCoordinateX, sourceCoordinateY), vector);
        assertThat(route).isEqualTo(new Route(Arrays.asList("34")));
    }

    @Test
    void 경로확인_동북방향() {
        Coordinate sourceCoordinateX = Coordinate.valueOf(5);
        Coordinate sourceCoordinateY = Coordinate.valueOf(5);
        Coordinate targetCoordinateX = Coordinate.valueOf(7);
        Coordinate targetCoordinateY = Coordinate.valueOf(6);

        Vector vector = new Vector(Arrays.asList(sourceCoordinateX, sourceCoordinateY, targetCoordinateX, targetCoordinateY));

        Route route = knight.produceRoute(Arrays.asList(sourceCoordinateX, sourceCoordinateY), vector);
        assertThat(route).isEqualTo(new Route(Arrays.asList("76")));
    }

    @Test
    void 경로확인_동남방향() {
        Coordinate sourceCoordinateX = Coordinate.valueOf(5);
        Coordinate sourceCoordinateY = Coordinate.valueOf(5);
        Coordinate targetCoordinateX = Coordinate.valueOf(7);
        Coordinate targetCoordinateY = Coordinate.valueOf(4);

        Vector vector = new Vector(Arrays.asList(sourceCoordinateX, sourceCoordinateY, targetCoordinateX, targetCoordinateY));

        Route route = knight.produceRoute(Arrays.asList(sourceCoordinateX, sourceCoordinateY), vector);
        assertThat(route).isEqualTo(new Route(Arrays.asList("74")));
    }

    @Test
    void 경로오류확인_북방향() {
        Coordinate sourceCoordinateX = Coordinate.valueOf(5);
        Coordinate sourceCoordinateY = Coordinate.valueOf(5);
        Coordinate targetCoordinateX = Coordinate.valueOf(8);
        Coordinate targetCoordinateY = Coordinate.valueOf(6);

        Vector vector = new Vector(Arrays.asList(sourceCoordinateX, sourceCoordinateY, targetCoordinateX, targetCoordinateY));

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> knight.produceRoute(Arrays.asList(sourceCoordinateX, sourceCoordinateY), vector));
    }

    @Test
    void 경로오류확인_정의하지_않은_방향() {
        Coordinate sourceCoordinateX = Coordinate.valueOf(5);
        Coordinate sourceCoordinateY = Coordinate.valueOf(5);
        Coordinate targetCoordinateX = Coordinate.valueOf(6);
        Coordinate targetCoordinateY = Coordinate.valueOf(8);

        Vector vector = new Vector(Arrays.asList(sourceCoordinateX, sourceCoordinateY, targetCoordinateX, targetCoordinateY));

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> knight.produceRoute(Arrays.asList(sourceCoordinateX, sourceCoordinateY), vector));
    }

    @Test
    void 팀_확인_white팀일_경우() {
        assertThat(knight.askTeamColor()).isEqualTo("white");
    }

    @Test
    void 팀_확인_black팀일_경우() {
        Piece blackKnight = new Knight("black");
        assertThat(blackKnight.askTeamColor()).isEqualTo("black");
    }

    @Test
    void pawn인지_확인() {
        assertThat(knight.isPawn()).isFalse();
    }
}
