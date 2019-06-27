package chess.model.piece;

import chess.model.board.Coordinate;
import chess.model.vector.Vector;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class PawnTest {
    private Piece whitePawn;
    private Piece blackPawn;

    @BeforeEach
    void setUp() {
        whitePawn = new Pawn(true, "white");
        blackPawn = new Pawn(true, "black");
    }

    @Test
    void 잘못된_팀을_입력할_경우_확인() {
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> new Pawn(true, "purple"));
    }

    @Test
    void 경로확인오류_coordinates가_null일_경우() {
        Coordinate sourceCoordinateX = Coordinate.valueOf(5);
        Coordinate sourceCoordinateY = Coordinate.valueOf(5);
        Coordinate targetCoordinateX = Coordinate.valueOf(5);
        Coordinate targetCoordinateY = Coordinate.valueOf(6);
        Vector vector = new Vector(Arrays.asList(sourceCoordinateX, sourceCoordinateY, targetCoordinateX, targetCoordinateY));

        assertThatExceptionOfType(NullPointerException.class)
                .isThrownBy(() -> whitePawn.produceRoute(null, vector));
    }

    @Test
    void 경로확인오류_coordinates가_비어있을_경우() {
        Coordinate sourceCoordinateX = Coordinate.valueOf(5);
        Coordinate sourceCoordinateY = Coordinate.valueOf(5);
        Coordinate targetCoordinateX = Coordinate.valueOf(5);
        Coordinate targetCoordinateY = Coordinate.valueOf(6);
        Vector vector = new Vector(Arrays.asList(sourceCoordinateX, sourceCoordinateY, targetCoordinateX, targetCoordinateY));

        assertThatExceptionOfType(NullPointerException.class)
                .isThrownBy(() -> whitePawn.produceRoute(Collections.emptyList(), vector));
    }

    @Test
    void 경로확인오류_vector가_null인_경우() {
        Coordinate sourceCoordinateX = Coordinate.valueOf(5);
        Coordinate sourceCoordinateY = Coordinate.valueOf(5);

        assertThatExceptionOfType(NullPointerException.class)
                .isThrownBy(() -> whitePawn.produceRoute(Arrays.asList(sourceCoordinateX, sourceCoordinateY), null));
    }

    @Test
    void 경로확인_백팀_북방향_크기1() {
        Coordinate sourceCoordinateX = Coordinate.valueOf(5);
        Coordinate sourceCoordinateY = Coordinate.valueOf(5);
        Coordinate targetCoordinateX = Coordinate.valueOf(5);
        Coordinate targetCoordinateY = Coordinate.valueOf(6);

        Vector vector = new Vector(Arrays.asList(sourceCoordinateX, sourceCoordinateY, targetCoordinateX, targetCoordinateY));

        Route route = whitePawn.produceRoute(Arrays.asList(sourceCoordinateX, sourceCoordinateY), vector);
        assertThat(route).isEqualTo(new Route(Arrays.asList("56")));
    }

    @Test
    void 경로확인_백팀_북방향_크기2() {
        Coordinate sourceCoordinateX = Coordinate.valueOf(5);
        Coordinate sourceCoordinateY = Coordinate.valueOf(5);
        Coordinate targetCoordinateX = Coordinate.valueOf(5);
        Coordinate targetCoordinateY = Coordinate.valueOf(7);

        Vector vector = new Vector(Arrays.asList(sourceCoordinateX, sourceCoordinateY, targetCoordinateX, targetCoordinateY));

        Route route = whitePawn.produceRoute(Arrays.asList(sourceCoordinateX, sourceCoordinateY), vector);
        assertThat(route).isEqualTo(new Route(Arrays.asList("56", "57")));
    }

    @Test
    void 경로확인_백팀_북동방향_크기1() {
        Coordinate sourceCoordinateX = Coordinate.valueOf(5);
        Coordinate sourceCoordinateY = Coordinate.valueOf(5);
        Coordinate targetCoordinateX = Coordinate.valueOf(6);
        Coordinate targetCoordinateY = Coordinate.valueOf(6);

        Vector vector = new Vector(Arrays.asList(sourceCoordinateX, sourceCoordinateY, targetCoordinateX, targetCoordinateY));

        Route route = whitePawn.produceRoute(Arrays.asList(sourceCoordinateX, sourceCoordinateY), vector);
        assertThat(route).isEqualTo(new Route(Arrays.asList("66")));
    }

    @Test
    void 경로확인_백팀_북서방향_크기1() {
        Coordinate sourceCoordinateX = Coordinate.valueOf(5);
        Coordinate sourceCoordinateY = Coordinate.valueOf(5);
        Coordinate targetCoordinateX = Coordinate.valueOf(4);
        Coordinate targetCoordinateY = Coordinate.valueOf(6);

        Vector vector = new Vector(Arrays.asList(sourceCoordinateX, sourceCoordinateY, targetCoordinateX, targetCoordinateY));

        Route route = whitePawn.produceRoute(Arrays.asList(sourceCoordinateX, sourceCoordinateY), vector);
        assertThat(route).isEqualTo(new Route(Arrays.asList("46")));
    }

    @Test
    void 경로확인_흑팀_남방향_크기1() {
        Coordinate sourceCoordinateX = Coordinate.valueOf(5);
        Coordinate sourceCoordinateY = Coordinate.valueOf(5);
        Coordinate targetCoordinateX = Coordinate.valueOf(5);
        Coordinate targetCoordinateY = Coordinate.valueOf(4);

        Vector vector = new Vector(Arrays.asList(sourceCoordinateX, sourceCoordinateY, targetCoordinateX, targetCoordinateY));

        Route route = blackPawn.produceRoute(Arrays.asList(sourceCoordinateX, sourceCoordinateY), vector);
        assertThat(route).isEqualTo(new Route(Arrays.asList("54")));
    }

    @Test
    void 경로확인_흑팀_남방향_크기2() {
        Coordinate sourceCoordinateX = Coordinate.valueOf(5);
        Coordinate sourceCoordinateY = Coordinate.valueOf(5);
        Coordinate targetCoordinateX = Coordinate.valueOf(5);
        Coordinate targetCoordinateY = Coordinate.valueOf(3);

        Vector vector = new Vector(Arrays.asList(sourceCoordinateX, sourceCoordinateY, targetCoordinateX, targetCoordinateY));

        Route route = blackPawn.produceRoute(Arrays.asList(sourceCoordinateX, sourceCoordinateY), vector);
        assertThat(route).isEqualTo(new Route(Arrays.asList("54", "53")));
    }

    @Test
    void 경로확인_흑팀_남동방향_크기1() {
        Coordinate sourceCoordinateX = Coordinate.valueOf(5);
        Coordinate sourceCoordinateY = Coordinate.valueOf(5);
        Coordinate targetCoordinateX = Coordinate.valueOf(6);
        Coordinate targetCoordinateY = Coordinate.valueOf(4);

        Vector vector = new Vector(Arrays.asList(sourceCoordinateX, sourceCoordinateY, targetCoordinateX, targetCoordinateY));

        Route route = blackPawn.produceRoute(Arrays.asList(sourceCoordinateX, sourceCoordinateY), vector);
        assertThat(route).isEqualTo(new Route(Arrays.asList("64")));
    }

    @Test
    void 경로확인_흑팀_남서방향_크기1() {
        Coordinate sourceCoordinateX = Coordinate.valueOf(5);
        Coordinate sourceCoordinateY = Coordinate.valueOf(5);
        Coordinate targetCoordinateX = Coordinate.valueOf(4);
        Coordinate targetCoordinateY = Coordinate.valueOf(4);

        Vector vector = new Vector(Arrays.asList(sourceCoordinateX, sourceCoordinateY, targetCoordinateX, targetCoordinateY));

        Route route = blackPawn.produceRoute(Arrays.asList(sourceCoordinateX, sourceCoordinateY), vector);
        assertThat(route).isEqualTo(new Route(Arrays.asList("44")));
    }

    @Test
    void 경로오류_확인_백팀이_남방향으로_1칸_이동할_경우() {
        Coordinate sourceCoordinateX = Coordinate.valueOf(5);
        Coordinate sourceCoordinateY = Coordinate.valueOf(5);
        Coordinate targetCoordinateX = Coordinate.valueOf(5);
        Coordinate targetCoordinateY = Coordinate.valueOf(4);

        Vector vector = new Vector(Arrays.asList(sourceCoordinateX, sourceCoordinateY, targetCoordinateX, targetCoordinateY));

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> whitePawn.produceRoute(Arrays.asList(sourceCoordinateX, sourceCoordinateY), vector));
    }

    @Test
    void 경로오류_확인_백팀이_남방향으로_2칸_이동할_경우() {
        Coordinate sourceCoordinateX = Coordinate.valueOf(5);
        Coordinate sourceCoordinateY = Coordinate.valueOf(5);
        Coordinate targetCoordinateX = Coordinate.valueOf(5);
        Coordinate targetCoordinateY = Coordinate.valueOf(3);

        Vector vector = new Vector(Arrays.asList(sourceCoordinateX, sourceCoordinateY, targetCoordinateX, targetCoordinateY));

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> whitePawn.produceRoute(Arrays.asList(sourceCoordinateX, sourceCoordinateY), vector));
    }

    @Test
    void 경로오류_확인_백팀이_남동방향으로_1칸_이동할_경우() {
        Coordinate sourceCoordinateX = Coordinate.valueOf(5);
        Coordinate sourceCoordinateY = Coordinate.valueOf(5);
        Coordinate targetCoordinateX = Coordinate.valueOf(6);
        Coordinate targetCoordinateY = Coordinate.valueOf(4);

        Vector vector = new Vector(Arrays.asList(sourceCoordinateX, sourceCoordinateY, targetCoordinateX, targetCoordinateY));

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> whitePawn.produceRoute(Arrays.asList(sourceCoordinateX, sourceCoordinateY), vector));
    }

    @Test
    void 경로오류_확인_백팀이_남서방향으로_1칸_이동할_경우() {
        Coordinate sourceCoordinateX = Coordinate.valueOf(5);
        Coordinate sourceCoordinateY = Coordinate.valueOf(5);
        Coordinate targetCoordinateX = Coordinate.valueOf(4);
        Coordinate targetCoordinateY = Coordinate.valueOf(4);

        Vector vector = new Vector(Arrays.asList(sourceCoordinateX, sourceCoordinateY, targetCoordinateX, targetCoordinateY));

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> whitePawn.produceRoute(Arrays.asList(sourceCoordinateX, sourceCoordinateY), vector));
    }

    @Test
    void 경로오류_확인_흑팀이_북방향으로_1칸_이동할_경우() {
        Coordinate sourceCoordinateX = Coordinate.valueOf(5);
        Coordinate sourceCoordinateY = Coordinate.valueOf(5);
        Coordinate targetCoordinateX = Coordinate.valueOf(5);
        Coordinate targetCoordinateY = Coordinate.valueOf(6);

        Vector vector = new Vector(Arrays.asList(sourceCoordinateX, sourceCoordinateY, targetCoordinateX, targetCoordinateY));

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> blackPawn.produceRoute(Arrays.asList(sourceCoordinateX, sourceCoordinateY), vector));
    }

    @Test
    void 경로오류_확인_흑팀이_북방향으로_2칸_이동할_경우() {
        Coordinate sourceCoordinateX = Coordinate.valueOf(5);
        Coordinate sourceCoordinateY = Coordinate.valueOf(5);
        Coordinate targetCoordinateX = Coordinate.valueOf(5);
        Coordinate targetCoordinateY = Coordinate.valueOf(7);

        Vector vector = new Vector(Arrays.asList(sourceCoordinateX, sourceCoordinateY, targetCoordinateX, targetCoordinateY));

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> blackPawn.produceRoute(Arrays.asList(sourceCoordinateX, sourceCoordinateY), vector));
    }

    @Test
    void 경로오류_확인_흑팀이_북동방향으로_1칸_이동할_경우() {
        Coordinate sourceCoordinateX = Coordinate.valueOf(5);
        Coordinate sourceCoordinateY = Coordinate.valueOf(5);
        Coordinate targetCoordinateX = Coordinate.valueOf(6);
        Coordinate targetCoordinateY = Coordinate.valueOf(6);

        Vector vector = new Vector(Arrays.asList(sourceCoordinateX, sourceCoordinateY, targetCoordinateX, targetCoordinateY));

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> blackPawn.produceRoute(Arrays.asList(sourceCoordinateX, sourceCoordinateY), vector));
    }

    @Test
    void 경로오류_확인_흑팀이_북서방향으로_1칸_이동할_경우() {
        Coordinate sourceCoordinateX = Coordinate.valueOf(5);
        Coordinate sourceCoordinateY = Coordinate.valueOf(5);
        Coordinate targetCoordinateX = Coordinate.valueOf(4);
        Coordinate targetCoordinateY = Coordinate.valueOf(6);

        Vector vector = new Vector(Arrays.asList(sourceCoordinateX, sourceCoordinateY, targetCoordinateX, targetCoordinateY));

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> blackPawn.produceRoute(Arrays.asList(sourceCoordinateX, sourceCoordinateY), vector));
    }

    @Test
    void 백팀일때_한번이라도_움직였을_경우_빈_경로_반환_확인() {
        Piece clonedPiece = whitePawn.cloneSelf();
        Coordinate sourceCoordinateX = Coordinate.valueOf(5);
        Coordinate sourceCoordinateY = Coordinate.valueOf(5);
        Coordinate targetCoordinateX = Coordinate.valueOf(5);
        Coordinate targetCoordinateY = Coordinate.valueOf(7);

        Vector vector = new Vector(Arrays.asList(sourceCoordinateX, sourceCoordinateY, targetCoordinateX, targetCoordinateY));

//        assertThat(clonedPiece.produceRoute(Arrays.asList(sourceCoordinateX, sourceCoordinateY), vector)).isEqualTo(new Route(Collections.emptyList()));
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> clonedPiece.produceRoute(Arrays.asList(sourceCoordinateX, sourceCoordinateY), vector));

    }

    @Test
    void 흑팀일때_한번이라도_움직였을_경우_빈_경로_반환_확인() {
        Piece clonedPiece = blackPawn.cloneSelf();
        Coordinate sourceCoordinateX = Coordinate.valueOf(5);
        Coordinate sourceCoordinateY = Coordinate.valueOf(5);
        Coordinate targetCoordinateX = Coordinate.valueOf(5);
        Coordinate targetCoordinateY = Coordinate.valueOf(3);

        Vector vector = new Vector(Arrays.asList(sourceCoordinateX, sourceCoordinateY, targetCoordinateX, targetCoordinateY));

//        assertThat(clonedPiece.produceRoute(Arrays.asList(sourceCoordinateX, sourceCoordinateY), vector)).isEqualTo(new Route(Collections.emptyList()));
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> clonedPiece.produceRoute(Arrays.asList(sourceCoordinateX, sourceCoordinateY), vector));
    }

    @Test
    void 팀_확인_white팀일_경우() {
        assertThat(whitePawn.askTeamColor()).isEqualTo("white");
    }

    @Test
    void 팀_확인_black팀일_경우() {
        assertThat(blackPawn.askTeamColor()).isEqualTo("black");
    }

    @Test
    void pawn인지_확인() {
        assertThat(whitePawn.isPawn()).isTrue();
    }

    @Test
    void clone_확인() {
        assertThat(whitePawn.cloneSelf()).isEqualTo(new Pawn(false, "white"));
    }
}
