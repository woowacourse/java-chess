package chess.model.piece;

import chess.model.Coordinate;
import chess.model.Route;
import chess.model.Vector;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.assertj.core.api.Assertions.*;


public class RookTest {
    private Piece rook;

    @BeforeEach
    void setUp() {
        rook = new Rook("white");
    }

    @Test
    void 잘못된_팀을_입력할_경우_확인() {
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> new Queen("purple"));
    }

    @Test
    void 경로확인오류_coordinates가_null일_경우() {
        Coordinate sourceCoordinateX = Coordinate.valueOf(5);
        Coordinate sourceCoordinateY = Coordinate.valueOf(5);
        Coordinate targetCoordinateX = Coordinate.valueOf(5);
        Coordinate targetCoordinateY = Coordinate.valueOf(6);
        Vector vector = new Vector(Arrays.asList(sourceCoordinateX, sourceCoordinateY, targetCoordinateX, targetCoordinateY));

        assertThatExceptionOfType(NullPointerException.class)
                .isThrownBy(() -> rook.produceRoute(null, vector));
    }

    @Test
    void 경로확인오류_coordinates가_비어있을_경우() {
        Coordinate sourceCoordinateX = Coordinate.valueOf(5);
        Coordinate sourceCoordinateY = Coordinate.valueOf(5);
        Coordinate targetCoordinateX = Coordinate.valueOf(5);
        Coordinate targetCoordinateY = Coordinate.valueOf(6);
        Vector vector = new Vector(Arrays.asList(sourceCoordinateX, sourceCoordinateY, targetCoordinateX, targetCoordinateY));

        assertThatExceptionOfType(NullPointerException.class)
                .isThrownBy(() -> rook.produceRoute(Collections.emptyList(), vector));
    }

    @Test
    void 경로확인오류_vector가_null인_경우() {
        Coordinate sourceCoordinateX = Coordinate.valueOf(5);
        Coordinate sourceCoordinateY = Coordinate.valueOf(5);

        assertThatExceptionOfType(NullPointerException.class)
                .isThrownBy(() -> rook.produceRoute(Arrays.asList(sourceCoordinateX, sourceCoordinateY), null));
    }

    @Test
    void 경로확인_북방향() {
        Coordinate sourceCoordinateX = Coordinate.valueOf(5);
        Coordinate sourceCoordinateY = Coordinate.valueOf(1);
        Coordinate targetCoordinateX = Coordinate.valueOf(5);
        Coordinate targetCoordinateY = Coordinate.valueOf(7);

        Vector vector = new Vector(Arrays.asList(sourceCoordinateX, sourceCoordinateY, targetCoordinateX, targetCoordinateY));

        Route route = rook.produceRoute(Arrays.asList(sourceCoordinateX, sourceCoordinateY), vector);
        assertThat(route).isEqualTo(new Route(Arrays.asList("52", "53", "54", "55", "56", "57")));
    }

    @Test
    void 경로확인_남방향() {
        Coordinate sourceCoordinateX = Coordinate.valueOf(5);
        Coordinate sourceCoordinateY = Coordinate.valueOf(5);
        Coordinate targetCoordinateX = Coordinate.valueOf(5);
        Coordinate targetCoordinateY = Coordinate.valueOf(1);

        Vector vector = new Vector(Arrays.asList(sourceCoordinateX, sourceCoordinateY, targetCoordinateX, targetCoordinateY));

        Route route = rook.produceRoute(Arrays.asList(sourceCoordinateX, sourceCoordinateY), vector);
        assertThat(route).isEqualTo(new Route(Arrays.asList("54", "53", "52", "51")));
    }

    @Test
    void 경로확인_서방향() {
        Coordinate sourceCoordinateX = Coordinate.valueOf(5);
        Coordinate sourceCoordinateY = Coordinate.valueOf(5);
        Coordinate targetCoordinateX = Coordinate.valueOf(1);
        Coordinate targetCoordinateY = Coordinate.valueOf(5);

        Vector vector = new Vector(Arrays.asList(sourceCoordinateX, sourceCoordinateY, targetCoordinateX, targetCoordinateY));

        Route route = rook.produceRoute(Arrays.asList(sourceCoordinateX, sourceCoordinateY), vector);
        assertThat(route).isEqualTo(new Route(Arrays.asList("45", "35", "25", "15")));
    }

    @Test
    void 경로확인_동방향() {
        Coordinate sourceCoordinateX = Coordinate.valueOf(5);
        Coordinate sourceCoordinateY = Coordinate.valueOf(5);
        Coordinate targetCoordinateX = Coordinate.valueOf(8);
        Coordinate targetCoordinateY = Coordinate.valueOf(5);

        Vector vector = new Vector(Arrays.asList(sourceCoordinateX, sourceCoordinateY, targetCoordinateX, targetCoordinateY));

        Route route = rook.produceRoute(Arrays.asList(sourceCoordinateX, sourceCoordinateY), vector);
        assertThat(route).isEqualTo(new Route(Arrays.asList("65", "75", "85")));
    }

    @Test
    void 경로오류_확인_룩이_같은_자리로_이동한_경우() {
        Coordinate sourceCoordinateX = Coordinate.valueOf(5);
        Coordinate sourceCoordinateY = Coordinate.valueOf(5);
        Coordinate targetCoordinateX = Coordinate.valueOf(5);
        Coordinate targetCoordinateY = Coordinate.valueOf(5);

        Vector vector = new Vector(Arrays.asList(sourceCoordinateX, sourceCoordinateY, targetCoordinateX, targetCoordinateY));

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> rook.produceRoute(Arrays.asList(sourceCoordinateX, sourceCoordinateY), vector));
    }

    @Test
    void 경로오류_확인_룩의_이동방향이_나이트의_이동방향인_경우() {
        Coordinate sourceCoordinateX = Coordinate.valueOf(5);
        Coordinate sourceCoordinateY = Coordinate.valueOf(5);
        Coordinate targetCoordinateX = Coordinate.valueOf(6);
        Coordinate targetCoordinateY = Coordinate.valueOf(7);

        Vector vector = new Vector(Arrays.asList(sourceCoordinateX, sourceCoordinateY, targetCoordinateX, targetCoordinateY));

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> rook.produceRoute(Arrays.asList(sourceCoordinateX, sourceCoordinateY), vector));
    }

    @Test
    void 경로오류_확인_룩의_이동방향이_정의된_방향이_아닐_경우() {
        Coordinate sourceCoordinateX = Coordinate.valueOf(5);
        Coordinate sourceCoordinateY = Coordinate.valueOf(5);
        Coordinate targetCoordinateX = Coordinate.valueOf(6);
        Coordinate targetCoordinateY = Coordinate.valueOf(8);

        Vector vector = new Vector(Arrays.asList(sourceCoordinateX, sourceCoordinateY, targetCoordinateX, targetCoordinateY));

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> rook.produceRoute(Arrays.asList(sourceCoordinateX, sourceCoordinateY), vector));
    }

    @Test
    void 팀_확인_white팀일_경우() {
        assertThat(rook.askTeamColor()).isEqualTo("white");
    }

    @Test
    void 팀_확인_black팀일_경우() {
        Piece blackRook = new Rook("black");
        assertThat(blackRook.askTeamColor()).isEqualTo("black");
    }

    @Test
    void pawn인지_확인() {
        assertThat(rook.isPawn()).isFalse();
    }
}

