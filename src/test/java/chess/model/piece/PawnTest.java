package chess.model.piece;

import chess.model.Coordinate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class PawnTest {
    private Piece whitePawn;
    private Piece blackPawn;

    @BeforeEach
    void setUp() {
        whitePawn = new Pawn("white");
        blackPawn = new Pawn("black");
    }

    @Test
    void 잘못된_팀을_입력할_경우_확인() {
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> new Pawn("purple"));
    }

    @Test
    void 백팀일_경우_한칸_움직일_수_있는지_확인() {
        boolean path = whitePawn.isMovePossible(Arrays.asList(Coordinate.valueOf(2), Coordinate.valueOf(2), Coordinate.valueOf(2), Coordinate.valueOf(3)));
        assertThat(path).isTrue();
    }

    @Test
    void 백팀일_경우_두칸_움직일_수_있는지_확인() {
        boolean path = whitePawn.isMovePossible(Arrays.asList(Coordinate.valueOf(2), Coordinate.valueOf(2), Coordinate.valueOf(2), Coordinate.valueOf(4)));
        assertThat(path).isTrue();
    }

    @Test
    void 백팀일_경우_대각선_움직일_수_있는지_확인() {
        boolean path = whitePawn.isMovePossible(Arrays.asList(Coordinate.valueOf(2), Coordinate.valueOf(2), Coordinate.valueOf(3), Coordinate.valueOf(3)));
        assertThat(path).isTrue();
    }

    @Test
    void 백팀일_경우_대각선_움직일_수_있는지_확인2() {
        boolean path = whitePawn.isMovePossible(Arrays.asList(Coordinate.valueOf(2), Coordinate.valueOf(2), Coordinate.valueOf(1), Coordinate.valueOf(3)));
        assertThat(path).isTrue();
    }

    @Test
    void 백팀일_경우_두칸_움직일_수_없는지_확인() {
        whitePawn.signalMoved();
        boolean path = whitePawn.isMovePossible(Arrays.asList(Coordinate.valueOf(2), Coordinate.valueOf(2), Coordinate.valueOf(2), Coordinate.valueOf(4)));
        assertThat(path).isFalse();
    }

    @Test
    void 흑팀일_경우_한칸_움직일_수_있는지_확인() {
        boolean path = blackPawn.isMovePossible(Arrays.asList(Coordinate.valueOf(3), Coordinate.valueOf(3), Coordinate.valueOf(3), Coordinate.valueOf(2)));
        assertThat(path).isTrue();
    }

    @Test
    void 흑팀일_경우_두칸_움직일_수_있는지_확인() {
        boolean path = blackPawn.isMovePossible(Arrays.asList(Coordinate.valueOf(3), Coordinate.valueOf(3), Coordinate.valueOf(3), Coordinate.valueOf(1)));
        assertThat(path).isTrue();
    }

    @Test
    void 흑팀일_경우_대각선_움직일_수_있는지_확인() {
        boolean path = blackPawn.isMovePossible(Arrays.asList(Coordinate.valueOf(2), Coordinate.valueOf(2), Coordinate.valueOf(3), Coordinate.valueOf(1)));
        assertThat(path).isTrue();
    }

    @Test
    void 흑팀일_경우_대각선_움직일_수_있는지_확인2() {
        boolean path = blackPawn.isMovePossible(Arrays.asList(Coordinate.valueOf(2), Coordinate.valueOf(2), Coordinate.valueOf(1), Coordinate.valueOf(1)));
        assertThat(path).isTrue();
    }

    @Test
    void 흑팀일_경우_두칸_움직일_수_없는지_확인() {
        blackPawn.signalMoved();
        boolean path = blackPawn.isMovePossible(Arrays.asList(Coordinate.valueOf(2), Coordinate.valueOf(3), Coordinate.valueOf(2), Coordinate.valueOf(1)));
        assertThat(path).isFalse();
    }
}
