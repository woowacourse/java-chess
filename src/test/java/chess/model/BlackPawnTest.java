package chess.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class BlackPawnTest {
    private BlackPawn blackPawn;

    @BeforeEach
    void setUp() {
        blackPawn = new BlackPawn();
    }

    @Test
    void 한칸_이동_가능_테스트() {
        Map<Point, AbstractChessPiece> board = new HashMap<>();
        Point source = new Point(3,2);
        Point target = new Point(3, 1);
        board.put(source, blackPawn);
        assertThat(blackPawn.canMove(source, target, board::get)).isTrue();
    }

    @Test
    void 한칸_대각선_이동_가능_테스트() {
        Map<Point, AbstractChessPiece> board = new HashMap<>();
        Point source = new Point(3,3);
        Point target = new Point(2, 2);
        board.put(source, blackPawn);
        board.put(target, new WhitePawn());
        assertThat(blackPawn.canMove(source, target, board::get)).isTrue();
    }

    @Test
    void 한칸_대각선_이동_불가능_테스트() {
        Map<Point, AbstractChessPiece> board = new HashMap<>();
        Point source = new Point(3,3);
        Point target = new Point(4, 4);
        board.put(source, blackPawn);
        assertThat(blackPawn.canMove(source, target, board::get)).isFalse();
    }

    @Test
    void 흑색_유닛에_막혀_한칸_이동_불가능_테스트_() {
        Map<Point, AbstractChessPiece> board = new HashMap<>();
        Point source = new Point(3,2);
        Point target = new Point(3, 1);
        board.put(source, blackPawn);
        board.put(target, new WhitePawn());
        assertThat(blackPawn.canMove(source, target, board::get)).isFalse();
    }

    @Test
    void 흑색_폰_위로_한칸_이동_불가능_테스트_() {
        Map<Point, AbstractChessPiece> board = new HashMap<>();
        Point source = new Point(3,7);
        Point target = new Point(3, 8);
        board.put(source, blackPawn);
        assertThat(blackPawn.canMove(source, target, board::get)).isFalse();
    }
}
