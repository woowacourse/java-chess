package chess.model;

import chess.model.coordinate.Point;
import chess.model.pieces.BlackPawn;
import chess.model.pieces.WhitePawn;
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
        Point source = new Point(3, 2);
        Point target = new Point(3, 3);
        board.put(source, whitePawn);
        assertThat(whitePawn.canMove(source, target, board::get)).isTrue();
    }

    @Test
    void 한칸_대각선_이동_가능_테스트() {
        Map<Point, AbstractChessPiece> board = new HashMap<>();
        Point source = new Point(3, 3);
        Point target = new Point(4, 4);
        board.put(source, whitePawn);
        board.put(target, new BlackPawn());
        assertThat(whitePawn.canMove(source, target, board::get)).isTrue();
    }

    @Test
    void 한칸_대각선_이동_불가능_테스트() {
        Map<Point, AbstractChessPiece> board = new HashMap<>();
        Point source = new Point(3, 3);
        Point target = new Point(4, 4);
        board.put(source, whitePawn);
        assertThat(whitePawn.canMove(source, target, board::get)).isFalse();
    }

    @Test
    void 유닛에_막혀_대각선_한칸_이동_불가능_테스트_() {
        Map<Point, AbstractChessPiece> board = new HashMap<>();
        Point source = new Point(3, 2);
        Point target = new Point(3, 3);
        board.put(source, whitePawn);
        board.put(target, new WhitePawn());
        assertThat(whitePawn.canMove(source, target, board::get)).isFalse();
    }

    @Test
    void 유닛에_막혀_한칸_이동_불가능_테스트_() {
        Map<Point, AbstractChessPiece> board = new HashMap<>();
        Point source = new Point(3, 2);
        Point target = new Point(3, 3);
        board.put(source, whitePawn);
        board.put(target, new WhitePawn());
        assertThat(whitePawn.canMove(source, target, board::get)).isFalse();
    }

    @Test
    void 점수_계산_같은_열에_다른_폰_붙어있을때_테스트() {
        Map<Point, AbstractChessPiece> board = new HashMap<>();
        Point p1 = new Point(1, 1);
        Point p2 = new Point(1, 2);
        board.put(p1, whitePawn);
        board.put(p2, whitePawn);
        WhitePawn whitePawn = new WhitePawn();
        assertThat(whitePawn.getScore(p1, (Point point) -> board.get(point))).isEqualTo(0.5);
    }

    @Test
    void 점수_계산_같은_열에_다른_폰_떨어져_있을때_테스트() {
        Map<Point, AbstractChessPiece> board = new HashMap<>();
        Point p1 = new Point(1, 1);
        Point p2 = new Point(1, 8);
        board.put(p1, whitePawn);
        board.put(p2, whitePawn);
        WhitePawn whitePawn = new WhitePawn();
        assertThat(whitePawn.getScore(p1, (Point point) -> board.get(point))).isEqualTo(0.5);
    }

    @Test
    void 점수_계산_같은_열에_다른_폰_없을때_테스트() {
        Map<Point, AbstractChessPiece> board = new HashMap<>();
        Point p1 = new Point(1, 1);
        board.put(p1, whitePawn);
        WhitePawn whitePawn = new WhitePawn();
        assertThat(whitePawn.getScore(p1, (Point point) -> board.get(point))).isEqualTo(1);
    }
}
