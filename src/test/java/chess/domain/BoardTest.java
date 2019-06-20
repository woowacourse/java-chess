package chess.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BoardTest {
    Map<Position, Square> map;
    Board board;

    @BeforeEach
    public void setUp() {
        board = new Board(BoardGenerator.generate());

        Position origin = Position.of("2", "d");
        Position target = Position.of("4", "d");
        board.action(origin, target);
        origin = Position.of("1", "c");
        target = Position.of("6", "h");
        board.action(origin, target);

    }


    @Test
    public void 장애물이_있을떄_빈칸_이동_테스트() {
        Position origin = Position.of("1", "a");
        Position target = Position.of("6", "a");

        assertFalse(board.action(origin, target));
    }

    @Test
    public void 빈칸을_눌렀을때() {
        Position origin = Position.of("6", "a");
        Position target = Position.of("1", "a");

        assertFalse(board.action(origin, target));
    }

    @Test
    public void 타겟에_같은팀_말이_있을때() {
        Position origin = Position.of("1", "a");
        Position target = Position.of("2", "a");

        assertFalse(board.action(origin, target));
    }

    @Test
    public void 타겟에_다른팀이_있을때_공격() {
        Position origin = Position.of("6", "h");
        Position target = Position.of("7", "g");

        assertTrue(board.action(origin, target));

        origin = Position.of("8", "f");
        target = Position.of("7", "g");
        assertTrue(board.action(origin, target));
    }

    @Test
    public void 이동_후에_공격_테스트() {
        Position origin = Position.of("6", "h");
        Position target = Position.of("5", "g");
        assertTrue(board.action(origin, target));

        origin = Position.of("5", "g");
        target = Position.of("7", "e");
        assertTrue(board.action(origin, target));
    }
}