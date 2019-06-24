package chess.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class WhitePawnTest {
    private WhitePawn whitePawn;

    @BeforeEach
    void setUp() {
        whitePawn = new WhitePawn();
    }

    @Test
    void 한칸_이동_가능_테스트() {
        Map<Point, AbstractChessPiece> board = new HashMap<>();
        Point source = new Point(3,2);
        Point target = new Point(3, 3);
        board.put(source, whitePawn);
        assertThat(whitePawn.canMove(source, target, board::get)).isTrue();
    }

    @Test
    void 한칸_대각선_이동_가능_테스트() {
        Map<Point, AbstractChessPiece> board = new HashMap<>();
        Point source = new Point(3,3);
        Point target = new Point(4, 4);
        board.put(source, whitePawn);
        board.put(target, new BlackPawn());
        assertThat(whitePawn.canMove(source, target, board::get)).isTrue();
    }

    @Test
    void 한칸_대각선_이동_불가능_테스트() {
        Map<Point, AbstractChessPiece> board = new HashMap<>();
        Point source = new Point(3,3);
        Point target = new Point(4, 4);
        board.put(source, whitePawn);
        assertThat(whitePawn.canMove(source, target, board::get)).isFalse();
    }

    @Test
    void 유닛에_막혀_대각선_한칸_이동_불가능_테스트_() {
        Map<Point, AbstractChessPiece> board = new HashMap<>();
        Point source = new Point(3,2);
        Point target = new Point(3, 3);
        board.put(source, whitePawn);
        board.put(target, new WhitePawn());
        assertThat(whitePawn.canMove(source, target, board::get)).isFalse();
    }

    @Test
    void 유닛에_막혀_한칸_이동_불가능_테스트_() {
        Map<Point, AbstractChessPiece> board = new HashMap<>();
        Point source = new Point(3,2);
        Point target = new Point(3, 3);
        board.put(source, whitePawn);
        board.put(target, new WhitePawn());
        assertThat(whitePawn.canMove(source, target, board::get)).isFalse();
    }

}
