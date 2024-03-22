package domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import domain.position.File;
import domain.position.Position;
import domain.position.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BishopTest {

    /*
        .......T
        *.....*.
        .*...*..
        ..*.*...
        ...S....
        ..*.*...
        .*...*..
        *.....*.
     */
    @Test
    @DisplayName("목적지가 대각선 경로에 있는 경우 움직일 수 있다.")
    void canMove_Diagonal_True() {
        Piece piece = new Bishop(Color.WHITE);
        Position source = new Position(File.D, Rank.FOUR);
        Position target = new Position(File.H, Rank.EIGHT);

        boolean actual = piece.canMove(source, target);

        assertThat(actual).isTrue();
    }

    /*
        .......*
        *.....*.
        .*...*..
        ..*.*...
        ...S...T
        ..*.*...
        .*...*..
        *.....*.
     */
    @Test
    @DisplayName("목적지가 대각선 경로에 없는 경우 움직일 수 없다.")
    void canMove_Diagonal_False() {
        Piece piece = new Rook(Color.WHITE);
        Position source = new Position(File.A, Rank.ONE);
        Position target = new Position(File.H, Rank.FOUR);

        boolean actual = piece.canMove(source, target);

        assertThat(actual).isFalse();
    }
}
