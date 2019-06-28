package chess.domain.pieces;

import chess.domain.Point;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PawnTest {

    @Test
    void 정상_생성_Test() {
        assertDoesNotThrow(() -> new Pawn(Color.BLACK));
    }

    @Test
    void 첫_턴_2칸_정상_이동_Test() {
        Pawn pawn = new Pawn(Color.BLACK);
        Point source = PointFactory.of("d7");
        Point target = PointFactory.of("d5");
        List<Point> path = Arrays.asList(
                PointFactory.of("d6"),
                PointFactory.of("d5"));
        assertThat(path).isEqualTo(pawn.action(source, target, false));
    }

    @Test
    void 첫_턴_1칸_정상_이동_Test() {
        Pawn pawn = new Pawn(Color.BLACK);
        Point source = PointFactory.of("d7");
        Point target = PointFactory.of("d6");
        List<Point> path = Arrays.asList(
                PointFactory.of("d6"));
        assertThat(path).isEqualTo(pawn.action(source, target, false));
    }

    @Test
    void 첫_턴_이후_1칸_정상_이동_Test() {
        Pawn pawn = new Pawn(Color.BLACK);
        Point source = PointFactory.of("d7");
        Point target = PointFactory.of("d6");
        // 첫 턴 이동
        pawn.action(source, target, false);
        // 첫 턴 이후
        List<Point> path = Arrays.asList(
                PointFactory.of("d6"));
        assertThat(path).isEqualTo(pawn.action(source, target, false));
    }

    @Test
    void 첫_턴_이후_2칸_비정상_이동_Test() {
        Pawn pawn = new Pawn(Color.BLACK);
        Point source = PointFactory.of("d6");
        Point target = PointFactory.of("d4");
        assertThrows(IllegalArgumentException.class, () -> pawn.action(source, target, false));
    }

    @Test
    void 비정상_이동_Test() {
        Pawn pawn = new Pawn(Color.BLACK);
        Point source = PointFactory.of("d7");
        Point target = PointFactory.of("d4");
        assertThrows(IllegalArgumentException.class, () -> pawn.action(source, target, false));
    }

    @Test
    void 정상_공격_Test() {
        Pawn pawn = new Pawn(Color.BLACK);
        Point source = PointFactory.of("d7");
        Point target = PointFactory.of("c6");
        assertThat(new ArrayList<>()).isEqualTo(pawn.action(source, target, true));
    }

    @Test
    void 비정상_공격_Test() {
        Pawn pawn = new Pawn(Color.BLACK);
        Point source = PointFactory.of("d7");
        Point target = PointFactory.of("b5");
        assertThrows(IllegalArgumentException.class, () -> pawn.action(source, target, true));
    }
}
