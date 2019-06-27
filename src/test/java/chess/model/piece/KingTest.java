package chess.model.piece;

import chess.model.board.Coordinate;
import chess.model.board.Route;
import chess.model.board.vector.Vector;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static chess.model.board.Board.*;

public class KingTest {
    private Piece king;

    @BeforeEach
    void setUp() {
        king = new King(WHITE_TEAM);
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
                .isThrownBy(() -> king.produceRoute(null, vector));
    }

    @Test
    void 경로확인오류_coordinates가_비어있을_경우() {
        Coordinate sourceCoordinateX = Coordinate.valueOf(5);
        Coordinate sourceCoordinateY = Coordinate.valueOf(5);
        Coordinate targetCoordinateX = Coordinate.valueOf(5);
        Coordinate targetCoordinateY = Coordinate.valueOf(6);
        Vector vector = new Vector(Arrays.asList(sourceCoordinateX, sourceCoordinateY, targetCoordinateX, targetCoordinateY));

        assertThatExceptionOfType(NullPointerException.class)
                .isThrownBy(() -> king.produceRoute(Collections.emptyList(), vector));
    }

    @Test
    void 경로확인오류_vector가_null인_경우() {
        Coordinate sourceCoordinateX = Coordinate.valueOf(5);
        Coordinate sourceCoordinateY = Coordinate.valueOf(5);

        assertThatExceptionOfType(NullPointerException.class)
                .isThrownBy(() -> king.produceRoute(Arrays.asList(sourceCoordinateX, sourceCoordinateY), null));
    }

    @Test
    void 경로확인_북방향() {
        Coordinate sourceCoordinateX = Coordinate.valueOf(5);
        Coordinate sourceCoordinateY = Coordinate.valueOf(5);
        Coordinate targetCoordinateX = Coordinate.valueOf(5);
        Coordinate targetCoordinateY = Coordinate.valueOf(6);

        Vector vector = new Vector(Arrays.asList(sourceCoordinateX, sourceCoordinateY, targetCoordinateX, targetCoordinateY));

        Route route = king.produceRoute(Arrays.asList(sourceCoordinateX, sourceCoordinateY), vector);
        assertThat(route).isEqualTo(new Route(Arrays.asList("56")));
    }

    @Test
    void 경로확인_남방향() {
        Coordinate sourceCoordinateX = Coordinate.valueOf(5);
        Coordinate sourceCoordinateY = Coordinate.valueOf(5);
        Coordinate targetCoordinateX = Coordinate.valueOf(5);
        Coordinate targetCoordinateY = Coordinate.valueOf(4);

        Vector vector = new Vector(Arrays.asList(sourceCoordinateX, sourceCoordinateY, targetCoordinateX, targetCoordinateY));

        Route route = king.produceRoute(Arrays.asList(sourceCoordinateX, sourceCoordinateY), vector);
        assertThat(route).isEqualTo(new Route(Arrays.asList("54")));
    }

    @Test
    void 경로확인_서방향() {
        Coordinate sourceCoordinateX = Coordinate.valueOf(5);
        Coordinate sourceCoordinateY = Coordinate.valueOf(5);
        Coordinate targetCoordinateX = Coordinate.valueOf(4);
        Coordinate targetCoordinateY = Coordinate.valueOf(5);

        Vector vector = new Vector(Arrays.asList(sourceCoordinateX, sourceCoordinateY, targetCoordinateX, targetCoordinateY));

        Route route = king.produceRoute(Arrays.asList(sourceCoordinateX, sourceCoordinateY), vector);
        assertThat(route).isEqualTo(new Route(Arrays.asList("45")));
    }

    @Test
    void 경로확인_동방향() {
        Coordinate sourceCoordinateX = Coordinate.valueOf(5);
        Coordinate sourceCoordinateY = Coordinate.valueOf(5);
        Coordinate targetCoordinateX = Coordinate.valueOf(6);
        Coordinate targetCoordinateY = Coordinate.valueOf(5);

        Vector vector = new Vector(Arrays.asList(sourceCoordinateX, sourceCoordinateY, targetCoordinateX, targetCoordinateY));

        Route route = king.produceRoute(Arrays.asList(sourceCoordinateX, sourceCoordinateY), vector);
        assertThat(route).isEqualTo(new Route(Arrays.asList("65")));
    }

    @Test
    void 경로확인_남동방향() {
        Coordinate sourceCoordinateX = Coordinate.valueOf(5);
        Coordinate sourceCoordinateY = Coordinate.valueOf(5);
        Coordinate targetCoordinateX = Coordinate.valueOf(6);
        Coordinate targetCoordinateY = Coordinate.valueOf(4);

        Vector vector = new Vector(Arrays.asList(sourceCoordinateX, sourceCoordinateY, targetCoordinateX, targetCoordinateY));

        Route route = king.produceRoute(Arrays.asList(sourceCoordinateX, sourceCoordinateY), vector);
        assertThat(route).isEqualTo(new Route(Arrays.asList("64")));
    }

    @Test
    void 경로확인_남서방향() {
        Coordinate sourceCoordinateX = Coordinate.valueOf(5);
        Coordinate sourceCoordinateY = Coordinate.valueOf(5);
        Coordinate targetCoordinateX = Coordinate.valueOf(4);
        Coordinate targetCoordinateY = Coordinate.valueOf(4);

        Vector vector = new Vector(Arrays.asList(sourceCoordinateX, sourceCoordinateY, targetCoordinateX, targetCoordinateY));

        Route route = king.produceRoute(Arrays.asList(sourceCoordinateX, sourceCoordinateY), vector);
        assertThat(route).isEqualTo(new Route(Arrays.asList("44")));
    }

    @Test
    void 경로확인_북동방향() {
        Coordinate sourceCoordinateX = Coordinate.valueOf(5);
        Coordinate sourceCoordinateY = Coordinate.valueOf(5);
        Coordinate targetCoordinateX = Coordinate.valueOf(6);
        Coordinate targetCoordinateY = Coordinate.valueOf(6);

        Vector vector = new Vector(Arrays.asList(sourceCoordinateX, sourceCoordinateY, targetCoordinateX, targetCoordinateY));

        Route route = king.produceRoute(Arrays.asList(sourceCoordinateX, sourceCoordinateY), vector);
        assertThat(route).isEqualTo(new Route(Arrays.asList("66")));
    }

    @Test
    void 경로확인_북서방향() {
        Coordinate sourceCoordinateX = Coordinate.valueOf(5);
        Coordinate sourceCoordinateY = Coordinate.valueOf(5);
        Coordinate targetCoordinateX = Coordinate.valueOf(4);
        Coordinate targetCoordinateY = Coordinate.valueOf(6);

        Vector vector = new Vector(Arrays.asList(sourceCoordinateX, sourceCoordinateY, targetCoordinateX, targetCoordinateY));

        Route route = king.produceRoute(Arrays.asList(sourceCoordinateX, sourceCoordinateY), vector);
        assertThat(route).isEqualTo(new Route(Arrays.asList("46")));
    }

    @Test
    void 경로오류_확인_킹이_같은_자리로_이동한_경우() {
        Coordinate sourceCoordinateX = Coordinate.valueOf(5);
        Coordinate sourceCoordinateY = Coordinate.valueOf(5);
        Coordinate targetCoordinateX = Coordinate.valueOf(5);
        Coordinate targetCoordinateY = Coordinate.valueOf(5);

        Vector vector = new Vector(Arrays.asList(sourceCoordinateX, sourceCoordinateY, targetCoordinateX, targetCoordinateY));

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> king.produceRoute(Arrays.asList(sourceCoordinateX, sourceCoordinateY), vector));
    }

    @Test
    void 경로오류_확인_킹의_이동방향이_나이트의_이동방향인_경우() {
        Coordinate sourceCoordinateX = Coordinate.valueOf(5);
        Coordinate sourceCoordinateY = Coordinate.valueOf(5);
        Coordinate targetCoordinateX = Coordinate.valueOf(6);
        Coordinate targetCoordinateY = Coordinate.valueOf(7);

        Vector vector = new Vector(Arrays.asList(sourceCoordinateX, sourceCoordinateY, targetCoordinateX, targetCoordinateY));

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> king.produceRoute(Arrays.asList(sourceCoordinateX, sourceCoordinateY), vector));
    }

    @Test
    void 경로오류_확인_킹의_이동방향이_정의된_방향이_아닐_경우() {
        Coordinate sourceCoordinateX = Coordinate.valueOf(5);
        Coordinate sourceCoordinateY = Coordinate.valueOf(5);
        Coordinate targetCoordinateX = Coordinate.valueOf(6);
        Coordinate targetCoordinateY = Coordinate.valueOf(8);

        Vector vector = new Vector(Arrays.asList(sourceCoordinateX, sourceCoordinateY, targetCoordinateX, targetCoordinateY));

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> king.produceRoute(Arrays.asList(sourceCoordinateX, sourceCoordinateY), vector));
    }

    @Test
    void 경로오류_확인_킹의_이동거리가_1칸을_초과할_경우() {
        Coordinate sourceCoordinateX = Coordinate.valueOf(5);
        Coordinate sourceCoordinateY = Coordinate.valueOf(5);
        Coordinate targetCoordinateX = Coordinate.valueOf(5);
        Coordinate targetCoordinateY = Coordinate.valueOf(7);

        Vector vector = new Vector(Arrays.asList(sourceCoordinateX, sourceCoordinateY, targetCoordinateX, targetCoordinateY));

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> king.produceRoute(Arrays.asList(sourceCoordinateX, sourceCoordinateY), vector));
    }

    @Test
    void 팀_확인_white팀일_경우() {
        assertThat(king.askTeamColor()).isEqualTo(WHITE_TEAM);
    }

    @Test
    void 팀_확인_black팀일_경우() {
        Piece blackKing = new King(BLACK_TEAM);
        assertThat(blackKing.askTeamColor()).isEqualTo(BLACK_TEAM);
    }

    @Test
    void pawn인지_확인() {
        assertThat(king.isPawn()).isFalse();
    }
}
