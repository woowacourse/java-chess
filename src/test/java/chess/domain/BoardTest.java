package chess.domain;

import chess.domain.RuleImpl.Empty;
import chess.domain.RuleImpl.Knight;
import chess.domain.RuleImpl.Pawn;
import chess.domain.RuleImpl.Rook;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class BoardTest {
    Map<Position, Square> map;
    Board board;

    @BeforeEach
    public void setUp() {
//        Position position1 = Position.of("1", "a");
//        Position position2 = Position.of("2", "a");
//        Position position2 = Position.of("2", "a");
//        Position position2 = Position.of("2", "a");
//        Position position2 = Position.of("2", "a");
//
//        Position position3 = Position.of("6", "a");
//        Position position4 = Position.of("7", "a");

        List<Position> positions = new ArrayList<>();
        List<Square> squares = new ArrayList<>();

        for (int i = 1; i <= 8; i++) {
            Position position = Position.of(String.valueOf(i), "a");
            positions.add(position);
        }

        Square square1 = Square.of(positions.get(0), Piece.of(Piece.Color.WHITE, Rook.getInstance()));
        Square square2 = Square.of(positions.get(1), Piece.of(Piece.Color.WHITE, Pawn.FIRST_BOTTOM));

        squares.add(square1);
        squares.add(square2);
        for (int i = 3; i <= 6; i++) {
            Square square = Square.of(positions.get(i-1), Piece.of(Piece.Color.EMPTY, Empty.getInstance()));
            squares.add(square);
        }
        Square square3 = Square.of(positions.get(6), Piece.of(Piece.Color.BLACK, Rook.getInstance()));
//        Square square4 = Square.of(positions.get(7), Piece.of(Piece.Color.BLACK, Pawn.FIRST_TOP));
        Square square4 = Square.of(positions.get(7), Piece.of(Piece.Color.BLACK, Rook.getInstance()));
        squares.add(square3);
        squares.add(square4);

        map = new HashMap<>();


        for (int i = 0; i < 8; i++) {
            map.put(positions.get(i), squares.get(i));
        }

        board = new Board(map);
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
        Position origin = Position.of("7", "a");
        Position target = Position.of("2", "a");

        assertTrue(board.action(origin, target));
    }
}