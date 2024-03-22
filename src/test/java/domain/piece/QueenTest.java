package domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import domain.position.File;
import domain.position.Position;
import domain.position.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class QueenTest {

    /*
        ...*...*
        *..*..*.
        .*.T.*..
        ..***...
        ***S****
        ..***...
        .*.*.*..
        *..*..*.
     */

    @Test
    @DisplayName("목적지가 직선 또는 대각선 경로에 있는 경우 움직일 수 있다.")
    void canMove_StraightDiagonal_True() {
        Piece piece = new Queen(Color.WHITE);
        Position source = new Position(File.D, Rank.FOUR);
        Position target = new Position(File.D, Rank.SIX);

        boolean actual = piece.canMove(source, target);

        assertThat(actual).isTrue();

    }

    /*
        ...*...*
        *..*..*.
        .*.*.*..
        ..***...
        ***S****
        ..***..T
        .*.*.*..
        *..*..*.
     */

    @Test
    @DisplayName("목적지가 직선 또는 대각선 경로에 없는 경우 움직일 수 없다.")
    void canMove_StraightDiagonal_False() {
        Piece piece = new Queen(Color.WHITE);
        Position source = new Position(File.D, Rank.FOUR);
        Position target = new Position(File.H, Rank.THREE);

        boolean actual = piece.canMove(source, target);

        assertThat(actual).isFalse();

    }
}
