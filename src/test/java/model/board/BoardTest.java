package model.board;

import model.game.Player;
import model.piece.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BoardTest {
    private Board testBoard;

    @BeforeEach
    void setUp() {
        testBoard = new Board();
    }

    @Test
    void initTest() {
        List<Piece> pieces = testBoard.getPieces().collect(Collectors.toList());
        List<Class> types = Arrays.asList(
                Rook.class,
                Knight.class,
                Bishop.class,
                Queen.class,
                King.class,
                Bishop.class,
                Knight.class,
                Rook.class,
                Pawn.class,
                Pawn.class,
                Pawn.class,
                Pawn.class,
                Pawn.class,
                Pawn.class,
                Pawn.class,
                Pawn.class,
                Pawn.class,
                Pawn.class,
                Pawn.class,
                Pawn.class,
                Pawn.class,
                Pawn.class,
                Pawn.class,
                Pawn.class,
                Rook.class,
                Knight.class,
                Bishop.class,
                Queen.class,
                King.class,
                Bishop.class,
                Knight.class,
                Rook.class
        );
        List<Position> positions = new ArrayList<>();
        for (int i = Coord.MIN; i < Coord.MAX; i++) {
            positions.add(Position.of(String.valueOf((char) (i + 'a')) + 1));
            positions.add(Position.of(String.valueOf((char) (i + 'a')) + 2));
            positions.add(Position.of(String.valueOf((char) (i + 'a')) + 7));
            positions.add(Position.of(String.valueOf((char) (i + 'a')) + 8));
        }
        Collections.sort(positions);
        assertThat(
                IntStream.range(0, pieces.size())
                        .mapToObj(i ->
                                pieces.get(i).getClass().equals(types.get(i))
                                && pieces.get(i).position() == positions.get(i)
                        )
                        .allMatch(x -> x)
        ).isTrue();
        assertThat(
                pieces.stream()
                        .collect(Collectors.partitioningBy(x -> x.team() == Player.WHITE))
                        .values().stream()
                        .map(l -> l.size())
                        .allMatch(i -> i == 16)
        );
    }

    @Test
    void getPieceAtTest() {
        assertThat(
                testBoard.getPieceAt(Position.of("c7")).get().position()
        ).isEqualTo(Position.of("c7"));
    }

    @Test
    void getPieceTestAtWrongPosition() {
        assertThatThrownBy(
                () -> testBoard.getPieceAt(Position.of("j1")).get().position()
        );
    }

    @Test
    void removePieceTest() {
        Position pos = Position.of("d2");
        assertThat(testBoard.removePieceAt(pos)).isTrue();
        assertThat(testBoard.getPieceAt(pos).isPresent()).isFalse();
    }

    @Test
    void moveTest() {
        testBoard.movePieceFromTo(Position.of("e1"), Position.of("c6"));
        assertThat(testBoard.getPieceAt(Position.of("c6")).get().isKing()).isTrue();
    }

    @Test
    void changeTypeTest() {
        Piece p = testBoard.getPieceAt(Position.of("a2")).get();
        assertThat(p.hasNotMoved()).isTrue();
        assertThat(testBoard.movePieceFromTo(Position.of("a2"), Position.of("a4"))).isTrue();
        assertThat(p.hasNotMoved()).isFalse();
        try {
            assertThat(testBoard.changeTypeOfPieceAt(Position.of("a4"), Queen.class)).isTrue();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Piece q = testBoard.getPieceAt(Position.of("a4")).get();
        assertThat(q.isPawn()).isFalse();
        assertThat(q.hasNotMoved()).isFalse();
        assertThat(q instanceof Queen).isTrue();
    }
}