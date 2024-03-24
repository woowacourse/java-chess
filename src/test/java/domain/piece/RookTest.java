package domain.piece;

import domain.position.File;
import domain.position.Position;
import domain.position.Rank;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RookTest {

    /*
        *.......
        T.......
        *.......
        *.......
        *.......
        *.......
        *.......
        S*******
     */
    @Test
    @DisplayName("목적지가 수직 직선 경로에 있는 경우 움직일 수 있다.")
    void canMove_Vertical_True() {
        Piece piece = new Rook(Color.WHITE);
        Position source = Position.generate(File.A, Rank.ONE);
        Position target = Position.generate(File.A, Rank.SEVEN);

        boolean actual = piece.canMove(source, target);

        Assertions.assertThat(actual).isTrue();
    }

    /*
        *.......
        *.......
        *.......
        *.......
        *.......
        *.......
        *.......
        S*****T*
     */
    @Test
    @DisplayName("목적지가 수평 직선 경로에 있는 경우 움직일 수 있다.")
    void canMove_Horizontal_True() {
        Piece piece = new Rook(Color.WHITE);
        Position source = Position.generate(File.A, Rank.ONE);
        Position target = Position.generate(File.G, Rank.ONE);

        boolean actual = piece.canMove(source, target);

        Assertions.assertThat(actual).isTrue();
    }

    /*
        *......T
        *.......
        *.......
        *.......
        *.......
        *.......
        *.......
        S*******
     */
    @Test
    @DisplayName("목적지가 직선 경로에 없는 경우 움직일 수 없다.")
    void canMove_Horizontal_False() {
        Piece piece = new Rook(Color.WHITE);
        Position source = Position.generate(File.A, Rank.ONE);
        Position target = Position.generate(File.H, Rank.EIGHT);

        boolean actual = piece.canMove(source, target);

        Assertions.assertThat(actual).isFalse();
    }
}
