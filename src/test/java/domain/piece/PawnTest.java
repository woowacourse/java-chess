package domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import domain.position.File;
import domain.position.Position;
import domain.position.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PawnTest {

    /*
        ........
        ........
        ........
        ........
        ...*....
        ...*....
        ...S....
        ........
     */
    @Test
    @DisplayName("하얀 기물이 첫 이동일 경우 한 칸 또는 두 칸 위로 움직일 수 있다.")
    void canMove_WhiteFirstMove_True() {
        Piece piece = new Pawn(Color.WHITE);
        Position source = new Position(File.D, Rank.TWO);
        Position target1 = new Position(File.D, Rank.THREE);
        Position target2 = new Position(File.D, Rank.FOUR);

        boolean actual1 = piece.canMove(source, target1);
        boolean actual2 = piece.canMove(source, target2);

        assertThat(actual1).isTrue();
        assertThat(actual2).isTrue();
    }

    /*
        ........
        ........
        ...*....
        ...S....
        ........
        ........
        ........
        ........
     */
    @Test
    @DisplayName("하얀 기물이 첫 이동이 아닐 경우 한 칸 위로 움직일 수 있다.")
    void canMove_WhiteNotFirstMove_True() {
        Piece piece = new Pawn(Color.WHITE);
        Position source = new Position(File.D, Rank.FIVE);
        Position target = new Position(File.D, Rank.SIX);

        boolean actual = piece.canMove(source, target);

        assertThat(actual).isTrue();
    }

    @Test
    @DisplayName("하얀 기물이 첫 이동이 아닐 경우 한 칸 위가 아닌 곳으로 움직일 수 없다.")
    void canMove_WhiteNotFirstMove_False() {
        Piece piece = new Pawn(Color.WHITE);
        Position source = new Position(File.D, Rank.FIVE);
        Position target = new Position(File.D, Rank.SEVEN);

        boolean actual = piece.canMove(source, target);

        assertThat(actual).isFalse();
    }

    /*
        ........
        ...S....
        ...*....
        ...*....
        ........
        ........
        ........
        ........
     */
    @Test
    @DisplayName("검정 기물이 첫 이동일 경우 한 칸 또는 두 칸 아래로 움직일 수 있다.")
    void canMove_BlackFirstMove_True() {
        Piece piece = new Pawn(Color.BLACK);
        Position source = new Position(File.D, Rank.SEVEN);
        Position target1 = new Position(File.D, Rank.SIX);
        Position target2 = new Position(File.D, Rank.FIVE);

        boolean actual1 = piece.canMove(source, target1);
        boolean actual2 = piece.canMove(source, target2);

        assertThat(actual1).isTrue();
        assertThat(actual2).isTrue();
    }

    /*
        ........
        ........
        ........
        ........
        ........
        ...S....
        ...*....
        ........
     */
    @Test
    @DisplayName("검정 기물이 첫 이동이 아닐 경우 한 칸 아래로 움직일 수 있다.")
    void canMove_BlackNotFirstMove_True() {
        Piece piece = new Pawn(Color.BLACK);
        Position source = new Position(File.D, Rank.THREE);
        Position target = new Position(File.D, Rank.TWO);

        boolean actual = piece.canMove(source, target);

        assertThat(actual).isTrue();
    }

    @Test
    @DisplayName("검정 기물이 첫 이동이 아닐 경우 한 칸 아래가 아닌 곳으로 움직일 수 없다.")
    void canMove_BlackNotFirstMove_False() {
        Piece piece = new Pawn(Color.BLACK);
        Position source = new Position(File.D, Rank.THREE);
        Position target = new Position(File.D, Rank.ONE);

        boolean actual = piece.canMove(source, target);

        assertThat(actual).isFalse();
    }

    /*
        ........
        ........
        ........
        ........
        ........
        ..*.*...
        ...S....
        ........
     */
    @Test
    @DisplayName("하얀 기물이 오른쪽위 또는 왼쪽위 한 칸을 공격할 수 있다.")
    void canAttack_White_True() {
        Piece piece = new Pawn(Color.WHITE);
        Position source = new Position(File.D, Rank.TWO);
        Position target1 = new Position(File.E, Rank.THREE);
        Position target2 = new Position(File.C, Rank.THREE);

        boolean actual1 = piece.canAttack(source, target1);
        boolean actual2 = piece.canAttack(source, target2);

        assertThat(actual1).isTrue();
        assertThat(actual2).isTrue();
    }

    @Test
    @DisplayName("하얀 기물이 오른쪽위 또는 왼쪽위 한 칸이 아닌 곳을 공격할 수 없다.")
    void canAttack_White_False() {
        Piece piece = new Pawn(Color.WHITE);
        Position source = new Position(File.D, Rank.TWO);
        Position target1 = new Position(File.E, Rank.FOUR);
        Position target2 = new Position(File.C, Rank.FOUR);

        boolean actual1 = piece.canAttack(source, target1);
        boolean actual2 = piece.canAttack(source, target2);

        assertThat(actual1).isFalse();
        assertThat(actual2).isFalse();
    }

    /*
        ........
        ...S....
        ..*.*...
        ........
        ........
        ........
        ........
        ........
     */
    @Test
    @DisplayName("검정 기물이 오른쪽아래 또는 왼쪽아래 한 칸을 공격할 수 있다.")
    void canAttack_Black_True() {
        Piece piece = new Pawn(Color.BLACK);
        Position source = new Position(File.D, Rank.SEVEN);
        Position target1 = new Position(File.E, Rank.SIX);
        Position target2 = new Position(File.C, Rank.SIX);

        boolean actual1 = piece.canAttack(source, target1);
        boolean actual2 = piece.canAttack(source, target2);

        assertThat(actual1).isTrue();
        assertThat(actual2).isTrue();
    }

    @Test
    @DisplayName("검정 기물이 오른쪽아래 또는 왼쪽아래 한 칸이 아닌 곳을 공격할 수 없다.")
    void canAttack_Black_False() {
        Piece piece = new Pawn(Color.BLACK);
        Position source = new Position(File.D, Rank.SEVEN);
        Position target1 = new Position(File.E, Rank.FIVE);
        Position target2 = new Position(File.C, Rank.FIVE);

        boolean actual1 = piece.canAttack(source, target1);
        boolean actual2 = piece.canAttack(source, target2);

        assertThat(actual1).isFalse();
        assertThat(actual2).isFalse();
    }
}
